package com.gms.service;

import com.gms.bean.po.Menus;
import com.gms.util.dbutil.SQLUtil;

/**
 * Created by Kevin on 2015/4/18.
 */
public class MenuService {
    private static final String INSERT_SQL = "INSERT INTO MENU (USER_TYPE, MENU_LEVEL, MENU_NAME, MENU_MSG, PARENT_ID, IS_VALID) VALUES(?, ?, ?, ?, ?, ?)";

    public int insertMenu(Menus menus) {
        SQLUtil.insertOne(INSERT_SQL);
        return 1;
    }

}
