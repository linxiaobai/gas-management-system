package com.gms.service;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.dbutil.SQLUtil;
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
    /*返回查询结果菜单*/
    private static final String MENUS_QUERY_SQL = "SELECT * FROM MENUS WHERE USER_TYPE = ? AND IS_VALID = ?";

    public String login(LoginInfo loginInfo) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        //如果传输的数据存在问题，则不执行sql操作直接返回错误信息
        if (loginInfo == null || StringUtils.isBlank(loginInfo.getUsername())
                || StringUtils.isBlank(loginInfo.getPassword())) {
            apiResultBuilder.withErrmsg(ConstantsUtil.LOGIN_ERROR_MSG).withErrcode(ConstantsUtil.LOGIN_ERROR)
                    .withRet(false);
            return JSONObject.toJSONString(apiResultBuilder);
        }
        logger.info("用户{}进行登录", loginInfo.getUsername());
        User user = SQLUtil.selectBean(LOGIN_VALIDATE_SQL, User.class, loginInfo.getUsername(), loginInfo.getPassword());
        if (user != null) {
            List<Menus> menuses = SQLUtil.selectBeanList(MENUS_QUERY_SQL, Menus.class, user.getUserType(), 1);
            apiResultBuilder.withRet(true).withData(JSONObject.toJSONString(menuses));
        } else {
            apiResultBuilder.withErrmsg(ConstantsUtil.LOGIN_ERROR_MSG).withErrcode(ConstantsUtil.LOGIN_ERROR)
                    .withRet(false);
        }

        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
