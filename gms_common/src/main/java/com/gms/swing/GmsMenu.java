package com.gms.swing;

import javax.swing.*;
import java.util.List;

/**
 * Created by Kevin on 2015/4/8.
 * 菜单栏（一级菜单）
 */
public class GmsMenu extends JMenu{
    public GmsMenu() {}

    public GmsMenu(String name) {
        super(name);
    }

    /**
     * 给menu里面增加item
     * @param isSep 是否增加分隔符
     * @param jMenuItems  一个或多个item
     * @return
     */
    public GmsMenu addItems(boolean isSep, List<String> jMenuItems) {
        if (isSep) { //是否需要在每个item之间增加分隔符
            for (int i = 0; i < jMenuItems.size(); i++) {
                this.add(new GmsMenuItem(jMenuItems.get(i)));
                if (i != (jMenuItems.size() - 1)) { //如果不是最后一个item,则增加一个分隔符
                    this.addSeparator();
                }
            }
        } else {
            for (int i = 0; i < jMenuItems.size(); i++) {
                this.add(new GmsMenuItem(jMenuItems.get(i)));
            }
        }
        return this;
    }
}
