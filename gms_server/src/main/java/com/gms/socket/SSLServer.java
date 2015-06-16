package com.gms.socket;

import com.gms.service.SystemParamService;
import com.gms.service.impl.SystemParamServiceImpl;
import com.gms.swing.GmsBaseFrame;
import com.gms.timer.TimerStart;
import com.gms.util.ConstantsUtil;
import com.gms.util.SystemConstantUtils;
import com.gms.util.date.DateUtils;
import com.gms.util.threadpool.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;


/**
 * Created by Kevin on 2015/4/16.
 * <ul>
 * <li>1)生成服务端私钥</li>
 * <li>keytool -genkey -alias serverkey -keystore kserver.keystore</li>
 * <li>2)根据私钥,导出服务端证书</li>
 * <li>keytool -exoport -alias serverkey -keystore kserver.keystore -file server.crt</li>
 * <li>3)把证书加入到客户端受信任的keystore中</li>
 * <li>keytool -import -alias serverkey -file server.crt -keystore tclient.keystore</li>
 * </ul>
 */
public class SSLServer extends GmsBaseFrame{
    private static Logger logger = LoggerFactory.getLogger(SSLServer.class);
    private SystemParamService systemParamService = new SystemParamServiceImpl();

    /*swing 成员变量*/
    private JTextArea jTextArea = null;
    private JButton jButton = null;
    private JPanel mainPanel = null;
    /*timer*/
    private Timer timer = null;

    /*ssl服务器相关参数,从配置参数工具类中读取*/
    private static final int DEFAULT_PORT = Integer.valueOf(SystemConstantUtils.getSystemDataSource().get("serverPort"));
    private static final String SERVER_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("serverSecret");
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("serverTrustKey");;
    private SSLServerSocket serverSocket;
    private LinkedList<ClientConn> clients = new LinkedList<ClientConn>(); //存放客户端连接对象


    protected void quit() {
        int confirmRet;
        String msg = "您 现 在 要 关 闭 系 统 吗 ?";
        confirmRet = JOptionPane.showConfirmDialog(null, msg, "提示", JOptionPane.YES_NO_OPTION);
        //不知道为什么在主界面点击关闭按钮时，对应值会被重置为3,导致点否仍然关闭，所以在这里重新赋值一下
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if(confirmRet == JOptionPane.YES_OPTION) {
            systemParamService.updateSequence();
            setVisible(false);
            System.exit(0);
        }//End if(flag == JOptionPane.YES_OPTION)
        return;
    }

    public SSLServer(String name) {
        super(name);
        initFrame();
    }

    private void initFrame() {
        this.setLayout(new FlowLayout());
        mainPanel = new JPanel(new BorderLayout());
        jTextArea =new JTextArea(40, 34);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        mainPanel.add(new JScrollPane(jTextArea), BorderLayout.CENTER);
        jButton = new JButton("启动数据模拟");
        jButton.setBackground(Color.GREEN);
        jButton.setSize(new Dimension(60,40));
        jButton.setActionCommand("startTimer");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButton) {
                    if ("startTimer".equals(jButton.getActionCommand())) {
                        timer = new Timer(true);
                        //服务器端跑一个定时任务模拟数据的插入
                        ThreadPoolManager.getThreadPool().execute(new TimerStart(timer));
                        jButton.setText("停止数据模拟");
                        jButton.setActionCommand("stopTimer");
                        jButton.setBackground(Color.RED);
                    } else if("stopTimer".equals(jButton.getActionCommand())) {
                        timer.cancel();
                        jButton.setText("启动数据模拟");
                        jButton.setActionCommand("startTimer");
                        jButton.setBackground(Color.GREEN);
                    }
                }
            }
        });
        mainPanel.add(jButton,BorderLayout.NORTH);
        this.getContentPane().add(mainPanel);
        setVisible(true);
        setSize(400,600);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void start() {
        if (serverSocket == null) {
            System.out.println("ERROR");
            return;
        }
        while (true) {
            try {
                Socket s = serverSocket.accept();
                clients.add(new ClientConn(s));
                jTextArea.append("一个客户端已连接!\n");
                jTextArea.append("对应ip:" +  s.getInetAddress() + ";对应端口号:" + s.getPort() + ";\n登录时间:"
                        + DateUtils.formatDateTime(new Date()) + "\n");
                jTextArea.append("当前客户端连接数：" + clients.size() + "\n");
            } catch (Exception e) {
                logger.error("服务器端接收客户端socket报错，原因：{}", e.getMessage());
            }
        }
    }

    /**
     * <ul>
     * <li>ssl连接的重点:</li>
     * <li>初始化SSLServerSocket</li>
     * <li>导入服务端私钥KeyStore，导入服务端受信任的KeyStore(客户端的证书)</li>
     * </ul>
     */
    public void init() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");

            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream(SystemConstantUtils.getSystemDataSource().get("serverKeyFile")), SERVER_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream(SystemConstantUtils.getSystemDataSource().get("serverTrustKeyFile")), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
            tmf.init(tks);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(DEFAULT_PORT);
            serverSocket.setNeedClientAuth(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    class ClientConn implements Runnable{ //内部客户端连接类
        private Socket socket;
        public ClientConn(Socket socket) {
            this.socket = socket;
            //加入到线程池的工作队列中防止多用户时线程阻塞
            ThreadPoolManager.getThreadPool().execute(this);
        }
        private void send(String msg) {
            //socket支持的最大传输数据大小为8M
            DataOutputStream dos = null;
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                if (msg.length() > ConstantsUtil.PACKAGE_SIZE) { //超过数据包数据后对数据进行拆解
                    int part = (int)Math.ceil((double) msg.length() / ConstantsUtil.PACKAGE_SIZE);
                    dos.writeUTF(String.valueOf(part));
                    dos.flush();
                    int mod = 0;
                    if (part * ConstantsUtil.PACKAGE_SIZE > msg.length()) {
                        mod = msg.length() % ConstantsUtil.PACKAGE_SIZE;
                    }
                    int count = 0;
                    while (count < part) {
                        if (count == part - 1) {
                            String tempStr = msg.substring(0,mod);
                            dos.writeUTF(tempStr);
                            dos.flush();
                        } else {
                            String tempStr = msg.substring(0,ConstantsUtil.PACKAGE_SIZE);
                            msg = msg.substring(ConstantsUtil.PACKAGE_SIZE);
                            dos.writeUTF(tempStr);
                            dos.flush();
                        }
                        count++;
                    }
                } else {
                    dos.writeUTF(String.valueOf(1));
                    dos.flush();
                    dos.writeUTF(msg);
                    dos.flush();
                }
            } catch (IOException e) {
                logger.error("服务器端发送信息异常，原因{}",e.getMessage());
            } finally {
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (IOException e) {
                        logger.error("服务器端关闭输出流出错!原因：{}", e.getMessage());
                    }
                }
            }
        }

        private void dispose() {
            try {
                if (socket != null) socket.close();
                clients.remove(this);
                jTextArea.append("一个客户端已经下线! \n");
                jTextArea.append("当前客户端连接数" + clients.size() + "\n\n");
            } catch (Exception e) {
                logger.error("客户端下线时异常:{}",e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                String str = dis.readUTF();
                //TODO 这里用if能否读完所有的数据
                if (str != null && str.length() != 0) {
                    logger.info("服务端接收到从ip为{}的客户端发送过来的消息{}",socket.getInetAddress(),str);
                    DealClientRequest dealClientRequest = DealClientRequestFactory.fetchDealClientRequest();
                    String responseStr = dealClientRequest.DealRequest(str);
                    logger.info("处理并返回数据：{}",responseStr);
                    send(responseStr);
                }
            } catch (Exception e) {
                logger.error("服务器端接收并处理客户端请求或者关闭客户端连接异常:{}",e.getMessage());
            } finally {
                this.dispose();
            }
        }
    }
}