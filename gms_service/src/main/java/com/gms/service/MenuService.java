package com.gms.service;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.dbutil.SQLUtil;
import com.gms.util.error.ErrorMessage;

import java.util.List;
import java.util.Set;

/**
 * Created by Kevin on 2015/4/18.
 */
public class MenuService {
    private static final String INSERT_SQL = "INSERT INTO MENUS (USER_TYPE, MENU_LEVEL, MENU_NAME, MENU_MSG, PARENT_ID, IS_VALID) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECTALL_SQL = "SELECT * FROM MENUS WHERE USER_TYPE = ?";
    private static final String UPDATEAll_SQL = "UPDATE MENUS SET IS_VALID = ?"; //先重置所有的菜单为启用状态
    private static final String ALTER_AUTH_SQL = "UPDATE MENUS SET IS_VALID = ? WHERE ID = ?"; //需要补充SQL
    private static final String RESET_AUTH_SQL = "UPDATE MENUS SET IS_VALID = ?"; //需要补充SQL

    public int insertMenu(Menus menus) {
        SQLUtil.insertOne(INSERT_SQL);
        return 1;
    }

    /**
     * 查询普通用户的所有菜单，用于客户端管理分配权限
     */
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<Menus> menuses = SQLUtil.selectBeanList(SELECTALL_SQL, Menus.class, ConstantsUtil.NORMAL_USER);
        if (menuses == null || menuses.size() == 0) {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SELECT_EMPTY).withErrmsg(ErrorMessage.SELECT_EMPTY_MSG);
        } else {
            apiResultBuilder.withRet(true).withData(menuses);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 开启或分配权限
     */
    public String distributeAuth(Set<Integer> menusIds) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        if (menusIds == null && menusIds.size() == 0) {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.ALTER_MENUS_ERROR).withErrmsg(ErrorMessage.ALTER_MENUS_ERROR_MSG);
        } else { //先开启所有的，再关闭指定的
            SQLUtil.updateOne(RESET_AUTH_SQL,1);
            Object[][] params = new Object[menusIds.size()][];
            int index = 0;
            for (Integer menusId : menusIds) {
                params[index++] = new Object[]{0, menusId}; //1表示IS_VALID对应的参数
            }
            int[] updateRet = SQLUtil.batch(ALTER_AUTH_SQL, params);
            if (updateRet != null && updateRet.length > 0) {
                apiResultBuilder.withRet(true).withData("重新绑定权限成功");
            } else {
                apiResultBuilder.withRet(false).withErrcode(ErrorMessage.ALTER_MENUS_ERROR).withErrmsg(ErrorMessage.ALTER_MENUS_ERROR_MSG);
            }

        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());

    }
}
