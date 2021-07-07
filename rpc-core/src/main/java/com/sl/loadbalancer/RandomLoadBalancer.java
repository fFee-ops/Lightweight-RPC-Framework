package com.sl.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Random;

/**
 * Created by yazai
 * Date: 上午11:58 2021/7/7
 * Description: 随机
 */
public class RandomLoadBalancer implements LoadBalancer {
    @Override
    public Instance select(List<Instance> instances) {
        return instances.get(new Random().nextInt(instances.size()));
    }
}
