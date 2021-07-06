package test;

import com.sl.api.HelloService;
import com.sl.registry.ServiceRegistry;
import com.sl.serializer.HessianSerializer;
import com.sl.transport.socket.server.SocketServer;

/**
 * Created by yazai
 * Date: 下午4:17 2021/7/4
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketServer socketServer = new SocketServer("127.0.0.1", 9998);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.publishService(helloService, HelloService.class);
    }
}
