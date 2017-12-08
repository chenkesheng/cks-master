package com.cksmaster.user.dubbo;

import com.cksmaster.core.entity.UserToken;

/**
 * @author cks
 * @Date 2017/7/20.
 */
public interface IUserTokenService {

    UserToken findUserToken(String token);

    /**
     * 生成token
     * @param userToken
     */
    void inserToken(UserToken userToken);
}
