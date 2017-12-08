package com.cksmaster.core.interceptor;

import com.cksmaster.core.entity.ResultBody;
import com.cksmaster.core.entity.ServiceException;
import com.cksmaster.core.exception.ParamsErrorException;
import com.cksmaster.core.exception.RequestNotFoundException;
import com.cksmaster.core.exception.UnauthorizedException;
import com.cksmaster.core.exception.UserNotLoginException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author cks
 * @Date 2017/7/22.
 */
public abstract class BaseGlobalExceptionHandler {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(BaseGlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ResultBody resultBody = new ResultBody();
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            resultBody.setCode(serviceException.getCode());
            resultBody.setMsg(serviceException.getMsg());
        } else if (e instanceof UnauthorizedException) {
            resultBody.setCode(5);
            resultBody.setMsg("请求未授权");
        } else if (e instanceof RequestNotFoundException) {
            resultBody.setCode(4);
            resultBody.setMsg("请求未找到");
        } else if (e instanceof ParamsErrorException) {
            resultBody.setCode(3);
            resultBody.setMsg("参数不正确");
        } else if (e instanceof UserNotLoginException) {
            resultBody.setCode(2);
            resultBody.setMsg("用户未登录");
        } else {
            resultBody.setCode(1);
            resultBody.setMsg("系统异常");
            e.printStackTrace();
            resultBody.setStack(ExceptionUtils.getStackTrace(e));
            try {
                addServerExceptionLog(request, e);
            } catch (Exception ex) {
                logger.error("add server exception error {}", ExceptionUtils.getStackTrace(ex));
            }
        }
        return resultBody;
    }

    public abstract void addServerExceptionLog(HttpServletRequest request, Throwable throwable);
}
