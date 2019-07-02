package com.roilka.shop.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author zhanghui
 * @description 对象公用工具类
 * @date 2019/5/5
 */
@Slf4j
public class ObjectUtil {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNotNullAndNotEmpty(Object obj) {
        return !isNull(obj) && !isEmpty(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean judgeBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return obj != null && (Boolean) obj;
        }
        return false;
    }

    public static <T> T copyObject(Object source, Class<T> clz) {
        Object target = null;
        if (source == null){
            return null;
        }
        try {
            target = clz.newInstance();
        } catch (InstantiationException e) {
            log.error("对象转换异常，source={},clz={}",source,clz,e);
        } catch (IllegalAccessException e) {
            log.error("对象转换异常，source={},clz={}",source,clz,e);
        }
        BeanUtils.copyProperties(source, target);
        return (T)target;
    }
}
