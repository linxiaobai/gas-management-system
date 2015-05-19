package com.gms.service;

import com.gms.bean.po.Menus;

import java.util.Set;

/**
 * Created by Kevin on 2015/5/7.
 */
public interface MenuService {

    int insertMenu(Menus menus);

    /**
     * 查询所有菜单
     * @return
     */
    String queryAll(Byte userType, boolean isUseValid);

    /**
     * 分配权限
     * @param menusIds
     * @return
     */
    String distributeAuth(Set<Integer> menusIds);


}
