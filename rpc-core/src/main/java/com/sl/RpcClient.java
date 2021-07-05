package com.sl;

import entity.RpcRequest;

/**
 * Created by yazai
 * Date: 20:40 2021/7/5
 * Description: 客户端类通用接口
 */
public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
