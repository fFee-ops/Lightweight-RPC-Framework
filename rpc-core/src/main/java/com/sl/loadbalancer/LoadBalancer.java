package com.sl.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * Created by yazai
 * Date: 上午11:57 2021/7/7
 * Description:
 */
public interface LoadBalancer {

    Instance select(List<Instance> instances);

}