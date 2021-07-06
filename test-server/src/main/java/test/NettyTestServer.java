package test;

import com.sl.api.HelloService;
import com.sl.serializer.ProtobufSerializer;
import com.sl.transport.netty.server.NettyServer;
import com.sl.registry.ServiceRegistry;

/**
 * Created by yazai
 * Date: 21:52 2021/7/5
 * Description:
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }
}
