package com.cksmaster.common.mapper;

import com.cksmaster.common.entity.CodeMessage;
import com.cksmaster.core.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cks
 * @Date 2017/7/22.
 */
@Mapper
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
    @Transactional
    void update(CodeMessage codeMessage);

    /**
     * 删除错误码
     *
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * id查询错误码
     *
     * @param id
     * @return
     */
    CodeMessage findById(@Param("id") Integer id);

    /**
     * 查找所有
     *
     * @return
     */
    List<CodeMessage> findAll();
}
