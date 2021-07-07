package com.sl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yazai
 * Date: 下午2:47 2021/7/7
 * Description: 标识这个类提供一个服务，用于远程接口的实现类
 * @Service 注解的值定义为该服务的名称，默认值是该类的完整类名
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    public String name() default "";

}
