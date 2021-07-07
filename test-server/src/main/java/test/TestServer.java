package test;

import com.sl.annotation.ServiceScan;
import com.sl.api.HelloService;
import com.sl.registry.ServiceRegistry;
import com.sl.serializer.CommonSerializer;
import com.sl.serializer.HessianSerializer;
import com.sl.transport.RpcServer;
import com.sl.transport.socket.server.SocketServer;

/**
 * Created by yazai
 * Date: 下午4:17 2021/7/4
 */
@ServiceScan
public class TestServer {

    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.HESSIAN_SERIALIZER);
        server.start();
    }

}