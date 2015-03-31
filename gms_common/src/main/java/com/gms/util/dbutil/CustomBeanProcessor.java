package com.gms.util.dbutil;

import org.apache.commons.dbutils.BeanProcessor;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015/3/31.
 * 由于DBUtils里面的BeanProcessor字段和实体变量名映射不支持驼峰规则
 * (例如:数据库里面USER_TYPE这种的字段对应的值取不到userType变量里)
 * 所以需要对其进行重写
 */
public class CustomBeanProcessor extends BeanProcessor{

    private final Map<String, String> columnToPropertyOverrides;

    public CustomBeanProcessor() {
        this(new HashMap<String, String>());
    }

    public CustomBeanProcessor(Map<String, String> columnToPropertyOverrides) {
        super(columnToPropertyOverrides);
        this.columnToPropertyOverrides = columnToPropertyOverrides;
    }

    @Override
    protected int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {
        int cols = rsmd.getColumnCount();
        int[] columnToProperty = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

        for (int col = 1; col <= cols; col++) {
            String columnName = rsmd.getColumnLabel(col);
            if (null == columnName || 0 == columnName.length()) {
                columnName = rsmd.getColumnName(col);
            }
            String propertyName = columnToPropertyOverrides.get(columnName);
            if (propertyName == null) {
                propertyName = columnName;
            }

            for (int i = 0; i < props.length; i++) {
                /*主要在这样地方进行改动，把数据库里面的表中的字段替换成空串*/
                if (propertyName.replace("_", "").equalsIgnoreCase(props[i].getName())) {
                    columnToProperty[col] = i;
                    break;
                }
            }

        }

        return columnToProperty;
    }

}
