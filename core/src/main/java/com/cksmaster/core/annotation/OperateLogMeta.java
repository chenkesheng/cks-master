package com.cksmaster.core.annotation;

import java.lang.annotation.*;

/**
 * 元注解类
 *
 * @author cks
 * @Date 2017/8/14.
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface OperateLogMeta {
    /**
     * 字段名称
     *
     * @return
     */
    String name() default "";
}
