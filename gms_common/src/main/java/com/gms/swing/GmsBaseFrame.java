package com.gms.swing;

import javax.swing.*;

/**
 * Created by Kevin on 2015/4/18.
 * 系统frame界面的父类，包含退出系统确认方法
 */
public class GmsBaseFrame extends JFrame{

    public GmsBaseFrame(String name) {
        super(name);
    }

    protected void quit() {
        int confirmRet;
        String msg = "您 现 在 要 关 闭 系 统 吗 ?";
        confirmRet = JOptionPane.showConfirmDialog(null, msg, "提示", JOptionPane.YES_NO_OPTION);
        if(confirmRet == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            System.exit(0);
        }//End if(flag == JOptionPane.YES_OPTION)
        return;
    }
}
