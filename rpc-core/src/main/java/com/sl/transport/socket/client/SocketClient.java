package com.sl.transport.socket.client;

import com.sl.loadbalancer.LoadBalancer;
import com.sl.loadbalancer.RandomLoadBalancer;
import com.sl.registry.NacosServiceDiscovery;
import com.sl.registry.NacosServiceRegistry;
import com.sl.registry.ServiceDiscovery;
import com.sl.registry.ServiceRegistry;
import com.sl.serializer.CommonSerializer;
import com.sl.transport.RpcClient;
import com.sl.transport.util.ObjectReader;
import com.sl.transport.util.ObjectWriter;
import entity.RpcRequest;
import entity.RpcResponse;
import enumeration.ResponseCode;
import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.RpcMessageChecker;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by yazai
 * Date: 下午2:37 2021/7/4
 * Socket方式远程方法调用的消费者（客户端）
 */
public class SocketClient implements RpcClient {
    private final static Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private final ServiceDiscovery serviceDiscovery;
    private CommonSerializer serializer;

    public SocketClient() {
        this(DEFAULT_SERIALIZER, new RandomLoadBalancer());
    }
    public SocketClient(LoadBalancer loadBalancer) {
        this(DEFAULT_SERIALIZER, loadBalancer);
    }
    public SocketClient(Integer serializer) {
        this(serializer, new RandomLoadBalancer());
    }

    public SocketClient(Integer serializer, LoadBalancer loadBalancer) {
        this.serviceDiscovery = new NacosServiceDiscovery(loadBalancer);
        this.serializer = CommonSerializer.getByCode(serializer);
    }

    /**
     * 作用就是将一个对象发过去，并且接受返回的对象
     *
     * @param rpcRequest
     * @return
     */
    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        if (serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getInterfaceName());
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectWriter.writeObject(outputStream, rpcRequest, serializer);
            Object obj = ObjectReader.readObject(inputStream);
            RpcResponse rpcResponse = (RpcResponse) obj;
            if (rpcResponse == null) {
                logger.error("服务调用失败，service：{}", rpcRequest.getInterfaceName());
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            if (rpcResponse.getStatusCode() == null || rpcResponse.getStatusCode() != ResponseCode.SUCCESS.getCode()) {
                logger.error("调用服务失败, service: {}, response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            RpcMessageChecker.check(rpcRequest, rpcResponse);
            return rpcResponse;
        } catch (IOException e) {
            logger.error("调用时有错误发生：", e);
            throw new RpcException("服务调用失败: ", e);
        }
    }

}
