package com.gms.service;

import com.gms.bean.vo.LoginInfo;
import org.junit.Test;

/**
 * Created by Kevin on 2015/4/17.
 */
public class UserServiceTest {
    @Test
    public void testLogin() {
        UserService userService = new UserService();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setPassword("12345");
        loginInfo.setUsername("linjian");
        System.out.println(userService.login(loginInfo));
    }
}
