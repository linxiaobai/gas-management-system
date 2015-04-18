package com.gms.mainframe;

import com.gms.bean.po.Menus;
import com.gms.swing.GmsBaseFrame;
import com.gms.swing.GmsFrameStandard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/4/18.
 * 系统主界面
 */
public class GmsMainFrame extends GmsBaseFrame implements ActionListener, GmsFrameStandard {

    private JMenu[] jMenus;
    private JMenuBar jMenuBar;
    private JPanel jPanel;

    public GmsMainFrame(String name, List<Menus> menusList) {
        super(name);
        buildMenus(menusList);
        initComponent();
        initFrame();
    }

    private void buildMenus(List<Menus> menuses) {
        List<Menus> parentsMenus = new ArrayList<Menus>();
        for (Menus menus : menuses) {
            if (menus.getMenuLevel() == 1) {
                parentsMenus.add(menus);
            }
        }
        jMenus = new JMenu[parentsMenus.size()];
        initJMenu(parentsMenus);
    }

    private void initJMenu(List<Menus> menuses) {
        for (int i = 0; i < menuses.size() ;i++) {
            jMenus[i] = new JMenu(menuses.get(i).getMenuName());
        }
    }

    @Override
    public void initComponent() {
        jMenuBar = new JMenuBar();
        jPanel = new JPanel();
        for (JMenu jMenu : jMenus) {
            jMenuBar.add(jMenu);
        }
    }

    @Override
    public void initFrame() {
        this.add(jPanel);
        this.setJMenuBar(jMenuBar);
        jPanel.setBackground(Color.green);
        this.setBounds(180, 10, 1024, 680);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
