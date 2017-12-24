package com.cksmaster.core.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.entity.UserSystemContext;

/**
 * 消费者context filter
 * Author: liuhuan
 * Date:  17/1/18 上午10:28
 */
//public class ContextConsumerFilter implements Filter {
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        UserSystemContext context = SystemContextHolder.getUserContext();
//        if (context != null) {
//            RpcContext.getContext().setAttachment("UserSystemContext", JSON.toJSONString(context) );
//        }
//        return invoker.invoke(invocation);
//    }
//}
