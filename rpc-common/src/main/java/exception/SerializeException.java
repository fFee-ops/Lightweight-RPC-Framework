package exception;

/**
 * Created by yazai
 * Date: 22:39 2021/7/5
 * Description: 序列化异常
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}