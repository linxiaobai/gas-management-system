package com.gms.bean.vo;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Kevin on 2015/4/17.
 */
public class GsonTest {
    public static void main(String[] args) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername("linjian");
        loginInfo.setPassword("123456");
        TransJsonObject transJsonObject = new TransJsonObject(loginInfo,1);
        Gson gson = new Gson();
        String ret = gson.toJson(transJsonObject);
        System.out.println(ret);

        TransJsonObject transJsonObject1 = gson.fromJson(ret, TransJsonObject.class);
        LoginInfo loginInfo1 = gson.fromJson(transJsonObject1.getDataObject().toString(), LoginInfo.class);
        System.out.println(loginInfo1.getUsername());
    }
}
