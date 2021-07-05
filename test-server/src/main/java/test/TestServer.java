package test;

import com.sl.api.HelloService;
import com.sl.registry.DefaultServiceRegistry;
import com.sl.registry.ServiceRegistry;
import com.sl.socket.server.SocketServer;

/**
 * Created by yazai
 * Date: 下午4:17 2021/7/4
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }
}
