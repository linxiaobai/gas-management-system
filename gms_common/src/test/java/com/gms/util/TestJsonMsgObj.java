package com.gms.util;

import com.gms.bean.vo.LoginInfo;
import com.gms.bean.vo.TransJsonObject;

/**
 * Created by Kevin on 2015/4/17.
 */
public class TestJsonMsgObj {
    public static void main(String[] args) {
        String str = JsonMsgObj.LOGIN_INFO.toString();
        System.out.println(str);
        String clazzName = StringUtil.camelName(str);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername("linjian");
        loginInfo.setPassword("123456");
        TransJsonObject transJsonObject = new TransJsonObject(loginInfo, ConstantsUtil.LOGIN_INFO);
        try {
            Class clazz = Class.forName("com.gms.bean.vo."+clazzName);
            LoginInfo ret = (LoginInfo) clazz.cast(transJsonObject.getDataObject());
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
