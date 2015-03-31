package com.gms.util;

import com.gms.util.dbconnpool.DBManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Kevin on 2015/3/31.
 */
public class TestDbUtils {
    public static void main(String[] args) {
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

        QueryRunner run = new QueryRunner();
        Connection connection = DBManager.getConn();

        try {
            Object[] result = run.query(connection,"SELECT * FROM USER WHERE id = ? and USERNAME = ?",h,1,"linjian");
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
