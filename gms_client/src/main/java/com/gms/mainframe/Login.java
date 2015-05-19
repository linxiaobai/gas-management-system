package com.gms.mainframe;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.bean.vo.TransJsonObject;
import com.gms.socket.SSLClientUtil;
import com.gms.swing.GmsBaseFrame;
import com.gms.swing.GmsFrameStandard;
import com.gms.util.ConstantsUtil;
import com.gms.util.MD5Util;
import com.gms.util.ServerRet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Created by Kevin on 2015/4/9.
 * 登录界面
 */
public class Login extends GmsBaseFrame implements ActionListener,KeyListener,GmsFrameStandard {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    private JLabel usernameLab;
    private JLabel passwordLab;
    private JLabel msgLab;
    private JButton loginBtn;
    private JButton resetBtn;
    private JPasswordField jPasswordField;
    private JTextField usernameTextField;
    private JPanel mainJPanel;
    private JPanel[] jPanels;
    private String clue = "  提 示 :  ";

    public Login(String name) {
        super(name);
        initComponent();
        initFrame();
    }

    public Login(String name, int width, int height) {
        this(name);
        this.setSize(width, height);
    }

    @Override
    public void initComponent() {
        usernameLab = new JLabel("用户名:");
        passwordLab = new JLabel("密  码:");
        msgLab = new JLabel();
        msgLab.setForeground(Color.RED);
        loginBtn = new JButton("登录");
        resetBtn = new JButton("重置");
        loginBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        jPasswordField = new JPasswordField(12);
        usernameTextField = new JTextField(12);
        mainJPanel = new JPanel(new GridLayout(3,1));
        jPanels =  new JPanel[3];
        initJPanels(jPanels);
        jPanels[0].add(usernameLab);
        jPanels[0].add(usernameTextField);
        jPanels[1].add(passwordLab);
        jPanels[1].add(jPasswordField);
        jPanels[1].add(msgLab);
        jPanels[2].add(loginBtn);
        jPanels[2].add(resetBtn);
        for (int i = 0; i < jPanels.length; i++) {
            mainJPanel.add(jPanels[i]);
        }
    }

    @Override
    public void initFrame() {
        this.add(mainJPanel);
        this.setResizable(false);
        //移到startWindow里面
//        this.setVisible(true);
//        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            logger.info("用户点击登录按钮");
            if (validateLogin()) { //校验登录信息
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setUsername(usernameTextField.getText().trim());
                /*密码用md5处理一下再传到server端*/
                loginInfo.setPassword(MD5Util.GetMD5Code(new String(jPasswordField.getPassword())));
                TransJsonObject transJsonObject = new TransJsonObject(loginInfo, ConstantsUtil.LOGIN_INFO);
                ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                if (serverRet != null) {
                    if (serverRet.isRet()) {
                        this.setVisible(false);
//                        List<Menus> menuses = JSONObject.parseArray(serverRet.getData().toString(), Menus.class);
                        User user = JSONObject.parseObject(serverRet.getData().toString(), User.class);
                        new GmsMainFrame("自动化燃气管理系统", user);
                    } else {
                        msgLab.setText(clue + serverRet.getErrmsg());
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"服务器端连接失败！");
                }
            }

        } else if (e.getSource() == resetBtn) { //用户点击重置按钮清空文本内容
            usernameTextField.setText("");
            jPasswordField.setText("");
        }
    }

    private boolean validateLogin() {
        String username = usernameTextField.getText().trim();
        String password = new String(jPasswordField.getPassword());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            msgLab.setText(clue + "用户名或者密码不能为空！");
            return false;
        }
        return true;
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
