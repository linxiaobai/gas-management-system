package com.gms.util;

import com.gms.swing.GmsFrame;
import com.gms.swing.GmsMenu;
import com.gms.swing.GmsMenuBar;
import com.gms.swing.GmsMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/4/7.
 */
public class TestFrame {
    public static void main(String[] args) {
        GmsMenuItem[] items = GmsMenuItem.fetchItems("开始导航", "刷新系统", "退出系统");
        List<String> itemList = new ArrayList<String>();
        itemList.add("开始导航");
        itemList.add("刷新系统");
        itemList.add("退出系统");
        GmsMenu gmsMenu = new GmsMenu("系统").addItems(true, itemList);

        GmsMenuBar gmsMenuBar = new GmsMenuBar().addMenu(gmsMenu);
        GmsFrame gmsFrame = new GmsFrame.Builder(300,500,"HelloJFrame").isVisible(true).jMenuBar(gmsMenuBar).build();
    }
}
