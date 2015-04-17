package com.gms.mainframe;

import com.gms.socket.SSLServer;

/**
 * Created by Kevin on 2015/4/17.
 */
public class StartServer {
    /**
     * 启动程序
     * @param args
     */
    public static void main(String[] args) {
        SSLServer server = new SSLServer("sslServer");
        server.init();
        server.start();
    }
}
