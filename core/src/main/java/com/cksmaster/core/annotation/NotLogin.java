package com.cksmaster.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要登录
 * BaseAuthorizationInterceptor
 *
 * @author cks
 * @Date 2017/8/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotLogin {
}
