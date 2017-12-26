package com.cksmaster.user.controller;

import com.cksmaster.common.dubbo.ICodeMessageService;
import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.annotation.NotLogin;
import com.cksmaster.core.utils.Page;
import com.cksmaster.user.dubbo.UserService;
import com.cksmaster.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cks
 * @Date 2017/7/19.
 */
@RequestMapping("/")
@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;
    @Autowired
    private ICodeMessageService codeMessageService;

    /**
     * 创建用户(注册)
     *
     * @param user
     */
    @NotLogin
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addUser(User user) {
        userService.add(user);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @NotLogin
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object[] login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        return userService.login(username, password, request);
    }

    /**
     * 查询错误码
     *
     * @param page
     * @return
     */
    @NotLogin
    @RequestMapping(value = "find-page", method = RequestMethod.GET)
    public Page<CodeMessage> find(Page<CodeMessage> page) {
        return codeMessageService.findPage(page);
    }

    @NotLogin
    @RequestMapping(value = "find-all", method = RequestMethod.GET)
    public List<CodeMessage> findAll() {
        return codeMessageService.findAll();
    }
}
