package com.gms.util.error;

/**
 * Created by wangzhen on 2015/2/12.
 * 错误信息接口枚举
 */
public interface ErrorMessage {

    /*error code*/
    public static int LOGIN_ERROR = 100;
    public static String LOGIN_ERROR_MSG = "用户名或密码有误";

    public static int USERNAME_IS_EXIST = 101;
    public static String USERNAME_IS_EXIST_ERROR = "用户名已经被使用";

    public static int SELECT_EMPTY = 200;
    public static String SELECT_EMPTY_MSG = "数据异常，查询结果集为空";

    public static int SQL_ERROR = 201;
    public static String SQL_ERROR_MSG = "数据库操作异常，请联系管理员";

    public static int ALTER_MENUS_ERROR = 201;
    public static String ALTER_MENUS_ERROR_MSG = "没有选择的任何菜单项";

    public static int REQ_CODE_NOT_EXIST = 300;
    public static String REQ_CODE_NOT_EXIST_MSG = "请求无效";

}
