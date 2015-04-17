package com.gms.swing;

import javax.swing.*;

/**
 * Created by Kevin on 2015/4/8.
 * 菜单栏组件
 */
public class GmsMenuBar extends JMenuBar{

    /**
     * 添加菜单内容
     * @param jMenu
     * @return
     */
    public GmsMenuBar addMenu(JMenu... jMenu) {
        for (JMenu menu : jMenu) {
            this.add(menu);
        }
        return this;
    }

}
