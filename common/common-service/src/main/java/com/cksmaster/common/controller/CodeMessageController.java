package com.cksmaster.common.controller;

import com.cksmaster.common.dubbo.service.CodeMessageService;
import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.annotation.NotLogin;
import com.cksmaster.core.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "find-page", method = RequestMethod.GET)
    public Page<CodeMessage> findPage(Page<CodeMessage> page) {
        page.setPageNum(2);
        page.setPageSize(2);
        return codeMessageService.findPage(page);
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
    public void update(CodeMessage codeMessage){
        codeMessageService.updateCodeMessage(codeMessage);
    }
}
