package server;

import entity.RpcRequest;
import entity.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yazai
 * Date: 23:01 2021/7/4
 * Description:处理RpcRequest的工作线程，真正的业务逻辑是由RequestHandler去处理的
 * 这里只负责处理线程，接受对象等
 */
public class RequestHandlerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {


            //解析出rpcRequest
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            //获得服务对象
            Object service = serviceRegistry.getService(interfaceName);
            //真正进行逻辑处理的地方
            Object result = requestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();

        } catch (Exception e) {
            logger.error("调用或发送时有错误发生：", e);
        }

    }
}
