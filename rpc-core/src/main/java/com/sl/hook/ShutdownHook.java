package com.sl.hook;

import factory.ThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.NacosUtil;

/**
 * Created by yazai
 * Date: 上午11:44 2021/7/7
 * Description: 钩子: 在某些事件发生后自动去调用的方法。那么我们只需要把注销服务的方法写到关闭系统的钩子方法里就行了
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        //Runtime 对象是 JVM 虚拟机的运行时环境
        //调用其 addShutdownHook 方法增加一个钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            NacosUtil.clearRegistry();
            ThreadPoolFactory.shutDownAll();
        }));
    }

}
