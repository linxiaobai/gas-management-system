package com.gms.util.dbconnpool;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Kevin on 2015/3/31.
 */
public class DBManager {
    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);
    private static final String configFile = "dbcp.properties";

    private static DataSource dataSource;

    static {
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(DBManager.class.getClassLoader()
                    .getResourceAsStream(configFile));
            dataSource = BasicDataSourceFactory.createDataSource(dbProperties);

            Connection conn = getConn();
            DatabaseMetaData mdm = conn.getMetaData();
            logger.info("Connected to " + mdm.getDatabaseProductName() + " "
                    + mdm.getDatabaseProductVersion());
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error("初始化连接池失败：{}", e.getMessage());
        }
    }

    private DBManager() {
    }

    /**
     * 获取链接，用完后记得关闭
     * @return
     */
    public static final Connection getConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("获取数据库连接失败：" + e);
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param conn 需要关闭的连接
     */
    public static void closeConn(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("关闭数据库连接失败：" + e);
        }
    }

    public static DataSource fetchDataSource() {
        return dataSource;
    }
}
