package com.gms.util;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;

/**
 * Created by Kevin on 2015/5/11.
 */
public class ExcelUtil {


    public static void exportTable(JTable table, File file) throws IOException {
        try {
            OutputStream out = new FileOutputStream(file);
            TableModel model = table.getModel();
            WritableWorkbook wwb = Workbook.createWorkbook(out);
            // 创建字表，并写入数据
            WritableSheet ws = wwb.createSheet("设备运行数据", 0);
            // 添加标题
            for (int i = 0; i < model.getColumnCount(); i++) {
                jxl.write.Label labelN = new jxl.write.Label(i, 0, model.getColumnName(i));
                try {
                    ws.addCell(labelN);
                } catch (RowsExceededException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (WriteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 添加列
            for (int i = 0; i < model.getColumnCount(); i++) {
                for (int j = 1; j <= model.getRowCount(); j++) {
                    jxl.write.Label labelN = new jxl.write.Label(i, j, model.getValueAt(j - 1, i).toString());
                    try {
                        ws.addCell(labelN);
                    } catch (RowsExceededException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                }
            }
            wwb.write();
            try {
                wwb.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导入数据前请关闭工作表");
        }
    }
}
