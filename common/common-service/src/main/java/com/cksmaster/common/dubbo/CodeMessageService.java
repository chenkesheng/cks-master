package com.cksmaster.common.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.common.mapper.CodeMessageMapper;
import com.cksmaster.core.utils.CollectionUtil;
import com.cksmaster.core.utils.Page;
import com.google.common.collect.ImmutableMap;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 错误码对应的消息工具类
 *
 * @author cks
 * @Date 2017/7/21.
 */
@Service(interfaceClass = ICodeMessageService.class)
@Component
public class CodeMessageService implements ICodeMessageService {

    @Autowired
    DataSource dataSource;

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
    @Async
    @Compensable(confirmMethod = "insertCodeMessage", cancelMethod = "updateCodeMessage")
    public Page<CodeMessage> findPage(Page<CodeMessage> page) throws InterruptedException {

        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10000);
        if (CollectionUtil.notEmpty(codeMessageMapper.findCodeMessage(page))) {
            page.setResult(codeMessageMapper.findCodeMessage(page));
            page.setTotal(codeMessageMapper.total());
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("page任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
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

    @Override
    public void updateCodeMessage(CodeMessage codeMessage) {
        codeMessageMapper.update(codeMessage);
        CodeMessage codeMessage1 = codeMessageMapper.findById(codeMessage.getId());
        System.out.println("===================================" + codeMessage1.getKey());
    }

    @Async
    @Override
    public List<CodeMessage> findAll() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10000);
        List<CodeMessage> codeMessages = codeMessageMapper.findAll();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("findAll任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return codeMessages;
    }

    @Async
    @Override
    public Future<List<CodeMessage>> findAlls() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10000);
        List<CodeMessage> codeMessages = codeMessageMapper.findAll();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("findAll任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<List<CodeMessage>>(codeMessages);
    }

    @Async
    @Override
    public CodeMessage findById(Integer id) {
        long currentTimeMillis = System.currentTimeMillis();
        CodeMessage codeMessage = codeMessageMapper.findById(id);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("findById任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return codeMessage;
    }
}
