package com.gms.util;

import com.gms.model.User;
import com.gms.util.dbconnpool.DBManager;
import com.gms.util.dbutil.CustomBeanProcessor;
import com.gms.util.dbutil.QueryRunnerFactory;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Kevin on 2015/3/31.
 */
public class TT {
    public static void main(String[] args) {
        QueryRunner queryRunner = QueryRunnerFactory.fetchQueryRunner();
        CustomBeanProcessor beanProcessor = new CustomBeanProcessor();
        RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
        BeanHandler<User> handle = new BeanHandler<User>(User.class, rowProcessor);
        ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
            public Object[] handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] result = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    result[i] = rs.getObject(i + 1);
                }

                return result;
            }
        };
        try {
            User user = queryRunner.query(DBManager.getConn(),"SELECT * FROM USER WHERE ID = ?",handle,1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
