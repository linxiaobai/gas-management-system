package com.gms.socket;

import com.gms.util.SystemConstantUtils;
import com.gms.util.threadpool.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;
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
public class SSLServer extends JFrame{
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
        this.setName(name);
        initFrame();
    }

    private void initFrame() {
        setLayout(new FlowLayout());
        jTextArea =new JTextArea(20, 17);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        this.getContentPane().add(new JScrollPane(jTextArea));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,400);
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
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                BufferedInputStream bis = new BufferedInputStream(input);
                BufferedOutputStream bos = new BufferedOutputStream(output);

                byte[] buffer = new byte[20];
                bis.read(buffer);
                System.out.println(new String(buffer));

                bos.write("Server Echo".getBytes());
                bos.flush();

                s.close();
            } catch (Exception e) {
                System.out.println(e);
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
                try {
                    dos.close();
                } catch (IOException e) {
                    logger.error("服务器端关闭数据流异常！{}",e.getMessage());
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
                while(str != null && str.length() != 0) {
                    logger.info("服务端接收到从ip为{}的客户端发送过来的消息{}",socket.getInetAddress(),str);
                    str = dis.readUTF();//少了这句话会无限输出
                    DealClientRequest dealClientRequest = DealClientRequestFactory.fetchDealClientRequest();
                    String responseStr = dealClientRequest.DealRequest(str);
                    send(responseStr);
                }
                this.dispose();
            } catch (Exception e) {
                logger.error("服务器端接收并处理客户端请求或者关闭客户端连接异常:{}",e.getMessage());
            } finally {
                this.dispose();
            }
        }

    }
}