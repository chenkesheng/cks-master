package com.cksmaster.user.dubbo;


import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.core.constants.Constants;
import com.cksmaster.core.entity.UserToken;
import com.cksmaster.core.utils.BCryptUtil;
import com.cksmaster.core.utils.CommonUtil;
import com.cksmaster.core.utils.SHAUtils;
import com.cksmaster.user.entity.User;
import com.cksmaster.user.mapper.UserMapper;
import com.cksmaster.user.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author cks
 * @Date 2017/7/19.
 */
@Service(interfaceClass = IUserService.class)
@Component
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

//    @Autowired
//    public ICodeMessageService codeMessageService;

    @Override
    public void add(User user) {
        user.setPassword(BCryptUtil.hashpw(SHAUtils.SHA256(user.getPassword()), BCryptUtil.gensalt()));
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateLastTime(now);
        userMapper.insertUser(user);
    }

    /**
     * 记录用户token并添加到cookie
     *
     * @param uid 用户id
     *            //     * @param systemType 系统类型
     */
    public String addUserToken(Long uid) {

        UserToken userToken = new UserToken();
        userToken.setUid(uid);
        userToken.setToken(CommonUtil.genUUID(false));

        userToken.setCreateTime(new Date());
        userToken.setStatus(true);

        long currentTime = System.currentTimeMillis();
        currentTime += 30 * 60 * 1000;
        userToken.setExpiryTime(new Date(currentTime));
        // TODO 先写死后期优化
        userToken.setSystemType(Constants.SYSTEM_PLATFORM_WORKSHOP);
        userTokenMapper.insertUserToken(userToken);
        return userToken.getToken();
    }

    public Object[] login(String userName, String password, HttpServletRequest request) {

        User user = userMapper.findByUsername(userName);
        String token = addUserToken(Long.valueOf(user.getId()));
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return new Object[]{user, token};
    }

//    public void update(CodeMessage codeMessage) {
//        codeMessageService.updateCodeMessage(codeMessage);
//    }
}
