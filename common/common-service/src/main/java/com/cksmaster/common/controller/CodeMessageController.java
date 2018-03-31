package com.cksmaster.common.controller;

import com.cksmaster.common.dubbo.CodeMessageService;
import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.annotation.NotLogin;
import com.cksmaster.core.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 错误码Controller
 *
 * @author cks
 * @Date 2017/8/14.
 */
@RestController
@RequestMapping("code/message")
public class CodeMessageController {
    @Autowired
    private CodeMessageService codeMessageService;

    /**
     * 错误码列表
     *
     * @param page
     * @return
     */
    @NotLogin
    @RequestMapping(value = "find-page", method = RequestMethod.GET)
    public Page<CodeMessage> findPage(Page<CodeMessage> page) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        Page<CodeMessage> codeMessagePage = codeMessageService.findPage(page);
        Future<String> future = (Future<String>) codeMessageService.findAll();
        System.out.println(future + "异步执行test");
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("find任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return codeMessagePage;
    }

    @NotLogin
    @RequestMapping(value = "find-all", method = RequestMethod.GET)
    public List<CodeMessage> findAll() throws Exception {
        return codeMessageService.findAll();
    }

    /**
     * 新增错误码
     *
     * @param codeMessage
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @NotLogin
    public void insertCodeMessage(CodeMessage codeMessage) {
        codeMessageService.insertCodeMessage(codeMessage);
    }

    @NotLogin
    @RequestMapping(value = "update-code", method = RequestMethod.POST)
    public void update(CodeMessage codeMessage) {
        codeMessageService.updateCodeMessage(codeMessage);
    }
}
