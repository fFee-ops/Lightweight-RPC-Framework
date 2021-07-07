package com.sl.transport;

import com.sl.serializer.CommonSerializer;
import entity.RpcRequest;

/**
 * Created by yazai
 * Date: 20:40 2021/7/5
 * Description: 客户端类通用接口
 */
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);
}
