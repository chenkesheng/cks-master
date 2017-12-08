package com.cksmaster.user.mapper;

import com.cksmaster.core.entity.UserToken;

/**
 * @author cks
 * @Date 2017/7/20.
 */
public interface UserTokenMapper {
    void insertUserToken(UserToken userToken);

    UserToken findByToken(String token);
}
