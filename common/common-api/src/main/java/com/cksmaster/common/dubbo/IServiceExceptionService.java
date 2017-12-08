package com.cksmaster.common.dubbo;

import com.cksmaster.core.entity.ServiceException;

/**
 * @author cks
 * @Date 2017/7/21.
 */
public interface IServiceExceptionService {
    /**
     * 返回业务异常
     *
     * @param code   错误码
     * @param params 消息格式化参数
     * @throws ServiceException
     */
    ServiceException createServiceException(int code, Object... params);
}
