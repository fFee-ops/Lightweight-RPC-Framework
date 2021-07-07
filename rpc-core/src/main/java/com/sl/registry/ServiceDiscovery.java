package com.sl.registry;

import java.net.InetSocketAddress;

/**
 * Created by yazai
 * Date: 下午2:03 2021/7/7
 * Description:     服务发现接口
 */
public interface ServiceDiscovery {
    /**
     * 根据服务名称查找服务实体
     *
     * @param serviceName 服务名称
     * @return 服务实体的地址
     */
    InetSocketAddress lookupService(String serviceName);
}
