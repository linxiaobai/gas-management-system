package com.gms.swing;

import javax.swing.*;

/**
 * Created by Kevin on 2015/4/8.
 * 菜单栏（二级菜单）
 */
public class GmsMenuItem extends JMenuItem{
    public GmsMenuItem() {}

    public GmsMenuItem(String name) {
        super(name);
    }

    /**
     * 根据name的集合一次性实例化多个item
     * @param names
     * @return
     */
    public static GmsMenuItem[] fetchItems(String... names) {
        GmsMenuItem[] gmsMenuItems = new GmsMenuItem[names.length];
        for (int i = 0; i < names.length; i++) {
            gmsMenuItems[i] =  new GmsMenuItem(names[i]);
        }
        return gmsMenuItems;
    }
}
