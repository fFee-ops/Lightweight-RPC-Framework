package test;

import com.sl.api.HelloService;
import server.RpcServer;

/**
 * Created by yazai
 * Date: 下午4:17 2021/7/4
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
