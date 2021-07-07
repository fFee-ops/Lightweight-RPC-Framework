package com.sl.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * Created by yazai
 * Date: 上午11:59 2021/7/7
 * Description: 轮询
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private int index = 0;

    @Override
    public Instance select(List<Instance> instances) {
        if(index >= instances.size()) {
            index %= instances.size();
        }
        return instances.get(index++);
    }

}