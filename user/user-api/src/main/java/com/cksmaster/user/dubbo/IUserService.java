package com.cksmaster.user.dubbo;

import com.cksmaster.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cks
 * @Date 2017/7/19.
 */
public interface IUserService {
    void add(User user);
    Object[] login(String username,
                   String password,
                   HttpServletRequest request);
}
