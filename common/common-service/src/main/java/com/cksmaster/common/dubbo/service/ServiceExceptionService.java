package com.cksmaster.common.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.common.dubbo.ICodeMessageService;
import com.cksmaster.common.dubbo.IServiceExceptionService;
import com.cksmaster.core.entity.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author cks
 * @Date 2017/7/21.
 */
@Service
public class ServiceExceptionService implements IServiceExceptionService {

    @Autowired
    private ICodeMessageService codeMessageService;

    /**
     * 返回业务异常
     *
     * @param code   错误码
     * @param params 消息格式化参数
     * @return
     */
    @Override
    public ServiceException createServiceException(int code, Object... params) {
        ServiceException serviceException = new ServiceException(code);
        serviceException.setMsg(codeMessageService.formatWithoutSys(code, params));
        return serviceException;
    }

}
