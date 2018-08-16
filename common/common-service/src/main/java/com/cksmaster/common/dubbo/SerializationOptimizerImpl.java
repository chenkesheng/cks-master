package com.cksmaster.common.dubbo;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.cksmaster.common.entity.CodeMessage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: cks
 * @Date: Created by 10:25 2018/8/16
 * @Package: com.cksmaster.common.dubbo
 * @Description: 要完全发挥出kryo序列化的高性能，最好将那些需要被序列化的类注册到dubbo系统中
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<>();
        classes.add(CodeMessage.class);
        return classes;
    }
}
