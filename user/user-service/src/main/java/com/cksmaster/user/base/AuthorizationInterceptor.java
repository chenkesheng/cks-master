package com.cksmaster.user.base;

import com.cksmaster.core.constants.Constants;
import com.cksmaster.core.context.SystemContextHolder;
import com.cksmaster.core.entity.UserSystemContext;
import com.cksmaster.core.interceptor.BaseAuthorizationInterceptor;

/**
 * 基础授权拦截器
 * Author: cks
 * Date:  17/7/9 下午3:44
 */

public class AuthorizationInterceptor extends BaseAuthorizationInterceptor {

    @Override
    public void workshopPermissionCheckHandler(String uri) throws Exception {
        //查询用户所拥有的url
        UserSystemContext userSystemContext = SystemContextHolder.getUserContext();
        switch (userSystemContext.getSystemType()) {
            case Constants.SYSTEM_PLATFORM_WORKSHOP:
                break;
            case Constants.SYSTEM_HOSPITAL_WORKSHOP:
//                if (!BeanManager.getBean(PermissionService.class).checkPermission(Constants.SYSTEM_HOSPITAL_WORKSHOP, uri)) {
//                    throw new RequestUnauthorizedException();
//                }
                break;
        }
    }

}
