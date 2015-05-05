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
    public static <T> Object[][] listToArr(List<T> list, Class<T> clazz) {
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
     * @param list
     * @param clazz
     * @param needNames 需要转成二维数组数据的字段
     * @param <T>
     * @return
     */
    public static <T> Object[][] listToArr(List<T> list, Class<T> clazz, String... needNames) {
        if (list == null || list.size() == 0) {
            return null;
        }

        if (needNames.length == 0) {
            return null;
        }
        String[] methodNames = new String[needNames.length];
        for (int i = 0; i < needNames.length; i++) {
            methodNames[i] = buildGetMethodStr(needNames[i]);
        }

        Object[][] retArr = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            //成员变量的数目对应着单个对象的字段数目
            Object[] array =  new Object[needNames.length];
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

    /**
     * 对象转一维数组，返回数据用于单个对象插入数据库
     * 需要保证对象中每个成员变量有值
     *
     * 好像可变参数不可以直接用数组代替，暂时用不到这个方法
     */
    @Deprecated
    public static Object[] objectToArr(Object o) {
        if (o == null) {
            return null;
        }
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object[] objArr = new Object[fields.length];
        String[] methodNames = new String[fields.length];
        for (int n = 0; n < fields.length; n++) {
            methodNames[n] = buildGetMethodStr(fields[n].getName()); //获取实体类中所有get方法对应的name
        }

        for (int i = 0; i < fields.length; i++) {
            try {
                Method m = clazz.getDeclaredMethod(methodNames[i]);
                objArr[i] = m.invoke(o);
            } catch (Exception e) {
                logger.error("对象转一维数组异常：{}", e.getMessage());
            }
        }

        return objArr;
    }

    /**
     * 获取一个类的所有成员变量名（即对于数据库里面表的字段名称）
     * @return
     */
    public static String[] fetchVariableNames(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] names = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();

        }
        return names;
    }

}
