package exception;

import enumeration.RpcError;

/**
 * Created by yazai
 * Date: 22:42 2021/7/4
 * Description:RPC调用异常
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}
