package com.gms.mainframe;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.*;
import com.gms.bean.vo.DealBill;
import com.gms.bean.vo.DeviceAvgData;
import com.gms.bean.vo.TransJsonObject;
import com.gms.component.CustomTableModel;
import com.gms.socket.SSLClientUtil;
import com.gms.swing.*;
import com.gms.util.*;
import com.gms.util.date.DateUtils;
import com.gms.util.dbutil.ConvertUtil;
import com.gms.util.jfreechart.JFreeChartUtils;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Kevin on 2015/4/18.
 * 系统主界面
 */
public class GmsMainFrame extends GmsBaseFrame implements ActionListener,ItemListener,GmsFrameStandard {


    private static final Logger logger = LoggerFactory.getLogger(GmsMainFrame.class);

    private User user = null;
    private Set<Integer> menuIdSet = new HashSet<Integer>(); //用来存放取消勾选的菜单ID
    private GmsMenu[] gmsMenus;
    private JMenuBar jMenuBar;
//    private JPanel contentPanel;
    private GmsTabbedPane jTabbedPane;
//    private CardLayout cardLayout; //用cardLayout来实现panel的切换，转用GmsTabbedPane进行切换

    public GmsMainFrame(String name, User user) {
        super(name);
        this.user = user; //将登录用户信息保存在变量中
        List<Menus> menusList = null;
        try {
            menusList = fetchMenusList(user);
        } catch (GmsException e) {
            logger.error(e.getMessage());
        }
        buildMenus(menusList);
        initComponent();
        initFrame();
    }

    private List<Menus> fetchMenusList(User user) throws GmsException {
        TransJsonObject transJsonObject = new TransJsonObject(user.getUserType(), ConstantsUtil.VALID_MENUS_INFO);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        if (serverRet.isRet()) {
            return JSONObject.parseArray(serverRet.getData().toString(), Menus.class);
        } else {
            throw new GmsException("构建菜单栏异常:"+ serverRet.getErrmsg());
        }
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
//        cardLayout = new CardLayout(5, 5);
//        contentPanel = new JPanel(cardLayout);
        jTabbedPane = new GmsTabbedPane();
        for (GmsMenu gmsMenu : gmsMenus) {
            jMenuBar.add(gmsMenu);
        }
    }

    @Override
    public void initFrame() {
//        this.setContentPane(contentPanel);
        this.setContentPane(jTabbedPane);
        this.setJMenuBar(jMenuBar);
        this.setBounds(180, 10, 1024, 680);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof GmsMenuItem) { //menuItem事件处理
            String actionCommand = e.getActionCommand();
            logger.info("用户{}点击菜单栏对应指令：{}", user.getUsername(), actionCommand);
            GmsPanel gmsPanel = new GmsPanel(actionCommand);
            jTabbedPane.remove(gmsPanel);
            if ("authDistribute".equals(actionCommand)) { //权限分配
                jTabbedPane.addTab("权限分配", initAuthDisJPanel());
            } else if ("userAdd".equals(actionCommand)) { //用户添加
                jTabbedPane.addTab("用户添加", initUserAddJPanel());
            } else if ("userDel".equals(actionCommand)) { //用户删除
                jTabbedPane.addTab("用户删除", initDelUser());
            } else if ("deviceManage".equals(actionCommand)) {
                jTabbedPane.addTab("设备管理", initDeviceManage());
            } else if ("deviceMonitor".equals(actionCommand)) {
                jTabbedPane.addTab("设备监控", initDeviceMonitor());
            } else if ("environmentMonitor".equals(actionCommand)) {
                jTabbedPane.addTab("环境监控", initEnvironmentMonitor());
            } else if ("environmentChart".equals(actionCommand)) {
                jTabbedPane.addTab("环境图表显示", initEnvironmentChart());
            } else if ("deviceDataCollection".equals(actionCommand)) {
                jTabbedPane.addTab("设备数据", initDeviceDataCollection());
            } else if ("deviceRepairedRecord".equals(actionCommand)) {
                jTabbedPane.addTab("设备维修记录", initDeviceRepairedRecord());
            } else if ("deviceDataExport".equals(actionCommand)) {
                jTabbedPane.addTab("设备运行数据", initRuntimeDeviceData());
            } else if ("accountManage".equals(actionCommand)) {
                jTabbedPane.addTab("账户管理", initAccountManage());
            } else if ("payManagement".equals(actionCommand)) {
                jTabbedPane.addTab("缴费管理", initPayManagement());
            }
            jTabbedPane.setSelectedComponent(gmsPanel);
        }
    }


    /**
     * =============
     * 权限分配布局界面
     * =============
     */
    private GmsPanel initAuthDisJPanel() {
        GmsPanel jPanel = new GmsPanel("authDistribute");
        jPanel.setBackground(Color.GRAY);
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.NORMAL_USER, ConstantsUtil.MENUS_INFO);
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
    private GmsPanel initCheckBoxList(List<Menus> menuses, GmsPanel jPanel) {
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
        checkBoxPanel.setLayout(new GridLayout(parentsMenus.size() + 2,1));

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
                    msgLab.setText(serverRet.getData().toString());
                } else {
                    msgLab.setForeground(Color.RED);
                    msgLab.setText(serverRet.getErrmsg());
                }

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
     *      增加用户模块
     * =======================
     */
    private GmsPanel initUserAddJPanel() {
        GmsPanel mainPanel = new GmsPanel("userAdd");
        JPanel userAddPanel = new JPanel();
        userAddPanel.setLayout(new GridLayout(6, 1));
        JPanel[] jPanels = new JPanel[4];
        initJPanels(jPanels);
        JLabel usernameLab = new JLabel("用户名");
        final JTextField username = new JTextField(13);
        jPanels[0].add(usernameLab);
        jPanels[0].add(username);
        JLabel passwdLab = new JLabel("登录密码");
        final JPasswordField passwd = new JPasswordField(13);
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
                String passwdText = String.valueOf(passwd.getPassword());
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
                        msgLab.setText(serverRet.getErrmsg());
                        msgLab.setForeground(Color.RED);
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

    /**
     * =============
     *  删除用户模块
     * =============
     */
    private GmsPanel initDelUser() {
        GmsPanel mainPanel = new GmsPanel("userDel");
        mainPanel.setLayout(new FlowLayout());
        //获取数据库中所有普通用户信息
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.USER_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<User> users = JSONObject.parseArray(serverRet.getData().toString(), User.class);
        final JScrollPane jScrollPane = listUserTable(users);
        mainPanel.add(jScrollPane);
        final JLabel msgLab = new JLabel("");
        JButton jButton = new JButton("删除用户");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable jTable = (JTable) jScrollPane.getViewport().getView();
                int selectRowNum = jTable.getSelectedRow();
                if (selectRowNum == -1) {//-1表示没有选择任何一行
                    msgLab.setForeground(Color.RED);
                    msgLab.setText("请选择要删除的用户");
                } else {
                    msgLab.setText("已选中第"+selectRowNum+"行");
                    String username = jTable.getValueAt(jTable.getSelectedRow(),0).toString();
                    TransJsonObject transJsonObject = new TransJsonObject(username, ConstantsUtil.USER_DEL);
                    ServerRet innerServerRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                    //TODO 增加数据库交互操作
                    if (innerServerRet.isRet()) {
                        msgLab.setForeground(Color.GREEN);
                        msgLab.setText(innerServerRet.getData().toString());
                        //同步表格显示的部分，在表格中删除一行
                        ((DefaultTableModel)jTable.getModel()).removeRow(jTable.getSelectedRow());
                        validate();
                    } else {
                        msgLab.setForeground(Color.RED);
                        msgLab.setText(innerServerRet.getErrmsg());
                    }
                }
            }
        });
        mainPanel.add(jButton);
        mainPanel.add(msgLab);
        return mainPanel;
    }

    /**
     * 展示所用用户容器
     * @param users
     * @return
     */
    private JScrollPane listUserTable(List<User> users) {
        Object[][] data = ConvertUtil.listToArr(users, User.class, "username", "realName", "mobilePhone", "createTime");
        String[] names = {"用户名", "真实姓名", "手机号码", "注册时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在UserTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    /**
     * ==================
     *  设备管理模块
     * ==================
     */
    private GmsPanel initDeviceManage() {
        GmsPanel mainJPanel = new GmsPanel("deviceManage");
        mainJPanel.setLayout(new BorderLayout());
        //获取数据库中所有设备信息
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.DEVICE_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<Device> devices = JSONObject.parseArray(serverRet.getData().toString(), Device.class);
        JScrollPane listDevices = listDevicesTable(devices);
        mainJPanel.add(listDevices, BorderLayout.CENTER);
        mainJPanel.add(addDevicePanel(), BorderLayout.EAST);
        return mainJPanel;
    }

    /**
     * 展示所用用户容器
     * @param devices
     * @return
     */
    private JScrollPane listDevicesTable(List<Device> devices) {
        Object[][] data = ConvertUtil.listToArr(devices, Device.class);
        String[] names = {"设备编号", "设备名称", "当前压强", "最小压强", "最大压强", "当前温度",
                "最小温度", "最大温度", "当前水位", "最低水位", "最高水位", "是否损坏"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在CustomeTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    /**
     * 添加设备
     * @return
     */
    private JPanel addDevicePanel() {
        JPanel mainJPanel = new JPanel(new GridLayout(8,1));
        JPanel[] jPanels = new JPanel[8];
        final List<JTextField> validateJTextList = new ArrayList<JTextField>();
        initJPanels(jPanels);
        JLabel deviceNameLab = new JLabel("设备名称");
        final JTextField deviceName = new JTextField(13);
        validateJTextList.add(deviceName);
        jPanels[0].add(deviceNameLab);
        jPanels[0].add(deviceName);
        JLabel minPaLab = new JLabel("最小压强");
        final JTextField minPa = new JTextField(13);
        validateJTextList.add(minPa);
        jPanels[1].add(minPaLab);
        jPanels[1].add(minPa);
        JLabel maxPaLab = new JLabel("最大压强");
        final JTextField maxPa = new JTextField(13);
        validateJTextList.add(maxPa);
        jPanels[2].add(maxPaLab);
        jPanels[2].add(maxPa);
        JLabel minTempLab = new JLabel("最小温度");
        final JTextField minTemp = new JTextField(13);
        validateJTextList.add(minTemp);
        jPanels[3].add(minTempLab);
        jPanels[3].add(minTemp);
        JLabel maxTempLab = new JLabel("最大温度");
        final JTextField maxTemp = new JTextField(13);
        validateJTextList.add(maxTemp);
        jPanels[4].add(maxTempLab);
        jPanels[4].add(maxTemp);
        JLabel minWlLab = new JLabel("最低水位");
        final JTextField minWl = new JTextField(13);
        validateJTextList.add(minWl);
        jPanels[5].add(minWlLab);
        jPanels[5].add(minWl);
        JLabel maxWlLab = new JLabel("最高水位");
        final JTextField maxWl = new JTextField(13);
        validateJTextList.add(maxWl);
        jPanels[6].add(maxWlLab);
        jPanels[6].add(maxWl);
        JButton addDevice = new JButton("添加设备");
        final JLabel msgLab = new JLabel();
        jPanels[7].add(msgLab);
        jPanels[7].add(addDevice);
        addDevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateText(validateJTextList)) {
                    Device device = new Device();
                    device.setDeviceName(deviceName.getText().trim());
                    try {
                        device.setPaMaxVal(Double.parseDouble(maxPa.getText().trim()));
                        device.setPaMinVal(Double.parseDouble(minPa.getText().trim()));
                        device.setTempMaxVal(Double.parseDouble(maxTemp.getText().trim()));
                        device.setTempMinVal(Double.parseDouble(minTemp.getText().trim()));
                        device.setWlMaxVal(Double.parseDouble(maxWl.getText().trim()));
                        device.setWlMinVal(Double.parseDouble(minWl.getText().trim()));
                    } catch (NumberFormatException exception) { //捕获类型转换异常
                        msgLab.setText("输入数值格式有误");
                        msgLab.setForeground(Color.RED);
                        return;
                    }
                    device.setIsFailed((byte)0);
                    TransJsonObject transJsonObject = new TransJsonObject(device, ConstantsUtil.DEVICE_ADD);
                    ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                    if (serverRet.isRet()) {
                        msgLab.setText(serverRet.getData().toString());
                        msgLab.setForeground(Color.GREEN);
                        jTabbedPane.remove(new GmsPanel("deviceManage"));
                        jTabbedPane.addTab("设备管理", initDeviceManage());
                        jTabbedPane.setSelectedComponent(new GmsPanel("deviceManage"));//complete~~~
                    } else {
                        msgLab.setText(serverRet.getErrmsg());
                    }
                } else {
                    msgLab.setText("输入参数值不可为空");
                    msgLab.setForeground(Color.RED);
                }
            }
        });
        for (JPanel panel : jPanels) {
            mainJPanel.add(panel);
        }
        return mainJPanel;
    }

    /**
     * ==================
     *  设备监控模块
     * ==================
     */

    private GmsPanel initDeviceMonitor() {
        GmsPanel mainJPanel = new GmsPanel("deviceMonitor");
        //获取数据库中所有设备信息
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.DEVICE_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<Device> devices = JSONObject.parseArray(serverRet.getData().toString(), Device.class);
        if (devices == null || devices.size() == 0) {
            mainJPanel.add(new JLabel("暂无设备,可以去设备管理中添加设备"));
        } else {
            int deviceNum = devices.size();
            mainJPanel.setLayout(new GridLayout(getCeil(deviceNum), getCeil(deviceNum)));
            mainJPanel = showDevice(mainJPanel, devices);
        }
        return mainJPanel;
    }

    /**
     * 获取显示出所有device所需要的行列
     * @param size
     * @return
     */
    private int getCeil(int size) {
        return (int)Math.ceil(Math.sqrt(size));
    }

    private GmsPanel showDevice(GmsPanel gmsPanel, List<Device> devices) {
        //图片会影响到布局
        String path = PathUtil.fetchPath(this.getClass(), "pic/ranqi.png");
        ImageIcon image = new ImageIcon(path);
        JPanel[] jPanel = new JPanel[devices.size()];
        initJPanels(jPanel);
        for (int i = 0; i < devices.size(); i++) {
            jPanel[i].setLayout(new BorderLayout());
            JButton jButton = new JButton(devices.get(i).getDeviceName(), image);
            jButton.setActionCommand(devices.get(i).getId() + "," + user.getRealName());//将设备编号设置到button隐藏的command里面
            final boolean[] checkDevice = checkIsWaring(devices.get(i));
            //判断设备状态
            if (devices.get(i).getIsFailed() == 1) {
                jButton.setBackground(Color.RED);
            } else if (validateBooleanArr(checkDevice)) {
                jButton.setBackground(Color.ORANGE);
            } else {
                jButton.setBackground(Color.GREEN);
            }
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickBtn = (JButton) e.getSource();
                    if (clickBtn.getBackground() == Color.RED) {
                        int confirmRet;
                        String msg = "该设备已损坏，是否进行修理?";
                        confirmRet = JOptionPane.showConfirmDialog(null, msg, "提示", JOptionPane.YES_NO_OPTION);
                        if(confirmRet == JOptionPane.YES_OPTION) {
                            mouseSetWait(true);
                            try {
                                Thread.sleep(3000);//模拟修理过程
                            } catch (InterruptedException e1) {
                                logger.error(e1.getMessage());
                            }
                            TransJsonObject transJsonObject = new TransJsonObject(clickBtn.getActionCommand(), ConstantsUtil.DEVICE_FIX);
                            ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                            if (serverRet.isRet()) {
                                JOptionPane.showMessageDialog(null, serverRet.getData());
                                jTabbedPane.remove(new GmsPanel("deviceMonitor"));
                                jTabbedPane.addTab("设备监控", initDeviceMonitor());
                                jTabbedPane.setSelectedComponent(new GmsPanel("deviceMonitor"));
                            } else {
                                JOptionPane.showMessageDialog(null, serverRet.getErrmsg(),"设备修理失败", JOptionPane.ERROR_MESSAGE);
                            }
                            mouseSetWait(false);
                        }
                    } else if (clickBtn.getBackground() == Color.ORANGE) {
                        if (checkDevice[0]) {
                            JOptionPane.showMessageDialog(null, "设备压强过低");
                        }

                        if (checkDevice[1]) {
                            JOptionPane.showMessageDialog(null, "设备压强过高");
                        }

                        if (checkDevice[2]) {
                            JOptionPane.showMessageDialog(null, "设备温度过低");
                        }

                        if (checkDevice[3]) {
                            JOptionPane.showMessageDialog(null, "设备温度过高");
                        }

                        if (checkDevice[4]) {
                            JOptionPane.showMessageDialog(null, "设备水位过低");
                        }

                        if (checkDevice[5]) {
                            JOptionPane.showMessageDialog(null, "设备水位过高");
                        }
                    }
                }
            });
            jPanel[i].add(jButton, BorderLayout.CENTER);
        }

        for (JPanel panel : jPanel) {
            gmsPanel.add(panel);
        }
        return gmsPanel;
    }

    private boolean[] checkIsWaring(Device device) {
        boolean[] ret = new boolean[6]; //默认值均为false(正常)  下标 0 1表示压强 过低过高  2 3表示温度过低过高  4 5表示水位过低过高

        if (device.getTempVal() != null && device.getPaVal() != null || device.getWaterLevelVal() != null) {
            /*各个参数的警戒差值，即报警条件*/
            double paWaringDiffVal = (device.getPaMaxVal() - device.getPaMinVal()) * 0.1;
            double tempWaringDiffVal = (device.getTempMaxVal() - device.getTempMinVal()) * 0.1;
            double wlWaringDiffVal = (device.getWlMaxVal() - device.getWlMinVal()) * 0.1;
            if (device.getPaVal() - device.getPaMinVal() < paWaringDiffVal) {
                ret[0] = true;
            }

            if (device.getPaMaxVal() - device.getPaVal() < paWaringDiffVal) {
                ret[1] = true;
            }

            if (device.getTempVal() - device.getTempMinVal() < tempWaringDiffVal) {
                ret[2] = true;
            }

            if (device.getTempMaxVal() - device.getTempVal() < tempWaringDiffVal) {
                ret[3] = true;
            }

            if (device.getWaterLevelVal() - device.getWlMinVal() < wlWaringDiffVal) {
                ret[4] = true;
            }

            if (device.getWlMaxVal() - device.getWaterLevelVal() < wlWaringDiffVal) {
                ret[5] = true;
            }
        }
        return ret;
    }

    /**
     * 鼠标致忙
     * @param bl true 致忙  false 还原
     */
    private void mouseSetWait(boolean bl) {
        Component c = this.getRootPane().getGlassPane();
        if (c == null) {
            return;
        }
        c.setVisible(bl);
        if (bl) {
            c.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            c.addMouseListener(new MouseAdapter() {
            });
            c.addMouseMotionListener(new MouseMotionAdapter() {
            });
        } else {
            c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    /**
     * =============
     *   环境监控
     * =============
     */
    private GmsPanel initEnvironmentMonitor() {
        GmsPanel mainJPanel = new GmsPanel("environmentMonitor");
        mainJPanel.setLayout(new GridLayout(1,1));
        //获取数据库中环境信息
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.ENVIRONMENT_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<EnvironmentalParam> environmentalParams = JSONObject.parseArray(serverRet.getData().toString(), EnvironmentalParam.class);
        mainJPanel.add(listEnvironment(environmentalParams));
        return mainJPanel;
    }

    /**
     * 环境参数列表
     * @param environmentalParams
     * @return
     */
    private JScrollPane listEnvironment(List<EnvironmentalParam> environmentalParams) {
        Object[][] data = ConvertUtil.listToArr(environmentalParams, EnvironmentalParam.class, "tempVal", "feedWaterTempVal", "outWaterTempVal", "createTime");
        String[] names = {"温度(℃)", "进水温度(℃)", "出水温度(℃)", "时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在CustomeTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    /**
     * ==========================
     *  图表显示 environmentChart
     * ==========================
     */
    private GmsPanel initEnvironmentChart() {
        final GmsPanel mainJPanel = new GmsPanel("environmentChart");
        mainJPanel.setLayout(new BorderLayout());
        final CardLayout cardLayout = new CardLayout();
        final JPanel chartPanel = new JPanel(cardLayout);
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.ENVIRONMENT_CHART_DATA);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<EnvironmentalParam> environmentalParams = JSONObject.parseArray(serverRet.getData().toString(), EnvironmentalParam.class);
        chartPanel.add(getEnvironmentChartPanel(JFreeChartUtils.BAR_CHART, environmentalParams), "bar");
        chartPanel.add(getEnvironmentChartPanel(JFreeChartUtils.LINE_CHART, environmentalParams), "line");
        final JButton jButton = new JButton("切换成折线图");
        jButton.setActionCommand("line");
        jButton.setBackground(Color.CYAN);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //切换条形折线图
                cardLayout.show(chartPanel, e.getActionCommand());
                if ("bar".equals(e.getActionCommand())) {
                    jButton.setActionCommand("line");
                    jButton.setText("切换成折线图");
                    jButton.setBackground(Color.CYAN);
                } else if ("line".equals(e.getActionCommand())) {
                    jButton.setActionCommand("bar");
                    jButton.setText("切换成条形图");
                    jButton.setBackground(Color.GREEN);
                }
            }
        });
        mainJPanel.add(jButton, BorderLayout.NORTH);
        mainJPanel.add(chartPanel, BorderLayout.CENTER);
        return mainJPanel;
    }

    private JPanel getEnvironmentChartPanel(Integer chartType, List<EnvironmentalParam> environmentalParams) {
        JFreeChart jFreeChart = null;
        if (chartType == JFreeChartUtils.BAR_CHART) {
            jFreeChart = JFreeChartUtils.createBarChart(getEnvironChartData(environmentalParams), "最近环境参数图", "日期", "温度（摄氏度）");
        } else if (chartType == JFreeChartUtils.LINE_CHART) {
            jFreeChart = JFreeChartUtils.createLineChart(getEnvironChartData(environmentalParams), "最近环境参数图", "日期", "温度（摄氏度）");
        }
        return new ChartPanel(jFreeChart);
    }

    private CategoryDataset getEnvironChartData(List<EnvironmentalParam> environmentalParams) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String[] rowKeys = {"环境温度", "进水温度", "出水温度"};
        for (EnvironmentalParam environmentalParam : environmentalParams) {
            dataSet.setValue(environmentalParam.getTempVal(),rowKeys[0], environmentalParam.getCreateTime());
            dataSet.setValue(environmentalParam.getFeedWaterTempVal(), rowKeys[1], environmentalParam.getCreateTime());
            dataSet.setValue(environmentalParam.getOutWaterTempVal(), rowKeys[2], environmentalParam.getCreateTime());
        }
        return dataSet;
    }

    /**
     * ==================
     *    设备数据采集
     * ==================
     */
    private GmsPanel initDeviceDataCollection() {
        GmsPanel mainPanel = new GmsPanel("deviceDataCollection");
        mainPanel.setLayout(new GridLayout(1,1));
        mainPanel.add(getDeviceDataChartPanel());
        return mainPanel;
    }


    private JPanel getDeviceDataChartPanel() {
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.DEVICE_CHART_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<DeviceAvgData> deviceAvgDataList = JSONObject.parseArray(serverRet.getData().toString(), DeviceAvgData.class);
        JFreeChart jFreeChart =  JFreeChartUtils.createBarChart(getDeviceChartData(deviceAvgDataList), "最近设备参数图", "日期", "");
        return new ChartPanel(jFreeChart);
    }

    private CategoryDataset getDeviceChartData(List<DeviceAvgData> deviceAvgDataList) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String[] rowKeys = {"平均压强值/Pa", "平均温度/℃", "平均水位/m"};
        String prefix = "设备编号:";
        for (DeviceAvgData deviceAvgData : deviceAvgDataList) {
            dataSet.setValue(FormatUtils.formatDecimal(deviceAvgData.getAvgPaVal(), 1), rowKeys[0], prefix + deviceAvgData.getDeviceId());
            dataSet.setValue(FormatUtils.formatDecimal(deviceAvgData.getAvgTempVal(), 1), rowKeys[1], prefix + deviceAvgData.getDeviceId());
            dataSet.setValue(FormatUtils.formatDecimal(deviceAvgData.getAvgWaterLevelVal(), 1), rowKeys[2], prefix + deviceAvgData.getDeviceId());
        }
        return dataSet;
    }

    /**
     * ====================
     *     设备维修记录
     * ====================
     */
    private GmsPanel initDeviceRepairedRecord() {
        GmsPanel mainPanel = new GmsPanel("deviceRepairedRecord");
        mainPanel.setLayout(new GridLayout(2,1));
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.DEVICE_REPAIRED_RECORD_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<RepairedRecord> repairedRecordList = JSONObject.parseArray(serverRet.getData().toString(), RepairedRecord.class);
        mainPanel.add(listDeviceRepairedRecord(repairedRecordList));
        mainPanel.add(getDeviceRepairedRecordChartPanel(repairedRecordList));
        return mainPanel;
    }

    private JScrollPane listDeviceRepairedRecord(List<RepairedRecord> repairedRecordList) {
        Object[][] data = ConvertUtil.listToArr(repairedRecordList, RepairedRecord.class);
        String[] names = {"序号", "设备编号", "处理修理人员", "处理修理时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在CustomeTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    private JPanel getDeviceRepairedRecordChartPanel(List<RepairedRecord> repairedRecordList) {
        Map<Integer,Integer> repairedRecordCount = new HashMap<Integer, Integer>();
        for (RepairedRecord repairedRecord : repairedRecordList) {
            Integer deviceId = repairedRecord.getDeviceId();
            Integer count = repairedRecordCount.get(deviceId);
            if (count != null) {
                repairedRecordCount.put(deviceId, ++count);
            } else {
                repairedRecordCount.put(deviceId, 1);
            }
        }
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String prefix = "设备编号:";
        Iterator<Map.Entry<Integer, Integer>> iterEntry = repairedRecordCount.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iterEntry.hasNext()) {
            entry = iterEntry.next();
            Integer deviceId = entry.getKey();
            Integer count = entry.getValue();
            dataSet.setValue(count, "设备维修次数", prefix + deviceId);
        }
        JFreeChart jFreeChart =  JFreeChartUtils.createBarChartInt(dataSet, "设备维修次数", "设备编号", "修理的次数");
        return new ChartPanel(jFreeChart);
    }

    /**
     * =======================
     *      设备运行时数据
     * =======================
     */
    private GmsPanel initRuntimeDeviceData() {
        GmsPanel mainPanel = new GmsPanel("deviceDataExport");
        mainPanel.setLayout(new BorderLayout());
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.RUNTIME_DEVICE_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<RuntimeDevice> runtimeDevices = JSONObject.parseArray(serverRet.getData().toString(), RuntimeDevice.class);
        final JScrollPane jScrollPane = listRuntimeDeviceData(runtimeDevices);
        JButton jbutton = new JButton("导出数据");
        jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable jTable = (JTable) jScrollPane.getViewport().getView();
                try {
                    ExcelUtil.exportTable(jTable, new File("运行设备数据.xls"));
                } catch (IOException e1) {
                    logger.error("生成excel表格异常！");
                }
                JOptionPane.showMessageDialog(null, "数据导出成功！");
            }
        });
        mainPanel.add(jbutton, BorderLayout.NORTH);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private JScrollPane listRuntimeDeviceData(List<RuntimeDevice> runtimeDevices) {
        Object[][] data = ConvertUtil.listToArr(runtimeDevices, RuntimeDevice.class);
        String[] names = {"序号", "设备编号", "压强值(/Pa)", "温度(/℃)","水位(/m)", "时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在CustomeTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    /**
     * ===========================
     *          账户管理
     * ===========================
     */
    private GmsPanel initAccountManage() {
        GmsPanel mainPanel = new GmsPanel("accountManage");
        mainPanel.setLayout(new BorderLayout());
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.FINANCE_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<Finance> finances = JSONObject.parseArray(serverRet.getData().toString(), Finance.class);
        JScrollPane jScrollPane = listFinanceData(finances);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
        JLabel financeTotal = new JLabel();
        financeTotal.setSize(new Dimension(40,30));
        double total = 0;
        JTable jTable = (JTable) jScrollPane.getViewport().getView();
        int rowCount = jTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            total += (Double)jTable.getValueAt(i,1);
        }
        logger.info("公司账户费用合计：" + total);
        if (total > 0) { //如果费用合计最后结果大于0则显示绿色，否则显示红色
            financeTotal.setForeground(Color.BLACK);
        } else {
            financeTotal.setForeground(Color.RED);
        }
        financeTotal.setText("费用合计：" + total);
        mainPanel.add(financeTotal, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JScrollPane listFinanceData(List<Finance> finances) {
        Object[][] data = ConvertUtil.listToArr(finances, Finance.class);
        String[] names = {"序号", "费用", "费用明细", "时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在CustomeTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    /**
     * ======================
     *       缴费管理
     * ======================
     */
    private GmsPanel initPayManagement() {
        GmsPanel mainPanel = new GmsPanel("payManagement");
        mainPanel.setLayout(new GridLayout(2,1));
        JButton orderCreateBtn = new JButton("订单创建");
        orderCreateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTabbedPane.remove(new GmsPanel("payManagement"));
                GmsPanel temp = orderCreatePanel();
                jTabbedPane.addTab("订单创建",temp);
                jTabbedPane.setSelectedComponent(temp);
            }
        });
        JButton payOrderBtn = new JButton("缴费");
        payOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTabbedPane.remove(new GmsPanel("payManagement"));
                GmsPanel temp = payBillPanel();
                jTabbedPane.addTab("缴费",temp);
                jTabbedPane.setSelectedComponent(temp);
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(orderCreateBtn);
        btnPanel.add(payOrderBtn);
        mainPanel.add(btnPanel);
        JTextPane statement = new JTextPane();
        statement.setContentType("text/html");
        statement.setText(ConstantsUtil.PAY_STATEMENT_STR);
        statement.setEnabled(false);
        mainPanel.add(statement);
        return mainPanel;
    }

    /**
     * 订单创建界面
     */
    private GmsPanel orderCreatePanel() {
        GmsPanel mainPanel = new GmsPanel("payManagement", new GridLayout(6,1));
        JPanel[] jPanels = new JPanel[5];
        initJPanels(jPanels);
        //用于判断文本框是否为空
        final List<JTextField> validateJTextList = new ArrayList<JTextField>();
        /*改为点击创建按钮时促发订单编号生成事件*/
//        JLabel orderSeqLab = new JLabel("订单编号:");
//        //订单编号唯一生成，从服务器端获取
//        final JTextField orderText = new JTextField(13);
//        validateJTextList.add(orderText);
//        orderText.setEnabled(false);
//        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.FETCH_ORDER_SEQ);
//        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
//        orderText.setText(serverRet.getData().toString());
//        jPanels[0].add(orderSeqLab);
//        jPanels[0].add(orderText);
        JLabel phoneLab = new JLabel("缴费者手机号:");
        final JTextField phoneText = new JTextField(13);
        validateJTextList.add(phoneText);
        jPanels[0].add(phoneLab);
        jPanels[0].add(phoneText);
        JLabel shdPayLab = new JLabel("应付金额:");
        final JTextField shdPayText = new JTextField(13);
        validateJTextList.add(shdPayText);
        jPanels[1].add(shdPayLab);
        jPanels[1].add(shdPayText);
        JLabel handlerLab = new JLabel("处理人员姓名:");
        final Long handlerId = user.getId();
        final JTextField handlerText = new JTextField(13);
        validateJTextList.add(handlerText);
        handlerText.setEnabled(false);
        handlerText.setText(user.getRealName());
        jPanels[2].add(handlerLab);
        jPanels[2].add(handlerText);
        JLabel gasAmountLab = new JLabel("燃气使用量:");
        final JTextField gasAmountText = new JTextField(13);
        validateJTextList.add(gasAmountText);
        jPanels[3].add(gasAmountLab);
        jPanels[3].add(gasAmountText);
        JButton submitBtn = new JButton("创建");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateText(validateJTextList)) {
                    if(!FormatUtils.isMobileNum(phoneText.getText())) {
                        JOptionPane.showMessageDialog(null, "手机号码格式不正确!");
                        return;
                    }
                    Bill bill = new Bill();
                    bill.setStatus((byte)0);
                    bill.setGasUseAmount(Double.valueOf(gasAmountText.getText()));
                    bill.setPayerPhone(phoneText.getText());
                    bill.setHandlerName(handlerText.getText());
                    bill.setHandlerId(handlerId);
                    bill.setShdPayMoney(Double.valueOf(shdPayText.getText()));
                    TransJsonObject billData = new TransJsonObject(bill, ConstantsUtil.CREATE_ORDER);
                    ServerRet createOrderRet = SSLClientUtil.sendAndReciveMsg(billData);
                    if (createOrderRet.isRet()) {
                        JOptionPane.showMessageDialog(null, createOrderRet.getData().toString());
                        jTabbedPane.remove(new GmsPanel("payManagement"));
                        jTabbedPane.addTab("订单创建",orderCreatePanel());
                        jTabbedPane.setSelectedComponent(new GmsPanel("payManagement"));
                    } else {
                        JOptionPane.showMessageDialog(null, createOrderRet.getErrmsg());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "文本框内数值不可以为空！");
                }
            }
        });
        jPanels[4].add(submitBtn);
        for (JPanel jPanel : jPanels) {
            mainPanel.add(jPanel);
        }
        return mainPanel;
    }

    /**
     * 缴费页面
     */
    public GmsPanel payBillPanel() {
        GmsPanel mainPanel = new GmsPanel("payManagement");
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.UNPAID_BILL_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<Bill> bills = JSONObject.parseArray(serverRet.getData().toString(), Bill.class);
        mainPanel.setLayout(new BorderLayout());
        final JScrollPane jScrollPane = listBillTable(bills);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
        JButton confirmPayBtn = new JButton("确认缴费");
        confirmPayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable jTable = (JTable) jScrollPane.getViewport().getView();
                int selectRowNum = jTable.getSelectedRow();
                if (selectRowNum == -1) {//-1表示没有选择任何一行
                    JOptionPane.showMessageDialog(null, "请选择要处理缴费的订单！");
                } else {
                    String orderSequence = jTable.getValueAt(jTable.getSelectedRow(),0).toString();
                    Double payMoney = Double.valueOf(jTable.getValueAt(jTable.getSelectedRow(),2).toString());
                    DealBill dealBill = new DealBill();
                    dealBill.setCode(ConstantsUtil.DEAL_BILL);
                    dealBill.setOrderSequence(orderSequence);
                    dealBill.setPayMoney(payMoney);
                    TransJsonObject transJsonObject = new TransJsonObject(dealBill, ConstantsUtil.DEAL_BILL);
                    ServerRet innerServerRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
                    //TODO 增加数据库交互操作
                    if (innerServerRet.isRet()) {
                        JOptionPane.showMessageDialog(null, innerServerRet.getData().toString());
                        //同步表格显示的部分，在表格中删除一行
                        ((DefaultTableModel)jTable.getModel()).removeRow(jTable.getSelectedRow());
                        validate();
                    } else {
                        JOptionPane.showMessageDialog(null, innerServerRet.getErrmsg());
                    }
                }
            }
        });
        mainPanel.add(confirmPayBtn, BorderLayout.SOUTH);
        return mainPanel;
    }

    /**
     * 展示所用订单容器
     * @param bills
     * @return
     */
    private JScrollPane listBillTable(List<Bill> bills) {
        Object[][] data = ConvertUtil.listToArr(bills, Bill.class, "billSequence", "payerPhone", "shdPayMoney", "handlerName", "gasUseAmount", "createTime");
        String[] names = {"订单编号", "缴费者手机号", "应缴费金额", "订单处理人员", "燃气使用量", "注册时间"};
        CustomTableModel myTableModel = new CustomTableModel(data, names);
        JTable jTable = new JTable(myTableModel);
        //在UserTableModel里重写了父类的isCellEditable保证其不可编辑，在这里设置true仅使其可以选择
        jTable.setEnabled(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

}
