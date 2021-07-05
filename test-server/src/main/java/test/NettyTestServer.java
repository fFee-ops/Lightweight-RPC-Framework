package test;

import com.sl.api.HelloService;
import com.sl.netty.server.NettyServer;
import com.sl.registry.DefaultServiceRegistry;
import com.sl.registry.ServiceRegistry;

/**
 * Created by yazai
 * Date: 21:52 2021/7/5
 * Description:
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(9999);
    }
}
