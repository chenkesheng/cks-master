package com.cksmaster.core.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.entity.UserSystemContext;


/**
 * 生产者context filter
 * Author: liuhuan
 * Date:  17/1/18 上午10:28
 */
//public class ContextProviderFilter implements Filter {
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//
//        String UserSystemContextStr = RpcContext.getContext().getAttachment("UserSystemContext");
//        SystemContextHolder.put(JSON.parseObject(UserSystemContextStr, UserSystemContext.class));
//
////        String adminContextDTO = RpcContext.getContext().getAttachment("adminContextDTO");
////        SystemContextHolder.put(JSON.parseObject(adminContextDTO, AdminDTO.class));
//
//        return invoker.invoke(invocation);
//    }
//
//}
