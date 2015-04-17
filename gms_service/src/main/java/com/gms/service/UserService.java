package com.gms.service;

import com.alibaba.fastjson.JSONObject;
import com.gms.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.StringUtil;
import com.gms.util.dbutil.SQLUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Kevin on 2015/4/17.
 */
public class UserService {
    public static final String LOGIN_VALIDATE_SQL = "SELECT * FROM USER WHERE USERNAME = ? and PASSWD = ?";

    public String login(LoginInfo loginInfo) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        //如果传输的数据存在问题，则不执行sql操作直接返回错误信息
        if (loginInfo == null || StringUtils.isBlank(loginInfo.getUsername())
                || StringUtils.isBlank(loginInfo.getPassword())) {
            apiResultBuilder.withErrmsg(ConstantsUtil.LOGIN_ERROR_MSG).withErrcode(ConstantsUtil.LOGIN_ERROR)
                    .withRet(false);
            return JSONObject.toJSONString(apiResultBuilder);
        }

        User user = SQLUtil.selectBean(LOGIN_VALIDATE_SQL, User.class, loginInfo.getUsername(), loginInfo.getPassword());
        if (user != null) {
            apiResultBuilder.withRet(true).withData("登录成功");
        } else {
            apiResultBuilder.withErrmsg(ConstantsUtil.LOGIN_ERROR_MSG).withErrcode(ConstantsUtil.LOGIN_ERROR)
                    .withRet(false);
        }

        return JSONObject.toJSONString(apiResultBuilder);
    }
}
