package com.gms.swing;

import javax.swing.*;

/**
 * Created by Kevin on 2015/4/19.
 */
public class GmsCheckBox extends JCheckBox{
    private int hiddenId; //用来存放菜单编号

    public GmsCheckBox() {}

    public GmsCheckBox(String text) {
        super(text);
    }

    public GmsCheckBox(Byte valid, String text, int menuId) {
        super(text, valid == 1 ? true : false);
        this.hiddenId = menuId;
    }

    public int getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(int hiddenId) {
        this.hiddenId = hiddenId;
    }
}
