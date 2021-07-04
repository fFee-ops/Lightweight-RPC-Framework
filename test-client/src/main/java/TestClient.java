import client.RpcClientProxy;
import com.sl.api.HelloObject;
import com.sl.api.HelloService;

/**
 * Created by yazai
 * Date: 下午3:48 2021/7/4
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "这是我的测试讯息✉️");
        String res=helloService.hello(object);
        System.out.println(res);
    }
}
