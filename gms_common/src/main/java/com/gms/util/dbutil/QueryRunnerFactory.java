package com.gms.util.dbutil;

import org.apache.commons.dbutils.QueryRunner;

/**
 * Created by Kevin on 2015/3/31.
 */
public class QueryRunnerFactory {
    /**
     * 获取一个QueryRunner实例
     */
    public static QueryRunner fetchQueryRunner() {
        return new QueryRunner();
    }
}
