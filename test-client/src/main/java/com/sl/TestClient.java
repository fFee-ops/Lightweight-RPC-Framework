package com.sl;

import com.sl.api.ByeService;
import com.sl.api.HelloObject;
import com.sl.api.HelloService;
import com.sl.serializer.CommonSerializer;
import com.sl.serializer.KryoSerializer;
import com.sl.transport.RpcClientProxy;
import com.sl.transport.socket.client.SocketClient;

/**
 * Created by yazai
 * Date: 下午3:48 2021/7/4
 */
public class TestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.KRYO_SERIALIZER);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = proxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }
}
