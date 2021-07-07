package test;

import com.sl.annotation.Service;
import com.sl.api.HelloObject;
import com.sl.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yazai
 * Date: 下午4:20 2021/7/4
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到消息：{}", object.getMessage());
        return "这是Impl1方法";
    }

}