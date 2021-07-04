package test;

import com.sl.api.HelloService;
import registry.DefaultServiceRegistry;
import registry.ServiceRegistry;
import server.RpcServer;

/**
 * Created by yazai
 * Date: 下午4:17 2021/7/4
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
