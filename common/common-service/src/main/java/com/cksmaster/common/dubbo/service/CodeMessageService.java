package com.cksmaster.common.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.common.dubbo.ICodeMessageService;
import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.common.mapper.CodeMessageMapper;
import com.cksmaster.core.entity.UserToken;
import com.cksmaster.core.utils.CollectionUtil;
import com.cksmaster.core.utils.Page;
import com.cksmaster.user.dubbo.IUserTokenService;
import com.google.common.collect.ImmutableMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * 错误码对应的消息工具类
 *
 * @author cks
 * @Date 2017/7/21.
 */
@Service
public class CodeMessageService implements ICodeMessageService {

    @Autowired
    private CodeMessageMapper codeMessageMapper;

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(CodeMessageService.class);

    private static Map<Integer, String> newCodeMap = ImmutableMap.<Integer, String>builder().build();

    public String formatWithoutSys(int code, Object... params) {
        String messagePattern = newCodeMap.get(code);
        if (messagePattern == null) {
            logger.error("[format][错误码({})没有对应的错误消息配置", code);
            return "";
        }
        return doFormat(code, messagePattern, params);
    }

    /**
     * 将错误编号对应的消息使用params进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
    private String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                logger.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sbuf.append(messagePattern.substring(i, messagePattern.length()));
                    return sbuf.toString();
                }
            } else {
                sbuf.append(messagePattern.substring(i, j));
                sbuf.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            logger.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        sbuf.append(messagePattern.substring(i, messagePattern.length()));
        return sbuf.toString();
    }

    /**
     * 获取错误码列表
     *
     * @param page
     * @return
     */
    public Page<CodeMessage> findPage(Page<CodeMessage> page) {
        if (CollectionUtil.notEmpty(codeMessageMapper.findCodeMessage(page))) {
            page.setResult(codeMessageMapper.findCodeMessage(page));
            page.setTotal(codeMessageMapper.total());
        }
        return page;
    }

    /**
     * 新增错误码
     *
     * @param codeMessage
     */
    @Override
    public void insertCodeMessage(CodeMessage codeMessage) {
        codeMessage.setStatus(CodeMessage.STATUS_OPEN);
        codeMessage.setCreateTime(new Date());
        codeMessageMapper.insert(codeMessage);
    }
}
