package com.sl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yazai
 * Date: 下午2:47 2021/7/7
 * Description: 服务扫描的基包：放在启动的入口类上
 *      扫描时会扫描该包及其子包下所有的类，找到标记有 Service 的类，并注册。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceScan {

    public String value() default "";

}
