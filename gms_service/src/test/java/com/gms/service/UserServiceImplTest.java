package com.gms.service;

import com.gms.bean.vo.LoginInfo;
import com.gms.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * Created by Kevin on 2015/4/17.
 */
public class UserServiceImplTest {
    @Test
    public void testLogin() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setPassword("12345");
        loginInfo.setUsername("linjian");
        System.out.println(userServiceImpl.login(loginInfo));
    }
}
