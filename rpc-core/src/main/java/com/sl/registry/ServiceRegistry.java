package com.sl.registry;

import java.net.InetSocketAddress;

/**
 * Created by yazai
 * Date: 22:31 2021/7/4
 * Description: 服务注册中心通用接口 [不同的注册中心通过继承该接口来实现具体的方法]
 */
public interface ServiceRegistry {

    /**
     * 将一个服务注册进注册表
     *
     * @param serviceName 服务名称
     * @param inetSocketAddress 提供服务的地址
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);

}
