package com.gms.component;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Kevin on 2015/5/5.
 */
public class CustomTableModel extends DefaultTableModel{

    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /**
     * 重写父类该方法保证table不可编辑
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;//父类的方法里面是 return true的，所以就可以编辑了
    }
}
