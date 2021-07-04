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

}
