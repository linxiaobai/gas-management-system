package com.gms.socket;

import com.gms.swing.GmsBaseFrame;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Date;
import java.util.LinkedList;


/**
 * Created by Kevin on 2015/4/16.
 * <ul>
 * <li>1)生成服务端私钥</li>
 * <li>keytool -genkey -alias serverkey -keystore kserver.keystore</li>
 * <li>2)根据私钥,到处服务端证书</li>
 * <li>keytool -exoport -alias serverkey -keystore kserver.keystore -file server.crt</li>
 * <li>3)把证书加入到客户端受信任的keystore中</li>
 * <li>keytool -import -alias serverkey -file server.crt -keystore tclient.keystore</li>
 * </ul>
 */
public class SSLServer extends GmsBaseFrame{
    private static Logger logger = LoggerFactory.getLogger(SSLServer.class);


    /*swing 成员变量*/
    private JTextArea jTextArea = null;

    /*ssl服务器相关参数,从配置参数工具类中读取*/
    private static final int DEFAULT_PORT = Integer.valueOf(SystemConstantUtils.getSystemDataSource().get("serverPort"));
    private static final String SERVER_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("serverSecret");
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("serverTrustKey");;
    private SSLServerSocket serverSocket;
    private LinkedList<ClientConn> clients = new LinkedList<ClientConn>(); //存放客户端连接对象

    public SSLServer(String name) {
        super(name);
        initFrame();
    }

    private void initFrame() {
        setLayout(new FlowLayout());
        jTextArea =new JTextArea(40, 34);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        this.getContentPane().add(new JScrollPane(jTextArea));
        setVisible(true);
        setSize(400,600);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * <ul>
     * <li>听SSL Server Socket</li>
     * <li> 由于该程序不是演示Socket监听，所以简单采用单线程形式，并且仅仅接受客户端的消息，并且返回客户端指定消息</li>
     * </ul>
     */
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
            ThreadPoolManager.getThreadPool().execute(this);
        }

        private void send(String msg) {
            DataOutputStream dos = null;
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(msg);
            } catch (IOException e) {
                logger.error("客户端发送信息异常，原因{}",e.getMessage());
            } finally {
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (IOException e) {
                        logger.error("客户端关闭输出流出错!原因：{}", e.getMessage());
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