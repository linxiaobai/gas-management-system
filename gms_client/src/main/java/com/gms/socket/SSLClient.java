package com.gms.socket;

import com.gms.util.SystemConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.security.KeyStore;

/**
 * Created by Kevin on 2015/4/17.
 */
public class SSLClient {
    private static final Logger logger = LoggerFactory.getLogger(SSLClient.class);
    /*从参数配置工具类中读取相关参数*/
    private static final String DEFAULT_HOST = SystemConstantUtils.getSystemDataSource().get("serverIp");
    private static final int DEFAULT_PORT = Integer.valueOf(SystemConstantUtils.getSystemDataSource().get("serverPort"));
    private static final String CLIENT_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("clientSecret");
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = SystemConstantUtils.getSystemDataSource().get("clientTrustKey");

    private SSLSocket sslSocket;

    /**
     * 启动客户端程序
     *
     * @param args
     */
    public static void main(String[] args) {
        SSLClient client = new SSLClient();
        client.init();
        client.process("s");
    }

    /**
     * 通过ssl socket与服务端进行连接,并且发送一个消息
     */
    public void process(String transData) {
        if (sslSocket == null) {
            System.out.println("ERROR");
            return;
        }
        try {
            InputStream input = sslSocket.getInputStream();
            OutputStream output = sslSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(input);
            DataOutputStream dos = new DataOutputStream(output);

            dos.writeUTF(transData);
            dos.flush();

            String ret = dis.readUTF();
            logger.info("登录结果：" + ret);

            sslSocket.close();
        } catch (IOException e) {
            logger.error("向服务器端请求数据出错，原因:{}", e.getMessage());
        }
    }

    /**
     * <ul>
     * <li>ssl连接的重点:</li>
     * <li>初始化SSLSocket</li>
     * <li>导入客户端私钥KeyStore，导入客户端受信任的KeyStore(服务端的证书)</li>
     * </ul>
     */
    public void init() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");

            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream(SystemConstantUtils.getSystemDataSource().get("clientKeyFile")), CLIENT_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream(SystemConstantUtils.getSystemDataSource().get("clientTrustKeyFile")), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());
            tmf.init(tks);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            sslSocket = (SSLSocket) ctx.getSocketFactory().createSocket(DEFAULT_HOST, DEFAULT_PORT);
        } catch (Exception e) {
            logger.error("初始化ssl客户端socket出错:{}",e.getMessage());
        }
    }
}
