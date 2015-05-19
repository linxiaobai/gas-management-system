package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.service.UserService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.dbutil.SQLUtil;
import com.gms.util.error.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kevin on 2015/4/17.
 */
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /*登录sql*/
    private static final String LOGIN_VALIDATE_SQL = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWD = ?";
    /*查询用户是否被注册sql*/
    private static final String CHECK_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?";
    /*用户添加*/
    private static final String INSERT_USER_SQL = "INSERT INTO USER(USERNAME, PASSWD, REAL_NAME, MOBILE_PHONE, USER_TYPE, CREATE_TIME, MODIFY_TIME)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_SQL = "DELETE FROM USER WHERE USERNAME = ?";//根据用户名删除用户，用户名是唯一的
    /*查询所有普通用户*/
    private static final String QUERY_ALL_USER = "SELECT * FROM USER WHERE USER_TYPE = ?";

    /**
     * 用户登录校验
     * @param loginInfo
     * @return
     */
    @Override
    public String login(LoginInfo loginInfo) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        //如果传输的数据存在问题，则不执行sql操作直接返回错误信息
        if (loginInfo == null || StringUtils.isBlank(loginInfo.getUsername())
                || StringUtils.isBlank(loginInfo.getPassword())) {
            apiResultBuilder.withErrmsg(ErrorMessage.LOGIN_ERROR_MSG).withErrcode(ErrorMessage.LOGIN_ERROR)
                    .withRet(false);
            return JSONObject.toJSONString(apiResultBuilder);
        }

        logger.info("用户{}进行登录", loginInfo.getUsername());
        User user = SQLUtil.selectBean(LOGIN_VALIDATE_SQL, User.class, loginInfo.getUsername(), loginInfo.getPassword());
        if (user != null) {
//            List<Menus> menuses = SQLUtil.selectBeanList(MENUS_QUERY_SQL, Menus.class, user.getUserType(), 1); //1表示菜单栏没被禁用
            apiResultBuilder.withRet(true).withData(JSONObject.toJSONString(user));
        } else {
            apiResultBuilder.withErrmsg(ErrorMessage.LOGIN_ERROR_MSG).withErrcode(ErrorMessage.LOGIN_ERROR)
                    .withRet(false);
        }

        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public String addUser(User user) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        logger.info("添加用户{}",user);
        if (SQLUtil.selectBean(CHECK_USERNAME, User.class, user.getUsername()) != null) {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.USERNAME_IS_EXIST).withErrmsg(ErrorMessage.USERNAME_IS_EXIST_ERROR);
        } else {
            int ret = SQLUtil.insertOne(INSERT_USER_SQL, user.getUsername(), user.getPasswd(), user.getRealName(), user.getMobilePhone(), user.getUserType(), user.getCreateTime(), user.getModifyTime());
            if (ret > 0) {
                apiResultBuilder.withRet(true).withData("用户添加成功");
            } else {
                apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SQL_ERROR).withErrmsg(ErrorMessage.SQL_ERROR_MSG);
            }
        }

        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 查询所有普通用户信息
     * @return
     */
    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        logger.info("查询所有普通用户");
        List<User> users = SQLUtil.selectBeanList(QUERY_ALL_USER, User.class, ConstantsUtil.NORMAL_USER);
        apiResultBuilder.withRet(true).withData(users);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 根据用户名删除用户
     * @param username
     * @return
     */
    @Override
    public String deleteUser(String username) {
        logger.info("删除用户名为{}的用户", username);
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        int ret = SQLUtil.updateOne(DELETE_USER_SQL, username);
        if (ret > 0) {
            apiResultBuilder.withRet(true).withData("删除用户成功");
        } else {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SQL_ERROR).withErrmsg(ErrorMessage.SQL_ERROR_MSG);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }


}
