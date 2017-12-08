package com.cksmaster.common.dubbo;

/**
 * @author cks
 * @Date 2017/7/22.
 */
public interface IServerExceptionLogService {
    void addServerExceptionLog(String ex, int sys, Long uid, String uri, String method,
                               String requestParams, String ua, String ip);
}
