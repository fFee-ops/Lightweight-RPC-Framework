package registry;

import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yazai
 * Date: 22:35 2021/7/4
 * Description:默认的服务注册表
 */
public class DefaultServiceRegistry implements ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);

    //存放了：服务名与提供服务的对象的对应关系
    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    //存放了：当前有哪些对象已经被注册
    //newKeySet()：创建一个由ConcurrentHashMap支持的ConcurrentHashSet
    private final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public <T> void register(T service) {
        String serviceName = service.getClass().getCanonicalName();

        //已经被注册了，直接返回
        if (registeredService.contains(serviceName)) {
            return;
        }
        registeredService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if (interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        //把服务名和对象 放入到map中
        for (Class<?> i : interfaces) {
            serviceMap.put(i.getCanonicalName(), service);
        }
        logger.info("向接口: {} 注册服务: {}", interfaces, serviceName);
    }

    /**
     * 获得服务的对象就更简单了，直接去 Map 里查找就行了
     *
     * @param serviceName 服务名称
     * @return
     */
    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
