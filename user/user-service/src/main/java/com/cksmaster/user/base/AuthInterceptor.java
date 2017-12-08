package com.cksmaster.user.base;

import com.cksmaster.core.entity.UserToken;
import com.cksmaster.core.interceptor.BaseAuthInterceptor;
import com.cksmaster.user.dubbo.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cks
 * @Date 2017/7/20.
 */
public class AuthInterceptor extends BaseAuthInterceptor {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public UserToken findUserToken(String token) {
        return userTokenService.findUserToken(token);
    }
}
