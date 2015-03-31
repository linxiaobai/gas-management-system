package com.gms.util.dbutil;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Kevin on 2015/3/31.
 */
public class ResultHandlerFactory<T> {
    private Class<T> entityClass;
    private ResultSetHandler<T> resultSetHandler = null;

    public ResultSetHandler<T> fetchResultHandler() {
        return new BeanHandler<T>(entityClass);
    }


}
