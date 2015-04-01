package com.gms.util.dbutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Kevin on 2015/4/1.
 *
 */
public class ConvertUtil {
    private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    /**
     * 利用反射机制将一个集合转成二维数组
     * DBUtils里面的batch操作需要每个实体bean里面的每个成员变量值做为一个一维数组里面的一部分
     * 然后多个实例对象对应多个一维数组，所以写个方法快速获取所需操作的二维数组
     *
     * @param list 封装好的所需要存入数据库中的实体对象集合
     * @param clazz 实体类类型
     * @return
     */
    public static Object[][] listToArr(List<Object> list,Class clazz) {
        if (list == null || list.size() == 0) {
            return null;
        }

        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {//必须保证实体类中拥有至少一个成员变量
            return null;
        }

        Object[][] retArr = new Object[list.size()][];
        String[] methodNames = new String[fields.length];
        for (int n = 0; n < fields.length; n++) {
            methodNames[n] = buildGetMethodStr(fields[n].getName()); //获取实体类中所有get方法对应的name
        }
        for (int i = 0; i < list.size(); i++) {
            //成员变量的数目对应着单个对象的字段数目
            Object[] array =  new Object[fields.length];
            for (int j = 0; j < methodNames.length; j++) {
                try {
                    Method m = clazz.getDeclaredMethod(methodNames[j]);
                    array[j] = m.invoke(list.get(i));
                } catch (Exception e) {
                    logger.error("集合转成二维数组异常,{}", e.getMessage());
                }
            }
            retArr[i] = array;
        }
        return retArr;
    }

    /**
     * 构建getXx类型的字符串
     * 例如 getName
     * @return
     */
    protected static String buildGetMethodStr(String str) {
        String getMethodName = "get"+Character.toUpperCase(str.charAt(0))+str.substring(1);
        return getMethodName;
    }
}
