package com.sl.transport;

import com.sl.serializer.CommonSerializer;

/**
 * Created by yazai
 * Date: 20:41 2021/7/5
 * Description: 服务器类通用接口
 */
public interface RpcServer {


    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    /**
     * 用于向 Nacos 注册服务
     *
     * @param service
     * @param serviceName
     * @param <T>
     */
    <T> void publishService(T service, String serviceName);

}
