package com.cksmaster.core.entity;

/**
 * 返回实体
 *
 * @author cks
 * @Date 2017/7/22.
 */
public class ResultBody {
    private int code;

    private Object data;

    private String msg;

    /**
     * 堆栈信息, 开发阶段返回
     */
    private Object stack;


    public ResultBody() {
    }

    public ResultBody(Object data) {
        this.code = 0;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getStack() {
        return stack;
    }

    public void setStack(Object stack) {
        this.stack = stack;
    }
}
