package com.cksmaster.common.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.common.dubbo.IServerExceptionLogService;
import com.cksmaster.common.entity.ServerExceptionLog;
import com.cksmaster.common.mapper.ServerExceptionLogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 服务端异常日志
 *
 * @author cks
 * @Date 2017/7/22.
 */
@Service
public class ServerExceptionLogService implements IServerExceptionLogService {

    @Autowired
    private ServerExceptionLogMapper serverExceptionLogMapper;

    @Override
    public void addServerExceptionLog(String ex, int sys, Long uid, String uri, String method,
                                      String requestParams, String ua, String ip) {
        ServerExceptionLog log = new ServerExceptionLog();
        log.setException(ex);
        log.setSys(sys);
        log.setRequestUri(uri);
        log.setRequestMethod(method);
        log.setRequestParams(requestParams);
        log.setUa(ua);
        log.setIp(ip);
        log.setAddTime(new Date());
        log.setStatus(ServerExceptionLog.STATUS_NO);
        log.setUserId(uid);
        serverExceptionLogMapper.insert(log);
    }
}
