package com.cksmaster.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cksmaster.core.utils.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author cks
 * @Date 2017/7/20.
 */
public class UserSystemContext implements Serializable {
    /**
     * 请求
     */
    @JSONField(serialize = false)
    private HttpServletRequest request;
    /**
     * 响应
     */
    @JSONField(serialize = false)
    private HttpServletResponse response;
    /**
     * 请求地址
     */
    private String matchedPath;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 医院id
     */
    private Integer orgId;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 应用版本
     */
    private Integer version;
    /**
     * 终端类型
     */
    private Integer terminal;
    /**
     * IP
     */
    private String ip;
    /**
     * UA
     */
    private String ua;
    /**
     * 是否进行登录
     */
    private boolean doLogin = false;
    /**
     * 客户端系统 1ios 2android
     */
    private Integer clientSystem;
    /**
     * 系统类型
     */
    private Integer systemType;
    /**
     * 服务端接收时间，单位：毫秒
     */
    private Long serverReceiveTime;

    /**
     * 用户请求时的Context
     */
    public UserSystemContext() {
    }

    /**
     * 用户请求时的Context
     *
     * @param request     请求
     * @param response    响应
     * @param matchedPath 匹配路径
     */
    public UserSystemContext(HttpServletRequest request, HttpServletResponse response, String matchedPath) {
        this.request = request;
        this.response = response;
        this.matchedPath = matchedPath;
        if (ip == null) {
            ip = HttpUtil.getIP(request);
        }
        if (ua == null) {
            ua = HttpUtil.getUA(request);
        }
        serverReceiveTime = System.currentTimeMillis();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getMatchedPath() {
        return matchedPath;
    }

    public void setMatchedPath(String matchedPath) {
        this.matchedPath = matchedPath;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public boolean isDoLogin() {
        return doLogin;
    }

    public void setDoLogin(boolean doLogin) {
        this.doLogin = doLogin;
    }

    public Integer getClientSystem() {
        return clientSystem;
    }

    public void setClientSystem(Integer clientSystem) {
        this.clientSystem = clientSystem;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Long getServerReceiveTime() {
        return serverReceiveTime;
    }

    public void setServerReceiveTime(Long serverReceiveTime) {
        this.serverReceiveTime = serverReceiveTime;
    }
}
