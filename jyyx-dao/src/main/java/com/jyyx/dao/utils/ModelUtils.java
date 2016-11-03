package com.jyyx.dao.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换工具
 * @author xuqing01
 *
 */
public class ModelUtils {

    /**
     * 复制source的成员变量给target class
     * @param source
     * @param target
     * @return  target class的实例
     * @throws BeansException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T copyProperty(Object source, Class<T> target) 
            throws BeansException, InstantiationException, IllegalAccessException {
        if (null == source) {
            return null;
        }
        T targetObj = target.newInstance();
        BeanUtils.copyProperties(source, targetObj);
        return targetObj;
    }
    
    /**
     * 复制source list中的对象到target类型的list中
     * @param source
     * @param target
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws BeansException 
     */
    public static <E, T> List<T> copyList(List<E> source, Class<T> target)
            throws BeansException, InstantiationException, IllegalAccessException {
        if (null == source) {
            return null;
        }
        List<T> targetList = new ArrayList<T>(source.size());
        for (E e : source) {
            targetList.add(copyProperty(e, target));
        }
        return targetList;
    }
}
