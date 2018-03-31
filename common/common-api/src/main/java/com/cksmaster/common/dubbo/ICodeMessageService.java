package com.cksmaster.common.dubbo;

import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.utils.Page;

import java.util.List;

/**
 * @author cks
 * @Date 2017/7/21.
 */
public interface ICodeMessageService {
    /**
     * 将错误编号对应的消息使用params进行格式化。
     * 具体规则为：我有{}只小毛驴，我重来也不{}.这里拓展参数可以使用{1, "打"}。
     * 具体类似SLF4J打印日志的形式
     *
     * @param code   错误编号
     * @param params 参数
     * @return 格式化后的提示
     */
    String formatWithoutSys(int code, Object... params);

    /**
     * 错误码列表
     *
     * @param page
     * @return
     */
    Page<CodeMessage> findPage(Page<CodeMessage> page);

    /**
     * 新增错误码
     *
     * @param codeMessage
     */
    void insertCodeMessage(CodeMessage codeMessage);

    /**
     * 更新错误码
     */
    void updateCodeMessage(CodeMessage codeMessage);

    /**
     * 查找所有错误码
     *
     * @return
     */
    List<CodeMessage>  findAll() throws Exception;

    CodeMessage findById(Integer id) throws Exception;
}
