package entity;

import enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yazai
 * Date: 下午2:24 2021/7/4
 * 提供者执行完成或出错后向消费者返回的结果对象
 */
@Data
public class RpcResponse<T> implements Serializable {
    /**
     * 响应状态码
     */
    private Integer statusCode;

    /**
     * 响应状态补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setData(data);
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}