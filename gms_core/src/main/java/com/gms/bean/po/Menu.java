package com.gms.bean.po;

/**
 * Created by Kevin on 2015/3/30.
 * 客户端显示对应的菜单表
 */
public class Menu {
    private Integer id;

    /**
     * 用户类型，用于区分管理员和普通用户显示不同的菜单栏
     */
    private Byte userType;

    /**
     * 菜单等级
     */
    private Byte menuLevel;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单促发事件对应的请求消息（如果直接记录在程序中比较方便就不使用该字段）
     */
    private String menuMsg;

    /**
     * 父级菜单对应的id
     */
    private Integer parentId;

    /**
     * 是否有效
     */
    private Byte isValid;
}
