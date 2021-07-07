package com.sl;

import com.sl.api.ByeService;
import com.sl.api.HelloObject;
import com.sl.api.HelloService;
import com.sl.loadbalancer.RoundRobinLoadBalancer;
import com.sl.serializer.CommonSerializer;
import com.sl.serializer.ProtobufSerializer;
import com.sl.transport.RpcClient;
import com.sl.transport.RpcClientProxy;
import com.sl.transport.netty.client.NettyClient;

/**
 * Created by yazai
 * Date: 21:08 2021/7/5
 * Description:
 */
public class NettyTestClient {
    public static void main(String[] args) {
        //指定序列化器
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER,new RoundRobinLoadBalancer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }
}
