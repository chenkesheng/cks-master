package com.cksmaster.common.entity;

import java.util.Date;

/**
 * 错误码实体
 *
 * @author cks
 * @Date 2017/7/21.
 */
public class CodeMessage {
    /**
     * 状态2为删除
     */
    public static final Integer STATUS_DELETE = 2;
    /**
     * 标记为1在使用中
     */
    public static final Integer STATUS_OPEN = 1;

    private Integer id;
    /**
     * 值
     */
    private Integer key;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 备注
     */
    private String memo;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
