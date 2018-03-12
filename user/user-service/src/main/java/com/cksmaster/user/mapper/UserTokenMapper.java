package com.cksmaster.user.mapper;

import com.cksmaster.core.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cks
 * @Date 2017/7/20.
 */
@Mapper
public interface UserTokenMapper {
    void insertUserToken(UserToken userToken);

    UserToken findByToken(String token);
}
