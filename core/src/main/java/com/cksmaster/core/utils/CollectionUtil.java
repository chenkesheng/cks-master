package com.cksmaster.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 集合工具类
 * User: yunai
 * Date: 13-9-11
 * Time: 下午4:36
 */
public class CollectionUtil {

    public static List<String> splitList(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>(0);
        }
        String[] strs = str.split(regex);
        List<String> list = new ArrayList<>(strs.length);
        Collections.addAll(list, strs);
        return list;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static List<Integer> convertList(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        String[] strs = str.split(regex);
        List<Integer> list = new ArrayList<>(strs.length);
        for (String s : strs) {
            list.add(Integer.valueOf(s.trim()));
        }
        return list;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static Set<Integer> convertSet(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        String[] strs = str.split(regex);
        Set<Integer> set = Sets.newHashSetWithExpectedSize(strs.length);
        for (String s : strs) {
            if (!StringUtil.isEmpty(s)) {
                set.add(Integer.valueOf(s));
            }
        }
        return set;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static Set<Long> convertSet2(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        String[] strs = str.split(regex);
        Set<Long> set = Sets.newHashSetWithExpectedSize(strs.length);
        for (String s : strs) {
            set.add(Long.valueOf(s));
        }
        return set;
    }

    /**
     * 将字符串转换为字符串集合
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return
     */
    public static Set<String> convertSet3(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        String[] strings = str.split(regex);
        Set<String> set = Sets.newHashSetWithExpectedSize(strings.length);
        for (String s : strings) {
            set.add(s);
        }
        return set;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static List<Long> convertList2(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        String[] strs = str.split(regex);
        List<Long> list = new ArrayList<>(strs.length);
        for (String s : strs) {
            list.add(Long.valueOf(s));
        }
        return list;
    }

    /**
     * 根据数组生成集合
     *
     * @param objs 集合数组
     * @param <T>  泛型
     * @return 集合
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... objs) {
        Set<T> set = Sets.newHashSetWithExpectedSize(objs.length);
        Collections.addAll(set, objs);
        return set;
    }

    @SafeVarargs
    public static <T> List<T> asArrayList(T... objs) {
        List<T> list = new ArrayList<>(objs.length);
        Collections.addAll(list, objs);
        return list;
    }

    /**
     * 创建指定属性成为一个集合，比如说将一个患者信息数组建立出一个患者编号集合
     *
     * @param objs     数组
     * @param clazz    类型
     * @param property 属性名
     * @param <T>      泛型
     * @return 指定属性的集合
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <T> Set<T> buildSet(List<?> objs, Class<T> clazz, String property) {
        if (isEmpty(objs)) {
            return Sets.newHashSetWithExpectedSize(0);
        }
        Set<T> results = Sets.newHashSetWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                T val = (T) field.get(obj);
                if (val == null) {
                    continue;
                }
                results.add(val);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性为KEY, objs的每个元素为值的Multimap的Map集合。
     *
     * @param objs     数组
     * @param keyClazz 值类型，即{@code property}的类型
     * @param valClazz 值类型
     * @param property 属性名
     * @param <K>      泛型
     * @param <V>      泛型
     * @return 指定属性的Map集合
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <K, V> Map<K, List<V>> buildMultimap(List<V> objs, Class<K> keyClazz, Class<V> valClazz,
                                                       String property) {
        if (isEmpty(objs)) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        Map<K, List<V>> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (V obj : objs) {
                K key = (K) field.get(obj);
                List<V> value = results.get(key);
                if (value == null) {
                    results.put(key, value = new ArrayList<>());
                }
                value.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性成为一个数组，比如说将一个患者信息数组建立出一个患者编号数组
     *
     * @param objs     数组
     * @param clazz    类型
     * @param property 属性名
     * @param <T>      泛型
     * @return 指定属性的数组
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <T> List<T> buildList(List<?> objs, Class<T> clazz, String property) {
        if (isEmpty(objs)) {
            return Lists.newArrayListWithExpectedSize(0);
        }
        List<T> results = new ArrayList<>(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                T val = (T) field.get(obj);
                if (val == null) {
                    continue;
                }
                results.add(val);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性为KEY, objs的每个元素为值的Multimap的Map集合。
     *
     * @param objs     数组
     * @param keyClazz 值类型，即{@code property}的类型
     * @param valClazz 值类型
     * @param property 属性名
     * @param <K>      泛型
     * @param <V>      泛型
     * @return 指定属性的Map集合
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <K, V> Map<K, V> buildMap(List<V> objs, Class<K> keyClazz, Class<V> valClazz,
                                            String property) {
        if (isEmpty(objs)) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        Map<K, V> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (V obj : objs) {
                K key = (K) field.get(obj);
                results.put(key, obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建数组里某个属性的次数
     *
     * @param objs     数组
     * @param keyClazz key的类
     * @param property key的属性名。当key对应的值为null是，不计数
     * @param <K>      key的类
     * @return 次数集合
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <K> Map<K, Integer> buildCountMap(List<?> objs, Class<K> keyClazz, String property) {
        if (isEmpty(objs)) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        Map<K, Integer> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                K val = (K) field.get(obj);
                if (val != null) {
                    incr(results, val);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建数组里某个属性为key，值属性为次数
     *
     * @param objs        数组
     * @param keyClazz    key的类
     * @param property    key的属性名。当key对应的值为null是，不计数
     * @param valProperty val的属性名
     * @param <K>         key的类
     * @return 次数集合
     */
    @SuppressWarnings("unchecked")
    // TODO 【code review】from 芋艿 to 欢欢：升级下，支持属性嵌套，例如说profile.xxx.yyy
    public static <K> Map<K, Integer> buildCountMap(List<?> objs, Class<K> keyClazz, String property, String valProperty) {
        if (isEmpty(objs)) {
            return Collections.emptyMap();
        }
        Map<K, Integer> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            Field field2 = getField(objs, valProperty);
            for (Object obj : objs) {
                K val = (K) field.get(obj);
                Object val2 = field2.get(obj);
                if (val != null && val2 != null) {
                    incr(results, val, ((Number) val2).intValue());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * @param objs     对象数组
     * @param property 属性
     * @return 对象元素里的指定属性Field, 并设置该field可以被访问
     */
    public static Field getField(List<?> objs, String property) {
        Field field = ReflectionUtils.findField(objs.get(0).getClass(), property);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * @param obj      对象
     * @param property 属性
     * @return 对象元素里的指定属性Field, 并设置该field可以被访问
     */
    public static Field getField(Object obj, String property) {
        Field field = ReflectionUtils.findField(obj.getClass(), property);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 提升计数
     *
     * @param counts 计数集合
     * @param key    key
     * @param <K>    泛型
     * @return 结果
     */
    public static <K> Integer incr(Map<K, Integer> counts, K key) {
        return incr(counts, key, 1);
    }

    public static <K> Integer incr(Map<K, Integer> counts, K key, int count) {
        Integer value = counts.get(key);
        if (value == null) {
            value = count;
        } else {
            value += count;
        }
        counts.put(key, value);
        return value;
    }

    /**
     * 校验集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 校验集合是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean notEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 从集合中获取随机一个元素
     *
     * @param collection 集合
     * @return 随机元素
     */
    public static <T> T getRandomElement(Collection<T> collection) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        Object element = collection.toArray()[new Random().nextInt(collection.size())];
        return (T) element;
    }

    /**
     * 拼接集合元素中对象指定field字符串
     *
     * @param collection 集合
     * @param fieldStr   field名
     * @param splitStr   分隔符号
     *                   集合{User[name=a], User[name=b], User[name=c]} field名name 分隔符号逗号 ===> a,b,c
     */
    public static String concatCollectionField(Collection collection, String fieldStr, String splitStr) {
        if (null == collection || collection.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Object[] array = collection.toArray();
        Field field = getField(array[0], fieldStr);
        try {
            for (int i = 0; i < array.length; i++) {
                Object element = array[i];
                Object value = field.get(element);
                if (i == array.length - 1) {
                    sb.append(value);
                } else {
                    sb.append(value).append(splitStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 去掉两个List中的交集
     *
     * @param list1
     * @param list2
     * @param <T>
     */
    public static <T> void difference(List<T> list1, List<T> list2) {
        if (isEmpty(list1) || isEmpty(list2)) {
            return;
        }
        for (int i = 0; i < list1.size(); i++) {
            T t = list1.get(i);
            if (list2.contains(t)) {
                list1.remove(t);
                list2.remove(t);
                i--;
            }
        }
    }

    /**
     * 将来源数据列表的元素赋值给目标数据列表的指定属性.
     * 参考{@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * 举例:
     * sourceList为userList,
     * targetList为orderList,
     * 那么joinEntry为<User.id, Order.uid>
     * sourceField为User.name
     * targetFields[Order.userName, Order.xxxUserName]
     *
     * @param sourceList   来源数据列表
     * @param targetList   目标数据列表
     * @param joinEntry    聚合Entry<来源数据的key, 目标数据的key>
     * @param sourceField  来源数据的key
     * @param targetFields 目标数据的字段key数据
     */
    public static void copyProperties(List<?> sourceList,
                                      List<?> targetList,
                                      Map.Entry<String, String> joinEntry,
                                      String sourceField, List<String> targetFields) {
        // TODO 这样写, 会导致重复format. 但是传进来是多个的场景比较少, 因此, 暂时不优化.
        for (String targetField : targetFields) {
            Map<String, String> mapping = Maps.newHashMapWithExpectedSize(1);
            mapping.put(sourceField, targetField);
            copyProperties(sourceList, targetList, joinEntry, mapping);
        }
    }

    /**
     * 将来源数据列表的元素赋值给目标数据列表的指定属性.
     * 参考{@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * 举例:
     * sourceList为userList,
     * targetList为orderList,
     * 那么joinEntry为<User.id, Order.uid>
     * fields为[Order.User, Order.XXXUser]
     *
     * @param sourceList 来源数据列表
     * @param targetList 目标数据列表
     * @param joinEntry  聚合Entry<来源数据的key, 目标数据的key>
     * @param field      目标数据列表的属性.
     */
    public static void copyProperties(List<?> sourceList,
                                      List<?> targetList,
                                      Map.Entry<String, String> joinEntry,
                                      String field) {
        Map<String, String> mapping = Maps.newHashMapWithExpectedSize(1);
        mapping.put(null, field);
        copyProperties(sourceList, targetList, joinEntry, mapping);
    }

    /**
     * 将来源数据列表的元素赋值给目标数据列表的指定属性.
     * 参考{@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * 举例:
     * sourceList为userList,
     * targetList为orderList,
     * 那么joinEntry为<User.id, Order.uid>
     * fields为[Order.User, Order.XXXUser]
     *
     * @param sourceList 来源数据列表
     * @param targetList 目标数据列表
     * @param joinEntry  聚合Entry<来源数据的key, 目标数据的key>
     * @param fields     目标数据列表的属性列表.
     */
    public static void copyProperties(List<?> sourceList,
                                      List<?> targetList,
                                      Map.Entry<String, String> joinEntry,
                                      List<String> fields) {
        // TODO 这样写, 会导致重复format. 但是传进来是多个的场景比较少, 因此, 暂时不优化.
        for (String field : fields) {
            copyProperties(sourceList, targetList, joinEntry, field);
        }
    }

    /**
     * 将来源数据列表的指定属性赋值给目标数据列表的指定属性.
     * 参考{@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * 举例:
     * sourceList为userList,
     * targetList为orderList,
     * 那么joinEntry为<User.id, Order.uid>
     * mapping为[{user.name, order.userName}, {user.age, order.userAge}, ...]
     *
     * @param sourceList 来源数据列表
     * @param targetList 目标数据列表
     * @param joinEntry  聚合Entry<来源数据的key, 目标数据的key>
     * @param mapping    字段映射Map<来源数据的字段key, 目标数据的字段key>
     */
    public static void copyProperties(List<?> sourceList,
                                      List<?> targetList,
                                      Map.Entry<String, String> joinEntry,
                                      Map<String, String> mapping) {
        // 参数校验部分
        if (isEmpty(sourceList) || isEmpty(targetList) || joinEntry == null || CollectionUtils.isEmpty(mapping)) {
            return;
        }
        Map<Object, Object> sourceMap;
        try {
            // 组件sourceMap, 减少循环次数
            String joinEntryKey = joinEntry.getKey();
            sourceMap = Maps.newHashMapWithExpectedSize(sourceList.size());
            Field joinEntryField = getField(sourceList, joinEntryKey);
            // TODO 芋艿: 未来增加一个场景, Map<?, List<?>>
            for (Object source : sourceList) {
                sourceMap.put(joinEntryField.get(source), source);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        copyProperties(sourceMap, targetList, joinEntry, mapping);
    }

    /**
     * 将来源数据Map的指定属性赋值给目标数据列表的指定属性.
     * 参考{@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * 举例:
     * sourceMap为userMap,
     * targetList为orderList,
     * 那么joinEntry为<User.id, Order.uid>
     * mapping为[{user.name, order.userName}, {user.age, order.userAge}, ...]
     *
     * @param sourceMap  来源数据Map
     * @param targetList 目标数据列表
     * @param joinEntry  聚合Entry<来源数据的key, 目标数据的key>
     * @param mapping    字段映射Map<来源数据的字段key, 目标数据的字段key>
     */
    private static void copyProperties(Map<?, ?> sourceMap, List<?> targetList,
                                       Map.Entry<String, String> joinEntry, Map<String, String> mapping) {
        // 参数校验部分
        if (CollectionUtils.isEmpty(sourceMap) || isEmpty(targetList) || joinEntry == null || CollectionUtils.isEmpty(mapping)) {
            return;
        }
        try {
            // 构建
            String joinEntryValue = joinEntry.getValue();
            Object firstSource = sourceMap.values().iterator().next(); // 获取sourceList第一个元素, 用于下面获得field信息
            List<Field> mappingFields = new ArrayList<>(mapping.size() * 2);
            for (Map.Entry<String, String> entry : mapping.entrySet()) {
                if (entry.getKey() != null) {
                    mappingFields.add(getField(firstSource, entry.getKey()));
                } else {
                    mappingFields.add(null);
                }
                mappingFields.add(getField(targetList, entry.getValue()));
            }
            // 赋值
            Field joinEntryValueField = getField(targetList, joinEntryValue);
            for (Object target : targetList) {
                Object source = sourceMap.get(joinEntryValueField.get(target));
                if (null == source) {
                    continue;
                }
                for (int i = 0, len = mappingFields.size(); i < len; i += 2) {
                    Field sourceField = mappingFields.get(i);
                    Field targetField = mappingFields.get(i + 1);
                    if (sourceField == null) {
                        targetField.set(target, source);
                    } else {
                        targetField.set(target, sourceField.get(source));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个List中的移除的元素
     *
     * @param srcList
     * @param targetList
     * @param <T>
     */
    public static <T> List<T> buildRemovedElements(List<T> srcList, List<T> targetList) {
        if (isEmpty(srcList)) {
            return Collections.EMPTY_LIST;
        }

        List<T> returnElements = new ArrayList<>(srcList);
        if (isEmpty(targetList)) {
            return returnElements;
        }

        returnElements.removeAll(targetList);
        return returnElements;
    }

    /**
     * 计算两个List中的新增的元素
     *
     * @param srcList
     * @param targetList
     * @param <T>
     */
    public static <T> List<T> buildAddedElements(List<T> srcList, List<T> targetList) {
        if (isEmpty(targetList)) {
            return Collections.EMPTY_LIST;
        }

        List<T> returnElements = new ArrayList<>(targetList);
        if (isEmpty(srcList)) {
            return returnElements;
        }

        returnElements.removeAll(srcList);
        return returnElements;
    }

    /**
     * 创建随机Map
     *
     * @param keyClazz key class
     * @param valClazz value class
     * @return 随机Map
     */
    public static <K, V> Map<K, V> randomMap(Class<K> keyClazz, Class<V> valClazz) {
        int size = MathUtil.random(10);
        Map<K, V> result = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            try {
                K key;
                if (keyClazz == String.class) {
                    key = (K) StringUtil.random();
                } else {
                    key = keyClazz.newInstance();
                }
                result.put(key, valClazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}