package com.cksmaster.user.base;

import com.cksmaster.common.dubbo.IServerExceptionLogService;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.interceptor.BaseGlobalExceptionHandler;
import com.cksmaster.core.utils.HttpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author cks
 * @Date 2017/7/22.
 */
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

    @Autowired
    private IServerExceptionLogService serverExceptionLogService;

    @Override
    public void addServerExceptionLog(HttpServletRequest request, Throwable throwable) {
        serverExceptionLogService.addServerExceptionLog(ExceptionUtils.getStackTrace(throwable),
                SystemContextHolder.getUserContext().getSystemType(), SystemContextHolder.getUserContext().getUid(),
                request.getRequestURI(), request.getMethod(), HttpUtil.buildQueryString(request),
                HttpUtil.getUA(request), HttpUtil.getIP(request));
    }
}
