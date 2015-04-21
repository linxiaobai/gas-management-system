package com.gms;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.User;
import com.google.gson.Gson;

/**
 * Created by Kevin on 2015/4/18.
 */
public class TestJson {
    public static void main(String[] args) {
        User user = new User();
        user.setUserType((byte)1);
        user.setUsername("asdd");
        user.setPasswd("123");
        String str = JSONObject.toJSONString(user);
        Gson gson = new Gson();
        String str2 = gson.toJson(user);
        System.out.println(str2);
        User ret = gson.fromJson(str2, User.class);
        System.out.println(ret);
    }
}
