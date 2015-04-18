package com.gms.service;

import com.gms.bean.po.Menus;
import com.gms.util.dbutil.SQLUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Kevin on 2015/4/18.
 */
public class MenusServiceTest {
    @Test
    @Ignore
    public void add() {
        Menus menus = new Menus();
        menus.setMenuName("lin");
        menus.setIsValid((byte) 1);
        menus.setMenuLevel((byte) 1);
        menus.setParentId(2);
        menus.setMenuMsg("asd");
        menus.setUserType((byte) 2);
        System.out.println(SQLUtil.insertOne("INSERT INTO MENU (USER_TYPE, MENU_LEVEL, MENU_NAME, MENU_MSG, PARENT_ID, IS_VALID) VALUES(?, ?, ?, ?, ?, ?)", menus.getUserType(), menus.getMenuLevel(),
                menus.getMenuName(), menus.getMenuMsg(), menus.getParentId(), menus.getIsValid()));
    }

    @Test
    public void selectTest() {
        String sql = "SELECT * FROM MENUS WHERE USER_TYPE = ? AND IS_VALID = ?";
        List<Menus> menuses = SQLUtil.selectBeanList(sql, Menus.class, 1, 1);
        System.out.println(menuses);
    }
}
