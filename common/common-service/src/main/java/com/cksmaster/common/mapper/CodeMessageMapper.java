package com.cksmaster.common.mapper;

import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cks
 * @Date 2017/7/22.
 */
public interface CodeMessageMapper {
    /**
     * 总条数
     *
     * @return
     */
    Long total();

    /**
     * 根据key查找错误码
     *
     * @param page
     * @return
     */
    List<CodeMessage> findCodeMessage(@Param("page") Page<CodeMessage> page);

    /**
     * 新增错误码
     *
     * @param codeMessage
     */
    void insert(CodeMessage codeMessage);

    /**
     * 修改错误码
     *
     * @param codeMessage
     */
    void update(CodeMessage codeMessage);

    /**
     * 删除错误码
     *
     * @param id
     */
    void delete(@Param("id") Integer id);
}
