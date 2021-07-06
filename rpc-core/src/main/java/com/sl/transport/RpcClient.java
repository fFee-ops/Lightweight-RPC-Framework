package com.sl.transport;

import com.sl.serializer.CommonSerializer;
import entity.RpcRequest;

/**
 * Created by yazai
 * Date: 20:40 2021/7/5
 * Description: 客户端类通用接口
 */
public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);

    /**
     * 设置序列化器
     * @param serializer
     */
    void setSerializer(CommonSerializer serializer);
}
