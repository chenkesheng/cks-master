package com.cksmaster.core.interceptor;


import com.cksmaster.core.annotation.NotLogin;
import com.cksmaster.core.constants.Constants;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.entity.UserSystemContext;
import com.cksmaster.core.exception.UserNotLoginException;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础授权拦截器
 * Author: cks
 * Date:  17/7/9 下午3:44
 */

public abstract class BaseAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        UserSystemContext userSystemContext = SystemContextHolder.getUserContext();
        switch (userSystemContext.getSystemType()) {
            case Constants.SYSTEM_PLATFORM_WORKSHOP:
                if (whetherLogin((HandlerMethod)o) && !userSystemContext.isDoLogin()) {
                    throw new UserNotLoginException();
                }
                break;
            case Constants.SYSTEM_HOSPITAL_WORKSHOP:
                if (whetherLogin((HandlerMethod)o)) {
                    if (!userSystemContext.isDoLogin()) {
                        throw new UserNotLoginException();
                    } else {
                        workshopPermissionCheckHandler(request.getRequestURI());
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    public abstract void workshopPermissionCheckHandler(String uri) throws Exception;

    /**
     * 获取是否需要登录
     * @return 是否需要登录
     */
    private boolean whetherLogin(HandlerMethod method) throws Exception {
        if (method.getBean() instanceof BasicErrorController) {
            throw new Exception();
        }
        boolean needLogin = true;
        NotLogin nl = method.getMethodAnnotation(NotLogin.class);
        if (nl != null) {
            needLogin = false;
        }
        return needLogin;
    }

}
