package com.gms.util;

/**
 * Created by Kevin on 2015/4/16.
 * 所有功能模块业务处理的标志
 */
public interface ConstantsUtil {

    public static int PACKAGE_SIZE = 10000; //拆分数据包字符串长度

    /*用户类型*/
    public static byte ADMIN_USER = 1;

    public static byte NORMAL_USER = 2;

    /*设备状态*/
    public static byte FAILED_DEVICE = 1;

    public static byte NORMAL_DEVICE = 0;

    public static Integer DATA_SIZE = 10; //报表显示最多数据数目

    /*缴费说明*/
    public static String PAY_STATEMENT_STR = "<html>\n" +
            "\t<div align=\"center\"><h2>使用说明</h2></div> <br />\n" +
            "\t<div>\n" +
            "\t\t<pre>\n" +
            "\t\t\t缴费管理中，你可以给燃气使用用户进行缴费操作。具体操作步骤分为以下两步。\n" +
            "\t\t\t步骤1：点击“订单创建”创建一个订单，填写用户的燃气使用额度，用户联系方式等\n" +
            "\t\t\t步骤2：点击“缴费”可以获知当前已经出单但是未收到用户缴费金额的订单信息，然后\n" +
            "\t\t\t收取用户对应燃气费用，处理某个订单进行缴费处理。\n" +
            "\t\t</pre>\n" +
            "\t</div>\n" +
            "</html>";

    /**
     * ================================================================
     * 以下数字不可轻易做变动，关联到DealClientRequest.java里面的switch模块代码
     */

    public static int LOGIN_INFO = 1; //登录信息

    public static int MENUS_INFO = 2; //请求所有菜单数据

    public static int MENUS_SETTING = 3; //配置普通管理员相关权限

    public static int USER_ADD = 4; //用户添加

    public static int USER_LIST = 5; //列出用户信息

    public static int USER_DEL = 6; //用户删除

    public static int DEVICE_LIST = 7; //列出所有设备信息

    public static int DEVICE_ADD = 8; //设备信息添加

    public static int DEVICE_FIX = 9; //设备修理

    public static int ENVIRONMENT_LIST = 10; //环境参数数据获取

    public static int VALID_MENUS_INFO = 11; //查询所有非屏蔽的菜单栏

    public static int ENVIRONMENT_CHART_DATA = 12; //取最近的10条数据，而不是所有数据

    public static int DEVICE_CHART_LIST = 13;  //设备数据显示图表所需的数据

    public static int DEVICE_REPAIRED_RECORD_LIST = 14; //设备维修记录查询

    public static int RUNTIME_DEVICE_LIST = 15; //设备平时运行的数据

    public static int FINANCE_LIST = 16; //公司账户数据查询

    public static int FETCH_ORDER_SEQ = 17; //获取订单号

    public static int CREATE_ORDER = 18; //创建订单数据

    public static int UNPAID_BILL_LIST = 19; //没有缴费的订单数据

    public static int DEAL_BILL = 20; //处理缴费


}
