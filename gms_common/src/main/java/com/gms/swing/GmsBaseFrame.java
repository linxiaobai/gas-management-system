package com.gms.swing;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Created by Kevin on 2015/4/18.
 * 系统frame界面的父类，包含退出系统确认方法
 */
public class GmsBaseFrame extends JFrame{

    public GmsBaseFrame(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //加窗口监听 new WindowAdapter适配器类
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                quit();
            }//End windowClosing
        });
    }

    protected void quit() {
        int confirmRet;
        String msg = "您 现 在 要 关 闭 系 统 吗 ?";
        confirmRet = JOptionPane.showConfirmDialog(null, msg, "提示", JOptionPane.YES_NO_OPTION);
        //不知道为什么在主界面点击关闭按钮时，对应值会被重置为3,导致点否仍然关闭，所以在这里重新赋值一下
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if(confirmRet == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            System.exit(0);
        }//End if(flag == JOptionPane.YES_OPTION)
        return;
    }

    /**
     * 初始化panel数组
     * @param jPanels
     */
    protected void initJPanels(JPanel[] jPanels) {
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel();
        }
    }

    protected void initJLabels(JLabel[] jLabels, String[] names) {
        if (jLabels.length != names.length) {
            return;
        }
        for (int i = 0; i < jLabels.length; i++) {
            jLabels[i] = new JLabel(names[i]);
        }
    }

    /**
     * 校验文本框
     * TODO 其他校验规则，例如浮点数非文本等等
     * @return false  有误， true 正确
     */
    protected boolean validateText(List<JTextField> jTextFields) {
        if (jTextFields == null || jTextFields.size() == 0) {
            return false;
        }
        boolean pass = true;
        for (JTextField jTextField : jTextFields) { //暂时只判断是否非空
            if (StringUtils.isBlank(jTextField.getText())) {
                pass = false;
                break;
            }
        }
        return pass;
    }

    /**
     * 如果数组中有一个值为true那么就返回true
     * 即设备的三个参数值有一个达到临界值那么就判断该设备处于警告状态
     */
    protected boolean validateBooleanArr(boolean[] array) {
        boolean ret = false;
        for (boolean b : array) {
            ret = ret | b;
        }
        return ret;
    }
}
