package com.sl.transport.netty.server;

import com.sl.hook.ShutdownHook;
import com.sl.provider.ServiceProvider;
import com.sl.provider.ServiceProviderImpl;
import com.sl.registry.NacosServiceRegistry;
import com.sl.registry.ServiceRegistry;
import com.sl.serializer.CommonSerializer;
import com.sl.transport.AbstractRpcServer;
import com.sl.transport.RpcServer;
import com.sl.codec.CommonDecoder;
import com.sl.codec.CommonEncoder;
import com.sl.serializer.KryoSerializer;
import enumeration.RpcError;
import exception.RpcException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by yazai
 * Date: 20:59 2021/7/5
 * Description: NIO方式服务提供侧
 */
public class NettyServer extends AbstractRpcServer {

    private final CommonSerializer serializer;

    public NettyServer(String host, int port) {
        this(host, port, DEFAULT_SERIALIZER);
    }

    public NettyServer(String host, int port, Integer serializer) {
        this.host = host;
        this.port = port;
        serviceRegistry = new NacosServiceRegistry();
        serviceProvider = new ServiceProviderImpl();
        this.serializer = CommonSerializer.getByCode(serializer);
        //自动注册服务
        scanServices();
    }

    @Override
    public void start() {
        //注册钩子，方便服务器停止时自动把服务从nacos中注销
        ShutdownHook.getShutdownHook().addClearAllHook();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG, 256)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS))
                                    .addLast(new CommonEncoder(serializer))
                                    .addLast(new CommonDecoder())
                                    .addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            logger.error("启动服务器时有错误发生: ", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



}
