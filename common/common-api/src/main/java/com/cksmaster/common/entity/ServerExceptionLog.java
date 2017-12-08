package com.cksmaster.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cks
 * @Date 2017/7/22.
 */
public class ServerExceptionLog implements Serializable{

    /**
     * 处理状态 - 已处理
     */
    public static int STATUS_YES = 1;
    /**
     * 处理状态 - 未处理
     */
    public static int STATUS_NO = 0;

    private Integer id;
    /**
     * 异常
     */
    private String exception;
    /**
     * 匹配地址
     */
    private String matchedPath;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 系统类型
     */
    private Integer sys;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * UA
     */
    private String ua;
    /**
     * IP
     */
    private String ip;
    /**
     * 记录时间
     */
    private Date addTime;
    /**
     * 处理状态
     */
    private Integer status;

    /**邮件发送状态
     * */
    private  Integer sendMailStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMatchedPath() {
        return matchedPath;
    }

    public void setMatchedPath(String matchedPath) {
        this.matchedPath = matchedPath;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSendMailStatus() {
        return sendMailStatus;
    }

    public void setSendMailStatus(Integer sendMailStatus) {
        this.sendMailStatus = sendMailStatus;
    }
}
