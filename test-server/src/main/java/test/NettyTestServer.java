package test;

import com.sl.annotation.ServiceScan;
import com.sl.api.HelloService;
import com.sl.serializer.CommonSerializer;
import com.sl.serializer.ProtobufSerializer;
import com.sl.transport.RpcServer;
import com.sl.transport.netty.server.NettyServer;
import com.sl.registry.ServiceRegistry;

/**
 * Created by yazai
 * Date: 21:52 2021/7/5
 * Description:
 */
@ServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}
