package com.sl.transport.socket.server;

import com.sl.handler.RequestHandler;
import com.sl.hook.ShutdownHook;
import com.sl.provider.ServiceProvider;
import com.sl.provider.ServiceProviderImpl;
import com.sl.registry.NacosServiceRegistry;
import com.sl.serializer.CommonSerializer;
import com.sl.transport.AbstractRpcServer;
import com.sl.transport.RpcServer;
import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sl.registry.ServiceRegistry;
import factory.ThreadPoolFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by yazai
 * Date: 下午3:07 2021/7/4
 * Socket方式远程方法调用的提供者（服务端）
 */
public class SocketServer extends AbstractRpcServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private final ExecutorService threadPool;
    private final CommonSerializer serializer;
    private final RequestHandler requestHandler = new RequestHandler();

    public SocketServer(String host, int port) {
        this(host, port, DEFAULT_SERIALIZER);
    }

    public SocketServer(String host, int port, Integer serializer) {
        this.host = host;
        this.port = port;
        threadPool = ThreadPoolFactory.createDefaultThreadPool("socket-rpc-server");
        this.serviceRegistry = new NacosServiceRegistry();
        this.serviceProvider = new ServiceProviderImpl();
        this.serializer = CommonSerializer.getByCode(serializer);
        scanServices();
    }


    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(host, port));
            logger.info("服务器启动……");
            //注册钩子，方便服务器停止时自动把服务从nacos中注销
            ShutdownHook.getShutdownHook().addClearAllHook();
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serializer));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

}