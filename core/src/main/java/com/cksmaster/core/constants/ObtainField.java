package com.cksmaster.core.constants;

import com.cksmaster.core.annotation.OperateLogMeta;

import java.lang.reflect.Field;

/**
 * 反射获取操作日志元注解辅助类
 *
 * @author cks
 * @Date 2017/8/14.
 */
public class ObtainField {
    public ObtainField() {
    }

    public ObtainField(OperateLogMeta meta, Field field) {
        super();
        this.meta = meta;
        this.field = field;
        this.name = field.getName();
        this.type = field.getType();
    }


    public ObtainField(OperateLogMeta meta, String name, Class<?> type) {
        super();
        this.meta = meta;
        this.name = name;
        this.type = type;
    }


    private OperateLogMeta meta;
    private Field field;
    private String name;
    private Class<?> type;

    public OperateLogMeta getMeta() {
        return meta;
    }

    public void setMeta(OperateLogMeta meta) {
        this.meta = meta;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
