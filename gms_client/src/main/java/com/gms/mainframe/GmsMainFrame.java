package com.gms.mainframe;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Menus;
import com.gms.bean.po.User;
import com.gms.bean.vo.TransJsonObject;
import com.gms.socket.SSLClientUtil;
import com.gms.swing.*;
import com.gms.util.ConstantsUtil;
import com.gms.util.FormatUtils;
import com.gms.util.MD5Util;
import com.gms.util.ServerRet;
import com.gms.util.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

/**
 * Created by Kevin on 2015/4/18.
 * 系统主界面
 */
public class GmsMainFrame extends GmsBaseFrame implements ActionListener,ItemListener,GmsFrameStandard {

    private static final Logger logger = LoggerFactory.getLogger(GmsMainFrame.class);
    private Set<Integer> menuIdSet = new HashSet<Integer>(); //用来存放取消勾选的菜单ID
    private GmsMenu[] gmsMenus;
    private JMenuBar jMenuBar;
    private JPanel contentPanel;
    private CardLayout cardLayout; //用cardLayout来实现panel的切换

    public GmsMainFrame(String name, List<Menus> menusList) {
        super(name);
        buildMenus(menusList);
        initComponent();
        initFrame();
    }

    /**
     * 构建菜单栏集合
     * @param menuses
     */
    private void buildMenus(List<Menus> menuses) {
        List<Menus> parentsMenus = new ArrayList<Menus>();
        /*构建子类菜单栏HashMap，key为父类ID，List为对应的ID的子类菜单栏名称的集合*/
        HashMap<Integer,List<Menus>> childrenMenus = new LinkedHashMap<Integer, List<Menus>>();
        for (Menus menus : menuses) {
            if (menus.getMenuLevel() == 1) {
                parentsMenus.add(menus);
            } else {
                /*判断某个父类菜单的子类菜单是否存在List,如果已经存在，则加入符合的子的，否则新建一个加入子的*/
                int parentId = menus.getParentId();
                List<Menus> childrenNames;
                if (childrenMenus.get(parentId) == null) {
                    childrenNames = new ArrayList<Menus>();
                } else {
                    childrenNames = childrenMenus.get(parentId);
                }
                childrenNames.add(menus);
                childrenMenus.put(parentId, childrenNames);
            }
        }

        gmsMenus = new GmsMenu[parentsMenus.size()];
        initJMenu(parentsMenus,childrenMenus);
    }

    /**
     * 初始化菜单栏，目前仅限1 2级菜单 TODO 如有需要，后期想办法扩展成3级菜单
     * @param menuses
     * @param childrenMenus
     */
    private void initJMenu(List<Menus> menuses, HashMap<Integer, List<Menus>> childrenMenus) {
        for (int i = 0; i < menuses.size(); i++) {
            gmsMenus[i] = new GmsMenu(menuses.get(i).getMenuName());
            List<Menus> childMenus= childrenMenus.get(menuses.get(i).getId());
            if (childMenus != null && childMenus.size() > 0) {
                for (Menus menus : childMenus) { //给所有的item绑定事件监听
                    GmsMenuItem gmsMenuItem = new GmsMenuItem(menus.getMenuName());
                    gmsMenuItem.setActionCommand(menus.getMenuMsg());
                    gmsMenuItem.addActionListener(this);
                    gmsMenus[i].add(gmsMenuItem);
                }
            }
        }
    }

    @Override
    public void initComponent() {
        jMenuBar = new JMenuBar();
        cardLayout = new CardLayout(5, 5);
        contentPanel = new JPanel(cardLayout);
        for (GmsMenu gmsMenu : gmsMenus) {
            jMenuBar.add(gmsMenu);
        }
    }

    @Override
    public void initFrame() {
        this.setContentPane(contentPanel);
        this.setJMenuBar(jMenuBar);
        this.setBounds(180, 10, 1024, 680);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof GmsMenuItem) { //menuItem事件处理
            String actionCommand = e.getActionCommand();
            logger.info("点击菜单栏对应指令：{}", actionCommand);

            if ("authDistribute".equals(actionCommand)) { //权限分配
                contentPanel.removeAll();
                contentPanel.add(intiAuthDisJPanel());
                contentPanel.revalidate();
                contentPanel.repaint();
            } else if ("userAdd".equals(actionCommand)) {
                contentPanel.removeAll();
                contentPanel.add(initUserAddJPanel());
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        }
    }

    /**
     * =======================
     * 权限分配布局界面  start
     * =======================
     */
    private JPanel intiAuthDisJPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.GRAY);
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.MENUS_INFO);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        if (serverRet != null) {
            if (serverRet.isRet()) {
                List<Menus> menuses = JSONObject.parseArray(serverRet.getData().toString(), Menus.class);
                jPanel = initCheckBoxList(menuses, jPanel);
            } else {
                JOptionPane.showMessageDialog(this, serverRet.getErrmsg());
            }
        }
        return jPanel;
    }

    /**
     * 初始化权限管理中所需要的checkBox界面
     */
    private JPanel initCheckBoxList(List<Menus> menuses, JPanel jPanel) {
        JPanel checkBoxPanel = new JPanel();
        JButton submitBtn = new JButton("提交");
        /*PS:please forgive me ctrl + c and ctrl + v   +.+| */
        List<Menus> parentsMenus = new ArrayList<Menus>();
        /*构建子类菜单栏HashMap，key为父类ID，List为对应的ID的子类菜单栏名称的集合*/
        HashMap<Integer,List<Menus>> childrenMenus = new LinkedHashMap<Integer, List<Menus>>();

        for (Menus menus : menuses) {
            if (menus.getIsValid() == 0) {
                menuIdSet.add(menus.getId());
            }

            if (menus.getMenuLevel() == 1) {
                parentsMenus.add(menus);
            } else {
                /*判断某个父类菜单的子类菜单是否存在List,如果已经存在，则加入符合的子的，否则新建一个加入子的*/
                int parentId = menus.getParentId();
                List<Menus> childrenNames;
                if (childrenMenus.get(parentId) == null) {
                    childrenNames = new ArrayList<Menus>();
                } else {
                    childrenNames = childrenMenus.get(parentId);
                }
                childrenNames.add(menus);
                childrenMenus.put(parentId, childrenNames);
            }
        }
        /*设置纵向*/
        checkBoxPanel.setLayout(new GridLayout(parentsMenus.size() + 1,1));

        for (Menus parentsMenu : parentsMenus) {
            /*有一组父类则实例一个JPanel*/
            JPanel oneCkBoxPanel = new JPanel();
            oneCkBoxPanel.setBorder(new TitledBorder(parentsMenu.getMenuName()));
            oneCkBoxPanel.setLayout(new BoxLayout(oneCkBoxPanel, BoxLayout.X_AXIS));

            GmsCheckBox parentCkBox = new GmsCheckBox(parentsMenu.getIsValid(), "主菜单", parentsMenu.getId());
            parentCkBox.addItemListener(this);
            oneCkBoxPanel.add(parentCkBox);
            oneCkBoxPanel.add(Box.createHorizontalStrut(20));

            List<Menus> children = childrenMenus.get(parentsMenu.getId());
            if (children != null) {
                for (Menus child : children) {
                    GmsCheckBox kidCkBox = new GmsCheckBox(child.getIsValid(), child.getMenuName(), child.getId());
                    kidCkBox.addItemListener(this); //添加监听
                    oneCkBoxPanel.add(kidCkBox);
                    oneCkBoxPanel.add(Box.createGlue());
                }
            }
            checkBoxPanel.add(oneCkBoxPanel);
        }
        final JLabel msgLab = new JLabel();

        checkBoxPanel.add(msgLab);
        checkBoxPanel.add(submitBtn);
        jPanel.add(checkBoxPanel);
        submitBtn.addActionListener(new ActionListener() { //保存权限配置
            @Override
            public void actionPerformed(ActionEvent e) {
                TransJsonObject transJsonObject = new TransJsonObject(menuIdSet, ConstantsUtil.MENUS_SETTING);
                ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                if (serverRet.isRet()) {
                    msgLab.setForeground(Color.GREEN);
                } else {
                    msgLab.setForeground(Color.RED);
                }
                msgLab.setText(serverRet.getData().toString());
            }
        });
        return jPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        GmsCheckBox gmsCheckBox = (GmsCheckBox)e.getItem();
        if (gmsCheckBox.isSelected()) { //如果未被勾选，则将对应的ID加到需要取消权限的菜单编号的集合中
            menuIdSet.remove(gmsCheckBox.getHiddenId());
        } else {
            menuIdSet.add(gmsCheckBox.getHiddenId());
        }
    }

    /**
     * =======================
     * 增加用户模块
     * =======================
     */
    private JPanel initUserAddJPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        JPanel userAddPanel = new JPanel();
        userAddPanel.setBorder(new TitledBorder("普通用户添加"));
        userAddPanel.setLayout(new GridLayout(6, 1));
        JPanel[] jPanels = new JPanel[4];
        initJPanels(jPanels);
        JLabel usernameLab = new JLabel("用户名");
        final JTextField username = new JTextField(13);
        jPanels[0].add(usernameLab);
        jPanels[0].add(username);
        JLabel passwdLab = new JLabel("登录密码");
        final JTextField passwd = new JTextField(13);
        jPanels[1].add(passwdLab);
        jPanels[1].add(passwd);
        JLabel realNameLab = new JLabel("姓名");
        final JTextField realName = new JTextField(13);
        jPanels[2].add(realNameLab);
        jPanels[2].add(realName);
        JLabel mobileLab = new JLabel("手机号");
        final JTextField mobilePhone = new JTextField(13);
        jPanels[3].add(mobileLab);
        jPanels[3].add(mobilePhone);
        final JLabel msgLab = new JLabel();
        msgLab.setForeground(Color.RED);
        JButton registerBtn = new JButton("添加");
        registerBtn.addActionListener(new ActionListener() { //提交用户信息
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = username.getText();
                String passwdText = passwd.getText();
                String realNameText = realName.getText();
                String mobilePhoneText = mobilePhone.getText();
                if (StringUtils.isBlank(usernameText) || StringUtils.isBlank(passwdText)
                        || StringUtils.isBlank(realNameText) || StringUtils.isBlank(mobilePhoneText)) {
                    msgLab.setText("文本框内值不能为空！");
                } else if(!FormatUtils.isMobileNum(mobilePhoneText)) {
                    msgLab.setText("手机号码格式不正确！");
                } else {
                    User user = new User();
                    user.setUsername(usernameText);
                    user.setUserType(ConstantsUtil.NORMAL_USER);
                    user.setPasswd(MD5Util.GetMD5Code(passwdText));
                    user.setRealName(realNameText);
                    user.setMobilePhone(mobilePhoneText);
                    user.setCreateTime(DateUtils.getCurrentTime());
                    user.setModifyTime(DateUtils.getCurrentTime());
                    TransJsonObject transJsonObject = new TransJsonObject(user, ConstantsUtil.USER_ADD);
                    ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                    if (serverRet.isRet()) {
                        msgLab.setText(serverRet.getData().toString());
                        msgLab.setForeground(Color.GREEN);
                        username.setText("");
                        passwd.setText("");
                        mobilePhone.setText("");
                        realName.setText("");
                    } else {
                        msgLab.setText(serverRet.getData().toString());
                    }
                }
            }
        });
        for (int i = 0; i < jPanels.length; i++) {
            userAddPanel.add(jPanels[i]);
        }
        userAddPanel.add(msgLab);
        userAddPanel.add(registerBtn);
        mainPanel.add(userAddPanel);
        return mainPanel;
    }



}
