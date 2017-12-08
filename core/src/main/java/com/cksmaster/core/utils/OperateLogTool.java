package com.cksmaster.core.utils;

import com.cksmaster.core.constants.ObtainField;
import com.cksmaster.core.annotation.OperateLogMeta;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志工具类
 *
 * @author cks
 * @Date 2017/8/14.
 */
public final class OperateLogTool {

    private List<Object[]> diffs;
    private static final String DIFF_TIP = "将%s从[%s]改成[%s]";
    private static final String DIFF_EMPTY_TIP = "未改变";

    private List<Object[]> adds;
    private static final String ADD_TIP = "设置%s为[%s]";
    private static final String ADD_EMPTY_TIP = "未设置任何值";

    private List<Object[]> snaps;
    private static final String SNAP_TIP = "%s为[%s]";
    private static final String SNAP_EMPTY_TIP = "无";

    private OperateLogTool() {
    }

    /**
     * @return 产生用于比较值改变的工具类.常用于修改一条数据时
     */
    public static OperateLogTool diff() {
        OperateLogTool tool = new OperateLogTool();
        tool.diffs = new ArrayList<>();
        return tool;
    }

    /**
     * 操作日志  编辑内容数据处理
     *
     * @param oldBean
     * @param newBean
     * @return
     */
    // TODO from 芋艿 to 克圣：具体哪些fields，作为一个参数传递进来，不去比较所有的
    public OperateLogTool diff(Object oldBean, Object newBean) {
        if (oldBean != null) {
            /**返回类中所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段
             * entity.getFields();只返回对象所表示的类或接口的所有可访问公共字段
             * 在class中getDeclaredFields()方法返回的都是所有访问权限的字段、方法等；可看API
             * */
            Class clazz = oldBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            ObtainField of = null;
            for (Field log : fields) {
                //获取字段中包含OperateLogMeta的注解
                OperateLogMeta meta = log.getAnnotation(OperateLogMeta.class);
                //FieldMeta meta =  FieldUtils.getField(clazz,log.getName()).getAnnotation(FieldMeta.class);
                if (meta != null) {
                    // TODO from 芋艿 to 克圣：下面简化成使用FieldUtils.getField().getAnnotation(FieldMeta.class)
                    of = new ObtainField(meta, log);
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(log.getName(), clazz);
                        Method getMethod = pd.getReadMethod();
                        Object o1 = getMethod.invoke(oldBean);
                        Object o2 = getMethod.invoke(newBean);
                        //避免空指针异常
                        // TODO from 芋艿 to 克圣：下面可以简化成，调用diff方法
                        String s1 = o1 == null ? "" : o1.toString();
                        String s2 = o2 == null ? "" : o2.toString();
//                        System.out.println("字段名称：" + log.getName() + "\t字段类型：" + log.getType() +
//                          "\t注解名称：" + log.getMeta().name());
                        if (!s1.equals(s2)) {
//                            str += "将" + of.getMeta().name() + "从:[" + o1 + "]改成:[" + o2 + "];";
//                            operateLog = prefix + str;
                            //diff(of.getMeta().name(),o1,o2);
                            diffs.add(new Object[]{of.getMeta().name(), o1, o2});
                        }

                    } catch (Exception e) {
//                        throw new Exception("");
                        e.printStackTrace();
                        // TODO from 芋艿 to 克圣：有异常，抛出
                    }
                }
            }
        }
        return this;
    }

    /**
     * 比较新旧值，若改变则加入diffs
     *
     * @param name     值的名称
     * @param oldValue 旧值
     * @param newValue 新值
     * @return self
     * @see ObjectUtils#notEqual(Object, Object)
     */
    public OperateLogTool diff(String name, Object oldValue, Object newValue) {
        if (ObjectUtils.notEqual(oldValue, newValue)) {
            diffs.add(new Object[]{name, oldValue, newValue});
        }
        return this;
    }

    /**
     * 比较新旧值对应的"是"/"否"，若改变则加入diffs
     *
     * @param name     值的名称
     * @param oldValue 旧值
     * @param newValue 新值
     * @return self
     */
    public OperateLogTool diffYesOrNo(String name, Integer oldValue, Integer newValue) {
        String oldStr = (oldValue == 1 ? "是" : "否");
        String newStr = (newValue == 1 ? "是" : "否");
        return diff(name, oldStr, newStr);
    }

    /**
     * 比较新旧值对应的"是"/"否"，若改变则加入diffs
     *
     * @param name     值的名称
     * @param oldValue 旧值
     * @param newValue 新值
     * @return self
     */
    public OperateLogTool diffYesOrNo(String name, Boolean oldValue, Boolean newValue) {
        String oldStr = (Boolean.TRUE.equals(oldValue) ? "是" : "否");
        String newStr = (Boolean.TRUE.equals(newValue) ? "是" : "否");
        return diff(name, oldStr, newStr);
    }


//    /**
//     * 比较新旧值对应的数据字典，若改变则加入diffs
//     *
//     * @param name 值的名称
//     * @param oldValue 旧值
//     * @param newValue 新值
//     * @param enumValue 枚举值
//     * @return self
//     */
//    public OperateLogTool diffDataDict(String name, Integer oldValue, Integer newValue, String enumValue) {
//        String oldStr = DataDictUtil.getDataDisplayName(enumValue, oldValue);
//        String newStr = DataDictUtil.getDataDisplayName(enumValue, newValue);
//        return diff(name, oldStr, newStr);
//    }

    /**
     * @return 产生用于添加值的工具类.常用于添加一条数据时
     */
    public static OperateLogTool add() {
        OperateLogTool tool = new OperateLogTool();
        tool.adds = new ArrayList<>();
        return tool;
    }

    /**
     * 操作日志 、 添加 、内容数据处理
     *
     * @param newBean
     * @return
     */
    // TODO from 芋艿 to 克圣：增加一个excludeFields，作为不记录日志的字段。
    public OperateLogTool add(Object newBean) {
        Class clazz = newBean.getClass();

        Field[] fields = newBean.getClass().getDeclaredFields();
        ObtainField of = null;
        for (Field log : fields) {
            //获取字段中包含OperateLogMeta的注解
            OperateLogMeta meta = log.getAnnotation(OperateLogMeta.class);
            if (meta != null) {
                of = new ObtainField(meta, log);
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(log.getName(), clazz);
                    Method getMethod = pd.getReadMethod();
                    Object o2 = getMethod.invoke(newBean);
                    //避免空指针异常
                    // TODO from 芋艿 to 克圣：直接调用add方法
//                    if (o2 != null) {
//                        add(of.getMeta().name(), o2);
//                    }
                    if (o2 != null) {
                        adds.add(new Object[]{of.getMeta().name(), o2});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    /**
     * 添加值
     *
     * @param name  值的名称
     * @param value 值
     * @return self
     */
    public OperateLogTool add(String name, Object value) {
        Assert.notNull(adds, "当前工具不为[添加类型]");
        adds.add(new Object[]{name, value});
        return this;
    }

    /**
     * 添加值(是/否）
     *
     * @param name  值的名称
     * @param value 值
     * @return self
     */
    public OperateLogTool addYesOrNo(String name, Integer value) {
        return add(name, value == 1 ? "是" : "否");
    }

//    /**
//     * 添加数据字典值
//     *
//     * @param name 值的名称
//     * @param value 新值
//     * @param enumValue 枚举值
//     * @return self
//     */
//    public AdminOperateLogTool addDataDict(String name, Integer value, String enumValue) {
//        String str = DataDictUtil.getDataDisplayName(enumValue, value);
//        return add(name, str);
//    }

    /**
     * @return 产生用于快照值的工具类.常用语删除一条数据时
     */
    public static OperateLogTool snap() {
        OperateLogTool tool = new OperateLogTool();
        tool.snaps = new ArrayList<>();
        return tool;
    }

    /**
     * 操作日志 、 删除 、内容数据处理
     *
     * @param newBean
     * @return
     */
    // TODO from 芋艿 to 克圣：剩余的一些，参考add、diff想想，有没可以优化的地方
    public OperateLogTool snap(Object newBean) {
        Class clazz = newBean.getClass();

        Field[] fields = newBean.getClass().getDeclaredFields();
        ObtainField of = null;
        for (Field log : fields) {
            //获取字段中包含fieldMeta的注解
            OperateLogMeta meta = log.getAnnotation(OperateLogMeta.class);
            if (meta != null) {
                of = new ObtainField(meta, log);
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(log.getName(), clazz);
                    Method getMethod = pd.getReadMethod();
                    Object o2 = getMethod.invoke(newBean);
                    //避免空指针异常
                    if (of.getMeta().name() != null) {
                        snaps.add(new Object[]{of.getMeta().name(), o2});
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    /**
     * 快照值
     *
     * @param name  值的名称
     * @param value 值
     * @return self
     */
    public OperateLogTool snap(String name, Object value) {
        Assert.notNull(snaps, "当前工具不为[快照类型]");
        snaps.add(new Object[]{name, value});
        return this;
    }

    /**
     * 创建提示语句。规则为[前缀 + "，" + 改变拼接 + "，" + 后缀 + "。"]
     *
     * @param prefixStr 前缀
     * @param suffixStr 后缀
     * @return 提示语句
     */
    public String build(String prefixStr, String suffixStr) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.hasText(prefixStr)) {
            sb.append(prefixStr);
        }
        if (adds != null || snaps != null) {
            List<Object[]> objz;
            String tip;
            String emptyTip;
            if (adds != null) {
                objz = adds;
                tip = ADD_TIP;
                emptyTip = ADD_EMPTY_TIP;
            } else {
                objz = snaps;
                tip = SNAP_TIP;
                emptyTip = SNAP_EMPTY_TIP;
            }
            if (objz.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("，");
                }
                sb.append(emptyTip);
            } else {
                for (Object[] objs : objz) {
                    if (sb.length() > 0) {
                        sb.append("，");
                    }
                    if (objs[1] == null) {
                        objs[1] = "{空}";
                    }
                    sb.append(String.format(tip, objs[0].toString(), objs[1].toString()));
                }
            }
        } else if (diffs != null) {
            if (diffs.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("，");
                }
                sb.append(DIFF_EMPTY_TIP);
            } else {
                for (Object[] objs : diffs) {
                    if (sb.length() > 0) {
                        sb.append("，");
                    }
                    if (objs[1] == null) {
                        objs[1] = "{空}";
                    }
                    if (objs[2] == null) {
                        objs[2] = "{空}";
                    }
                    sb.append(String.format(DIFF_TIP, objs[0].toString(), objs[1].toString(), objs[2].toString()));
                }
            }
        }
        if (StringUtils.hasText(suffixStr)) {
            if (sb.length() > 0) {
                sb.append("，");
            }
            sb.append(suffixStr);
        }
        sb.append("。");
        return sb.toString();
    }
}
