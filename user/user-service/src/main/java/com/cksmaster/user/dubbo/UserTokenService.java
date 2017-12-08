package com.cksmaster.user.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.core.entity.UserToken;
import com.cksmaster.core.utils.CommonUtil;
import com.cksmaster.user.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author cks
 * @Date 2017/7/20.
 */
@Service(version = "1.0.0")
public class UserTokenService implements IUserTokenService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 根据token查找用户token信息
     *
     * @param token
     * @return
     */
    @Override
    public UserToken findUserToken(String token) {
        return userTokenMapper.findByToken(token);
    }

    /**
     * 生成token
     *
     * @param userToken
     */
    @Override
    public void inserToken(UserToken userToken) {
        userToken.setToken(CommonUtil.genUUID(false));
        userToken.setCreateTime(new Date());
        userToken.setStatus(true);
        userToken.setExpiryTime(new Date());
        userTokenMapper.insertUserToken(userToken);
    }
}
