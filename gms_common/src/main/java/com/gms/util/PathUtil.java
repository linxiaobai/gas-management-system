package com.gms.util;

/**
 * Created by Kevin on 2015/4/8.
 */
public class PathUtil {

    /**
     * 获取资源路径，主要是获取图片的
     * @param clazz
     * @param fileName
     * @return
     */
    public static String fetchPath(Class clazz, String fileName) {
        ClassLoader classLoader = clazz.getClassLoader();
        String path = classLoader.getResource(fileName).getPath();
        return path;
    }
}
