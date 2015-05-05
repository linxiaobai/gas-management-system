package com.gms.mainframe;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.User;
import com.gms.bean.vo.TransJsonObject;
import com.gms.socket.SSLClientUtil;
import com.gms.util.ConstantsUtil;
import com.gms.util.ServerRet;
import com.gms.util.dbutil.ConvertUtil;

import javax.swing.*;
import java.util.List;

/**
 * Created by Kevin on 2015/5/2.
 */
public class Table extends JFrame{
    public Table() {
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(initTable());

    }

    private JScrollPane initTable() {
        TransJsonObject transJsonObject = new TransJsonObject(ConstantsUtil.USER_LIST);
        ServerRet serverRet = SSLClientUtil.sendAndReciveMsg(transJsonObject);
        List<User> users = JSONObject.parseArray(serverRet.getData().toString(), User.class);
        for (User user : users) {
            System.out.println(user);
        }
        Object[][] data = ConvertUtil.listToArr(users, User.class, "username", "realName", "mobilePhone", "createTime");
        String[] names = {"username", "realName", "mobilePhone", "createTime"};
        JTable jTable = new JTable(data, names);
        JScrollPane scrollPane = new JScrollPane(jTable);
        return scrollPane;
    }

    public static void main(String[] args) {
        new Table();
    }
}
