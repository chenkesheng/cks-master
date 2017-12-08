package com.cksmaster.common.mapper;


import com.cksmaster.common.entity.ServerExceptionLog;

/**
 * 服务端异常日志
 *
 * @author cks
 * @Date 2017/7/22.
 */
public interface ServerExceptionLogMapper {
    int insert(ServerExceptionLog serverExceptionLog);
}
