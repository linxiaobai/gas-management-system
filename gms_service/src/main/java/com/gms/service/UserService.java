package com.gms.service;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
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
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /*登录sql*/
    private static final String LOGIN_VALIDATE_SQL = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWD = ?";
    /*查询用户是否被注册sql*/
    private static final String CHECK_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?";
    /*用户添加*/
    private static final String INSER_USER_SQL = "INSERT INTO USER(USERNAME, PASSWD, REAL_NAME, MOBILE_PHONE, USER_TYPE, CREATE_TIME, MODIFY_TIME)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    /*返回查询结果菜单*/
    private static final String MENUS_QUERY_SQL = "SELECT * FROM MENUS WHERE USER_TYPE = ? AND IS_VALID = ?";

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
            List<Menus> menuses = SQLUtil.selectBeanList(MENUS_QUERY_SQL, Menus.class, user.getUserType(), 1); //1表示菜单栏没被禁用
            apiResultBuilder.withRet(true).withData(JSONObject.toJSONString(menuses));
        } else {
            apiResultBuilder.withErrmsg(ErrorMessage.LOGIN_ERROR_MSG).withErrcode(ErrorMessage.LOGIN_ERROR)
                    .withRet(false);
        }

        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    public String addUser(User user) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);

        if (SQLUtil.selectBean(CHECK_USERNAME, User.class, user.getUsername()) != null) {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.USERNAME_IS_EXIST).withErrmsg(ErrorMessage.USERNAME_IS_EXIST_ERROR);
        } else {
            int ret = SQLUtil.insertOne(INSER_USER_SQL, user.getUsername(), user.getPasswd(), user.getRealName(), user.getMobilePhone(), user.getUserType(), user.getCreateTime(), user.getModifyTime());
            if (ret > 0) {
                apiResultBuilder.withRet(true).withData("用户添加成功");
            } else {
                apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SQL_ERROR).withErrmsg(ErrorMessage.SQL_ERROR_MSG);
            }
        }

        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

}
