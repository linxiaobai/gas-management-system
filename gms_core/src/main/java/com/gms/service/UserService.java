package com.gms.service;

import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;

/**
 * Created by Kevin on 2015/5/7.
 */
public interface UserService {

    /**
     * 登录处理
     * @param loginInfo
     * @return
     */
    String login(LoginInfo loginInfo);

    /**
     * 用户添加
     * @param user
     * @return
     */
    String addUser(User user);

    /**
     * 查询全部
     * @return
     */
    String queryAll();

    /**
     * 删除用户
     * @param username
     * @return
     */
    String deleteUser(String username);

}
