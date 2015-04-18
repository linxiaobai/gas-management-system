package com.gms.util.dbutil;

import com.gms.util.dbconnpool.DBManager;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kevin on 2015/3/31.
 * 利用DbUtils结合数据库连接池对相关实体进行增删改查操作
 */
public class SQLUtil {
    private static Logger logger = LoggerFactory.getLogger(SQLUtil.class);

    private static Connection getConnection() {
        return DBManager.getConn();
    }

    private static void closeConnection(Connection connection) {
        DBManager.closeConn(connection);
    }

    /**
     * 插入一条记录
     * @param sql
     * @param params
     * @return
     */
    public static int insertOne(String sql, Object... params) {
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        Connection conn = getConnection();
        int inserts = 0;
        try {
            inserts = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            logger.error("执行数据库插入操作失败！原因:{},对应sql:{}", e.getMessage(), sql);
        } finally {
            closeConnection(conn);
        }

        return inserts;
    }

    /**
     * 执行数据库批量操作
     * @param sql
     * @param params
     * 例如：
     * batch("delete from TEST where id=?",
     * new Object[][] { { 3 }, { 4 } });
     *
     * batch("insert into TEST (id,name) values (?,?)",
     * new Object[][] { { 11, "a" },
     * { 12, "b" } });
     *
     * @return
     */
    public static int[] batch(String sql, Object[][] params) {
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        Connection conn = getConnection();
        int[] inserts = new int[0];
        try {
            inserts = queryRunner.batch(conn, sql, params);
        } catch (SQLException e) {
            logger.error("执行数据库批量操作失败！原因:{},对应sql:{}", e.getMessage(), sql);
        } finally {
            closeConnection(conn);
        }
        return inserts;
    }

    /**
     * 更新一条记录
     * @param sql
     * @param params
     * @return
     */
    public static int updateOne(String sql, Object... params) {
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        Connection conn = getConnection();
        int update = 0;
        try {
            update = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            logger.error("执行数据库更新操作失败！原因:{},对应sql:{}", e.getMessage(), sql);
        } finally {
            closeConnection(conn);
        }
        return update;
    }

    /**
     * 查询一条记录,对查询结果需要根据model对应的类型进行强转
     * @param sql
     * @param params
     * @return
     */
    public static <T> T selectBean(String sql, Class<T> type, Object... params) {
        CustomBeanProcessor convert = new CustomBeanProcessor();
        RowProcessor rp = new BasicRowProcessor(convert);
        BeanHandler<T> bh = new BeanHandler<T>(type, rp);
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        Connection conn = getConnection();
        T ret = null;
        try {
            ret = (T)queryRunner.query(conn, sql, bh, params);
        } catch (SQLException e) {
            logger.error("查询单个实体类出错,错误原因:{},对应sql:{}",e.getMessage(), sql);
        } finally {
            closeConnection(conn);
        }
        return ret;
    }

    /**
     * 如果查询时没有查询条件，则只需要传一个null给params参数
     * @param sql
     * @param type
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> selectBeanList(String sql, Class<T> type, Object... params) {
        CustomBeanProcessor convert = new CustomBeanProcessor();
        RowProcessor rp = new BasicRowProcessor(convert);
        ResultSetHandler<List<T>> bh = new BeanListHandler<T>(type, rp);
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        Connection conn = getConnection();
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, bh, params);
        } catch (SQLException e) {
            logger.error("查询单个实体类集合出错,错误原因:{},对应sql:{}",e.getMessage(), sql);
        } finally {
            closeConnection(conn);
        }
        return list;
    }


}
