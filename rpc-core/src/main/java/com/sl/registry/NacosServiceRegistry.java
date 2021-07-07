package com.sl.registry;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.NacosUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by yazai
 * Date: 22:26 2021/7/6
 * Description: Nacos服务注册中心
 */
public class NacosServiceRegistry implements ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);

    /**
     * 可以直接向 Nacos 注册服务
     *
     * @param serviceName       服务名称
     * @param inetSocketAddress 提供服务的地址
     */
    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        try {
            NacosUtil.registerService(serviceName, inetSocketAddress);
            logger.info("向nacos注册服务成功");
        } catch (NacosException e) {
            logger.error("注册服务时有错误发生:", e);
            throw new RpcException(RpcError.REGISTER_SERVICE_FAILED);
        }
    }


}
