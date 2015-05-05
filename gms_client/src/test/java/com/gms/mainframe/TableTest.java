package com.gms.mainframe;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 2015/5/1.
 */
public class TableTest extends JFrame{
    public TableTest() {
        super();
        initTable();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTable() {
        Object[][] playerInfo={
                {"阿呆",new Integer(66),new Integer(32),new Integer(98),new Boolean(false)},
                {"阿呆",new Integer(82),new Integer(69),new Integer(128),new Boolean(true)},
        };
        String[] Names={"姓名","语文","数学","总分","及格"};
        JTable jTable = new JTable(playerInfo, Names);
        JScrollPane scrollPane=new JScrollPane(jTable);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TableTest tableTest = new TableTest();
    }
}
