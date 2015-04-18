package com.gms.mainframe;

import com.gms.socket.SSLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kevin on 2015/4/17.
 */
public class StartServer {
    private static final Logger logger = LoggerFactory.getLogger(StartServer.class);
    /**
     * 启动程序
     * @param args
     */
    public static void main(String[] args) {
        SSLServer server = new SSLServer("自动化燃气管理系统服务器端");
        logger.warn("警告程序员！！！项目启动啦！{}");
        server.init();
        server.start();
    }
}
