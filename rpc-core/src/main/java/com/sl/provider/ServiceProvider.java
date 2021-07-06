package com.sl.provider;

/**
 * Created by yazai
 * Date: 22:17 2021/7/6
 * Description: 保存和提供服务实例对象
 */
public interface ServiceProvider {

    /**
     * 将一个服务注册进注册表
     *
     * @param service 待注册的服务实体
     * @param <T>     服务实体类
     */
    <T> void addServiceProvider(T service);

    /**
     * 根据服务名称获取服务实体
     *
     * @param serviceName 服务名称
     * @return 服务实体
     */
    Object getServiceProvider(String serviceName);
}
