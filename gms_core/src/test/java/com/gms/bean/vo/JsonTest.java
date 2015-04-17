package com.gms.bean.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Kevin on 2015/4/17.
 */
public class JsonTest {
    public static void main(String[] args) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername("linjian");
        loginInfo.setPassword("123456");
        TransJsonObject jsonObject = new TransJsonObject(loginInfo, 1);
        String str = JSONObject.toJSONString(jsonObject);
        System.out.println(str);

        TransJsonObject jsonObject1 = JSONObject.parseObject(str, TransJsonObject.class);
        System.out.println(jsonObject.getDataObject());

    }
}
