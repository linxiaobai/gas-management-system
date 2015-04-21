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
        List<String> list = new ArrayList<String>();
        list.add("开始导航");
        list.add("刷新系统");
        list.add("退出系统");
        GmsMenuItem[] items = GmsMenuItem.fetchItems(list);
        List<String> itemList = new ArrayList<String>();
        itemList.add("开始导航");
        itemList.add("刷新系统");
        itemList.add("退出系统");
        GmsMenu gmsMenu = new GmsMenu("系统").addItems(true, itemList);

        GmsMenuBar gmsMenuBar = new GmsMenuBar().addMenu(gmsMenu);
        GmsFrame gmsFrame = new GmsFrame.Builder(300,500,"HelloJFrame").isVisible(true).jMenuBar(gmsMenuBar).build();
    }
}
