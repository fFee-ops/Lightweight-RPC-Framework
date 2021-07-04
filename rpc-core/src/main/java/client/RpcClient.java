package client;

import entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yazai
 * Date: 下午2:37 2021/7/4
 * 远程方法调用的消费者（客户端）
 */
public class RpcClient {
    private final static Logger logger = LoggerFactory.getLogger(RpcClient.class);

    /**
     * 作用就是将一个对象发过去，并且接受返回的对象。
     *
     * @param rpcRequest
     * @param host
     * @param port
     * @return
     */
    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            return objectInputStream.readObject();
        } catch (Exception e) {
            logger.error("调用发生了错误", e);
            return null;
        }
    }
}
