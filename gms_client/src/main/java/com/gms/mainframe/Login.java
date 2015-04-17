package com.gms.mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Kevin on 2015/4/9.
 * 登录界面
 */
public class Login extends JFrame implements ActionListener,KeyListener{

    private JLabel usernameLab;
    private JLabel passwordLab;
    private JButton loginBtn;
    private JButton resetBtn;
    private JPasswordField jPasswordField;
    private JTextField usernameTextField;
    private JPanel mainJPanel;
    private JPanel[] jPanels;

    public Login(String name) {
        super(name);
        initComponent();
        initFrame();
    }

    public Login(String name, int width, int height) {
        this(name);
        this.setSize(width,height);
    }

    private void initComponent() {
        usernameLab = new JLabel("用户名:");
        passwordLab = new JLabel("密码:");
        loginBtn = new JButton("登录");
        resetBtn = new JButton("重置");
        jPasswordField = new JPasswordField(13);
        usernameTextField = new JTextField(12);
        mainJPanel = new JPanel(new GridLayout(3,1));
        jPanels =  new JPanel[3];
        initJPanels(jPanels);
        jPanels[0].add(usernameLab);
        jPanels[0].add(usernameTextField);
        jPanels[1].add(passwordLab);
        jPanels[1].add(jPasswordField);
        jPanels[2].add(loginBtn);
        jPanels[2].add(resetBtn);
        for (int i = 0; i < jPanels.length; i++) {
            mainJPanel.add(jPanels[i]);
        }
    }

    private void initFrame() {
        this.add(mainJPanel);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        //加窗口监听 new WindowAdapter适配器类
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
//                quit();
            }//End windowClosing
        });
    }

    private void initJPanels(JPanel[] jPanels) {
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel();
        }
    }

    private void quit() {
        int confirmRet;
        String msg = "您 现 在 要 关 闭 系 统 吗 ?";
        confirmRet = JOptionPane.showConfirmDialog(null, msg, "提示", JOptionPane.YES_NO_OPTION);
        if(confirmRet == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            System.exit(0);
        }//End if(flag == JOptionPane.YES_OPTION)
        return;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {

        }

        if (e.getSource() == resetBtn) { //用户点击重置按钮清空文本内容
            usernameTextField.setText("");
            jPasswordField.setText("");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
