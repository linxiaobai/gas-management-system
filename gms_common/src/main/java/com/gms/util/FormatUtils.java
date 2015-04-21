package com.gms.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin on 2014/10/6.
 * 关于格式处理的一些工具类
 */
public class FormatUtils {
    /**
     * 向上取整    例如: 30条数据，每页8条  一共4页
     * @param total
     * @param pageSize
     * @return
     */
    public static int getPageTotal(int total,int pageSize){
        if(pageSize == 0){//分母不能为0
            return 0;
        }
        return (int)Math.ceil((double)total/pageSize);
    }



    /**
     * 将字符串类小数转换成BigDecimal类型（保留2位小数）
     * @param str
     * @return
     */
    public static BigDecimal convertToBigDecimal(String str) {
        BigDecimal bigDecimal = new BigDecimal(str);
        DecimalFormat format = new DecimalFormat("0.00");
        return new BigDecimal(format.format(bigDecimal));
    }

    /**
     * 验证邮箱格式是否正确
     * @param email
     * @return
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher mat = pattern.matcher(email);
        if (!mat.find()) { tag = false; }
        return tag;
    }

    /**
     * 验证手机号码格式是否正确
     * @param mobiles
     * @return  true 表示正确  false表示不正确
     */
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 保留小数点后几位
     * @param number
     * @param bit
     * @return
     */
    public static double formatDecimal(double number, int bit) {
        StringBuffer format = new StringBuffer(".");
        for (int i = 0; i < bit; i++) {
            format.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(format.toString());
        return Double.valueOf(decimalFormat.format(number));
    }

    public static Float formatDecimal2(Float number, int bit) {
        StringBuffer format = new StringBuffer(".");
        for (int i = 0; i < bit; i++) {
            format.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(format.toString());
        return Float.valueOf(decimalFormat.format(number));
    }

}
