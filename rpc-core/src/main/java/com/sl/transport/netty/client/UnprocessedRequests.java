package com.sl.transport.netty.client;

import entity.RpcResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yazai
 * Date: 下午2:23 2021/7/7
 * Description: Netty客户端使用CompletableFuture接收返回结果，优化Netty客户端连接逻辑
 */
public class UnprocessedRequests {

    private static ConcurrentHashMap<String, CompletableFuture<RpcResponse>> unprocessedResponseFutures = new ConcurrentHashMap<>();

    public void put(String requestId, CompletableFuture<RpcResponse> future) {
        unprocessedResponseFutures.put(requestId, future);
    }

    public void remove(String requestId) {
        unprocessedResponseFutures.remove(requestId);
    }

    public void complete(RpcResponse rpcResponse) {
        CompletableFuture<RpcResponse> future = unprocessedResponseFutures.remove(rpcResponse.getRequestId());
        if (null != future) {
            future.complete(rpcResponse);
        } else {
            throw new IllegalStateException();
        }
    }

}
