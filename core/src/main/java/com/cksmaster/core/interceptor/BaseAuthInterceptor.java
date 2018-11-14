package com.cksmaster.core.interceptor;


import com.cksmaster.core.constants.Constants;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.entity.UserSystemContext;
import com.cksmaster.core.entity.UserToken;
import com.cksmaster.core.exception.RequestNotFoundException;
import com.cksmaster.core.exception.UserNotLoginException;
import com.cksmaster.core.utils.StringUtil;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础认证拦截器
 *
 * @author cks
 * @Date 2017/7/13.
 */
public abstract class BaseAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        authorization(getSystemType((HandlerMethod) o), httpServletRequest, httpServletResponse);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public abstract UserToken findUserToken(String token);

    /**
     * 获取用户访问的接口支持系统类型
     *
     * @return 系统类型
     */
    private int getSystemType(HandlerMethod method) throws Exception {
        if (method.getBean() instanceof BasicErrorController) {
            throw new RequestNotFoundException();
        }
        int systemType = Constants.SYSTEM_PLATFORM_WORKSHOP;
//        SystemType system = method.getMethodAnnotation(SystemType.class);
//        if (system != null) {
//            return system.value();
//        }
        return systemType;
    }

    /**
     * 认证授权
     *
     * @param systemType 系统类型
     * @param request
     * @param response
     */
    private void authorization(Integer systemType, HttpServletRequest request, HttpServletResponse response) throws UserNotLoginException {
        String token = request.getParameter("token");
        UserSystemContext userSystemContext = new UserSystemContext(request, response, request.getRequestURI());
        Long uid = null;
        Integer orgId = null;
        if (!StringUtil.isEmpty(token) && !request.getRequestURI().equals("/user/login")) {
            UserToken userToken = findUserToken(token);
            if (userToken != null && userToken.getSystemType().equals(systemType)) {
                if (UserToken.STATUS_EFFECTIVE.equals(userToken.getStatus())) {
                    uid = userToken.getUid();
                } else {
                    throw new UserNotLoginException();
                }
            }
        }

        userSystemContext.setUid(uid);
        if (uid != null) {
            userSystemContext.setDoLogin(true);
        }
        userSystemContext.setOrgId(orgId);
        userSystemContext.setSystemType(systemType);
        //appId
//        userSystemContext.setAppId(appId);
        //线程数量
        SystemContextHolder.put(userSystemContext);
    }

}
