package com.gms.util;

/**
 * Created by Kevin on 2015/4/16.
 * 所有功能模块业务处理的标志
 */
public interface ConstantsUtil {

    public static byte ADMIN_USER = 1;

    public static byte NORMAL_USER = 2;

    /*以下数字不可轻易做变动，关联到DealClientRequest.java里面的switch模块代码*/
    public static int LOGIN_INFO = 1; //登录信息

    public static int MENUS_INFO = 2; //请求所有菜单数据

    public static int MENUS_SETTING = 3; //配置普通管理员相关权限

    public static int USER_ADD = 4; //用户添加

    public static int USER_LIST = 5; //列出用户信息

    public static int USER_DEL = 6;





}
