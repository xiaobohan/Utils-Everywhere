package com.linsh.utilseverywhere;

import com.linsh.utilseverywhere.interfaces.Action;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/10
 *    desc   : 工具类: 集合相关
 *             API  : 判空 / 反转 / 拼接 / 转字符串 等
 * </pre>
 */
public class ListUtils {

    private ListUtils() {
    }

    /**
     * 判断集合是否为空
     *
     * @param list 集合
     * @return 是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 反转集合
     *
     * @param list 集合
     * @return 反转的新集合
     */
    public static <T> List<T> invertList(List<T> list) {
        if (isEmpty(list)) {
            return list;
        }
        List<T> invertList = new ArrayList<>(list.size());
        for (int i = list.size() - 1; i >= 0; i--) {
            invertList.add(list.get(i));
        }
        return invertList;
    }

    /**
     * 转换集合
     *
     * @param list   集合
     * @param action 用于将元素T 转换元素R
     * @return 转换后的集合
     */
    public static <R, T> List<R> convertList(List<T> list, Action<R, T> action) {
        ArrayList<R> result = new ArrayList<>();
        if (list != null && action != null) {
            for (T t : list) {
                result.add(action.call(t));
            }
        }
        return result;
    }

    /**
     * 拼接集合中所有的元素
     *
     * @param list    集合
     * @param divider 元素之间的连接符
     * @return 拼接后的字符串
     */
    public static <T> String joint(List<T> list, String divider) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                builder.append(divider);
            }
            builder.append(list.get(i));
        }
        return builder.toString();
    }

    /**
     * 将集合转化成数组
     *
     * @param list  指定的集合
     * @param clazz 指定的数组类型的类
     * @param <T>   泛型, 集合中的泛型或其父类
     * @return 转化后得到的数组
     */
    public static <T> T[] toArray(List<? extends T> list, Class<T> clazz) {
        if (list == null) {
            return null;
        }
        T[] array = (T[]) Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }

    /**
     * 将可迭代对象转为字符串表示, 如 [a, b, c]
     *
     * @param iterable 可迭代对象
     * @return 字符串
     */
    public static String toString(Iterable iterable) {
        return toString(iterable == null ? null : iterable.iterator());
    }

    /**
     * 将迭代器对象转为字符串表示, 如 [a, b, c]
     *
     * @param iterator 迭代器对象
     * @return 字符串
     */
    public static String toString(Iterator iterator) {
        if (iterator == null) return "null";
        if (!iterator.hasNext()) return "[]";
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (; ; ) {
            Object element = iterator.next();
            if (element == null) {
                builder.append("null");
            } else if (element instanceof Iterable) {
                builder.append(toString((Iterable) element));
            } else if (element instanceof Iterator) {
                builder.append(toString((Iterator) element));
            } else if (element.getClass().isArray()) {
                builder.append(ArrayUtils.toString(element));
            } else {
                builder.append(element);
            }
            if (!iterator.hasNext())
                return builder.append(']').toString();
            builder.append(',').append(' ');
        }
    }
}
