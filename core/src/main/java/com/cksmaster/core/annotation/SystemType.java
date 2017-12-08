package com.cksmaster.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 平台类型
 *
 * @author cks
 * @Date 2017/7/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemType {

    /**
     * 平台名
     */
    int value();
}
