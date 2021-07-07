package com.sl.transport;

import com.sl.annotation.Service;
import com.sl.annotation.ServiceScan;
import com.sl.provider.ServiceProvider;
import com.sl.registry.ServiceRegistry;
import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ReflectUtil;

import java.net.InetSocketAddress;
import java.util.Set;

/**
 * Created by yazai
 * Date: 下午2:34 2021/7/7
 * Description: 扫描服务
 */
public abstract class AbstractRpcServer implements RpcServer {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String host;
    protected int port;

    protected ServiceRegistry serviceRegistry;
    protected ServiceProvider serviceProvider;

    public void scanServices() {
        String mainClassName = ReflectUtil.getStackTrace();
        Class<?> startClass;
        try {
            startClass = Class.forName(mainClassName);

            //isAnnotationPresent：判断该类是否有 ServiceScan 注解
            if (!startClass.isAnnotationPresent(ServiceScan.class)) {
                logger.error("启动类缺少 @ServiceScan 注解");
                throw new RpcException(RpcError.SERVICE_SCAN_PACKAGE_NOT_FOUND);
            }
        } catch (ClassNotFoundException e) {
            logger.error("出现未知错误");
            throw new RpcException(RpcError.UNKNOWN_ERROR);
        }
        //获取注解的值    ：获得要扫描的包的范围，就需要获取到 ServiceScan 注解的值
        String basePackage = startClass.getAnnotation(ServiceScan.class).value();
        if ("".equals(basePackage)) {
            basePackage = mainClassName.substring(0, mainClassName.lastIndexOf("."));
        }

//        获取到所有的 Class
        Set<Class<?>> classSet = ReflectUtil.getClasses(basePackage);
        //逐个判断是否有 @Service 注解，如果有的话，通过反射创建该对象，并且调用 publishService 注册即可
        for (Class<?> clazz : classSet) {
            if (clazz.isAnnotationPresent(Service.class)) {
                String serviceName = clazz.getAnnotation(Service.class).name();
                Object obj;
                try {
                    obj = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    logger.error("创建 " + clazz + " 时有错误发生");
                    continue;
                }
                if ("".equals(serviceName)) {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> oneInterface : interfaces) {
                       //向nacos注册该服务
                        publishService(obj, oneInterface.getCanonicalName());
                    }
                } else {
                    publishService(obj, serviceName);
                }
            }
        }
    }

    @Override
    public <T> void publishService(T service, String serviceName) {
        serviceProvider.addServiceProvider(service, serviceName);
        serviceRegistry.register(serviceName, new InetSocketAddress(host, port));
    }

}

