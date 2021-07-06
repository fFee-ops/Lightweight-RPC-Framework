package com.sl;

import com.sl.api.HelloObject;
import com.sl.api.HelloService;
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

        //RpcClientProxy 通过传入不同的 Client（SocketClient、NettyClient）来切换客户端不同的发送方式。
        RpcClient client = new NettyClient();
        client.setSerializer(new ProtobufSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "这是我的测试讯息✉️");
        String res = helloService.hello(object);
        System.out.println(res);

    }
}
