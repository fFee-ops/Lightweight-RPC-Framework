package com.sl;

import com.sl.RpcClientProxy;
import com.sl.api.HelloObject;
import com.sl.api.HelloService;
import com.sl.socket.client.SocketClient;

/**
 * Created by yazai
 * Date: 下午3:48 2021/7/4
 */
public class TestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(client);
        /*
          这里是客户端，没有HelloService的实现类，但是我又需要调用它的方法，所以就需要用到JDK动态代理
          来产生一个代理对象，进而创造出HelloService，来调用它的hello（）
         */
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "这是我的测试讯息✉️");

        //调用hello的时候会被动态代理拦截，然后去执行invoke()
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
