package com.gms.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Kevin on 2015/4/17.
 * 相关参数存放类，从config.properties中读取出来存放到一个hashMap中
 * 对应的服务器端ip，端口号以及密钥的配置参数
 */
public class SystemConstantUtils {

    private static final Logger logger = LoggerFactory.getLogger(SystemConstantUtils.class);
    private static Map<String,String> systemDataSource = new HashMap<String,String>();
    private static final String configFile = "config.properties";

    /**
     * 服务器端
     */
    private static final String GMS_SERVER_KEY = "gmsserverkey.keystore"; //存有服务端私钥服务端KeyStore文件
    private static final String GMS_SERVER_TRUST_KEY = "gmsserver.keystore"; //导入服务端证书的客户端的Trust KeyStore

    /**
     * 客户端
     */
    private static final String GMS_CLIENT_KEY = "gmsclientkey.keystore"; //存有私钥客户端KeyStore文件
    private static final String GMS_CLIENT_TRUST_KEY = "gmsclient.keystore"; //导入客户端证书的服务端的Trust KeyStore

    static {
        Properties configParams = new Properties();
        try {
            configParams.load(SystemConstantUtils.class.getClassLoader()
                    .getResourceAsStream(configFile));
            systemDataSource.put("serverIp", configParams.getProperty("serverIp"));
            systemDataSource.put("serverPort", configParams.getProperty("serverPort"));
            systemDataSource.put("serverSecret", configParams.getProperty("serverSecret"));
            systemDataSource.put("clientSecret", configParams.getProperty("clientSecret"));
            systemDataSource.put("clientTrustKey", configParams.getProperty("clientTrustKey"));
            systemDataSource.put("serverTrustKey", configParams.getProperty("serverTrustKey"));
            /*ssl相关密钥文件*/
            systemDataSource.put("serverKeyFile", configParams.getProperty("secretPath") + GMS_SERVER_KEY);
            systemDataSource.put("serverTrustKeyFile", configParams.getProperty("secretPath") + GMS_SERVER_TRUST_KEY);
            systemDataSource.put("clientKeyFile", configParams.getProperty("secretPath") + GMS_CLIENT_KEY);
            systemDataSource.put("clientTrustKeyFile", configParams.getProperty("secretPath") + GMS_CLIENT_TRUST_KEY);

        } catch (Exception e) {
            logger.error("初始化系统参数出错,原因:{}",e.getMessage());
        }
    }

    public static Map<String, String> getSystemDataSource() {
        return systemDataSource;
    }
}
