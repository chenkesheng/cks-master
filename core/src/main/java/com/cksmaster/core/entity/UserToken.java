package com.cksmaster.core.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @author cks
 * @Date 2017/7/13.
 */
public class UserToken implements Serializable {

    /**
     * 状态 - 有效
     */
    public static final Integer STATUS_EFFECTIVE = 1;
    /**
     * 状态 - 无效
     */
    public static final Integer STATUS_UN_EFFECTIVE = 2;

    /**
     * token值
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 状态 1 有效 2 无效
     */
    private Boolean status;
    /**
     * 失效时间
     */
    private Date expiryTime;
    /**
     * 系统类型
     */
    private Integer systemType;
    /**
     * 失效类型
     */
    private Integer expiryType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Integer getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Integer expiryType) {
        this.expiryType = expiryType;
    }
}
