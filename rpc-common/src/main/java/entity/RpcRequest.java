package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yazai
 * Date: 下午2:16 2021/7/4
 * 消费者向提供者发送的请求对象：也就是说服务端根据什么才能唯一确定需要调用的接口的方法
 */
@Data
@AllArgsConstructor
@Builder
public class RpcRequest implements Serializable {
    public RpcRequest() {}

    /**
     * 请求号
     * 为每个请求加上了请求号，客户端在调用后会检查响应与请求的请求号
     */
    private String requestId;
    /**
     * 待调用接口名称
     */
    private String interfaceName;

    /**
     * 待调用方法名称
     */
    private String methodName;

    /**
     * 调用方法的参数
     */
    private Object[] parameters;

    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;

    /**
     * 是否是心跳包
     */
    private Boolean heartBeat;

}
