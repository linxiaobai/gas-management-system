package com.gms.mainframe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Kevin on 2015/5/1.
 */


/**
 * 测试JTable添加数据，删除数据频繁操作，JTable出现数组越界的处理
 * 在工作中如果遇到频繁的操作Jtable的数据，特别是速率很快的情况下，经常会遇到
 * Exception in thread "AWT-EventQueue-0" java.lang.ArrayIndexOutOfBoundsException
 * 这样的数组越界的异常,这里引入Swing的一个线程，能很好的解决这个问题
 * 供同样遇到这样问题的人参考。
 * @author 蒋家狂潮
 * email:simon1006@163.com
 *
 */
public class ThreadTable extends JTable {
    private DefaultTableModel model;

    static String[] header = new String[] { "id", "name", "sex", "age" };

    public ThreadTable() {
        model = new DefaultTableModel(header, 0);
        this.setModel(model);
    }

    public void deleteRows(int rowCount) throws Exception {
        if (rowCount >= model.getColumnCount()) {
            throw new Exception("删除的行数不能超过model的总行数！");
        } else {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    public void testInsertValue() {
        final Vector<String> value = new Vector<String>();
        value.add("0");
        value.add("simon");
        value.add("boy");
        value.add("21");

        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    //addValueWithThread(value);//这个方法不会出现越界
                    addValueWithoutThread(value);//这个方法会出现越界,差别就在于加入一个线程
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    /**
     * 将添加记录和删除记录在一个线程里走，不会出现页面刷新的时候，数组越界的问题
     * @param value
     */
    public void addValueWithThread(final Vector value) {
        Thread thread = new Thread() {
            public void run() {
                Runnable runnable = new Runnable() {
                    public void run() {
                        model.addRow(value);
                        if (model.getRowCount() > 5) {
                            try {
                                deleteRows(2);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                };
                SwingUtilities.invokeLater(runnable);
            }
        };
        thread.start();
    }
    /**
     * 这样一边添加记录，一边删除记录，会出现数组越界的情况
     * @param value
     */
    public void addValueWithoutThread(final Vector value) {
        model.addRow(value);
        if (model.getRowCount() > 5) {
            try {
                deleteRows(2);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {


        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BorderLayout());

        ThreadTable table = new ThreadTable();
        JScrollPane scroll = new JScrollPane(table);
        f.getContentPane().add(scroll, BorderLayout.CENTER);

        f.setSize(800, 600);
        f.setLocation(250, 250);
        f.setVisible(true);

        table.testInsertValue();
    }
}