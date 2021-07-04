package com.sl.api;

/**
 * Created by yazai
 * Date: 下午2:13 2021/7/4
 * 暴露给客户端和服务端的通用接口，方便客户端调用服务端的方法等
 */
public interface HelloService {
    String hello(HelloObject object);
}
