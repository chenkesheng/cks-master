package com.cksmaster.core.entity;

/**
 * 异常实体
 *
 * @author cks
 * @Date 2017/7/21.
 */
public class ServiceException extends RuntimeException {
    /**
     * 异常编码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServiceException(int code) {
        this.code = code;
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
