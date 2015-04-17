package com.gms.util;

import com.gms.bean.po.Finance;
import com.gms.util.date.DateUtils;
import com.gms.util.dbutil.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/3/31.
 */
public class TTT {
    public static void main(String[] args) {
//        String sql = "select * from FINANCE";
//        List<Finance> finances = SQLUtil.selectBeanList(sql, Finance.class, null);
//        for (Finance finance : finances) {
//            System.out.println(finance);
//        }
//        Object o = SQLUtil.selectOne(sql,1);
//        String sql = "insert into USER(ID,USERNAME,PASSWD,REAL_NAME,USER_TYPE,CREATE_TIME,MODIFY_TIME) values(?, ?, ?, ?, ?, ?, ?)";
//        int ret = SQLUtil.insertOne(sql,3,"haohao","123456","小小",2,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()));
//        System.out.println(ret);
//        String batchInsert = "insert into FINANCE(ID, FEE, CREATE_TIME) values(?,?,?)";

        Object[][] data = new Object[][]{
                {1,10, DateUtils.getCurrentTime()},
                {2,-20,DateUtils.getCurrentTime()}
        };
        List<Object> financeList = new ArrayList<Object>();
        for (int i = 3; i < 10; i++) {
            Finance f = new Finance();
            f.setId(i);
            f.setFee(20.2+i*2);
            f.setCreateTime(DateUtils.getCurrentTime());
            financeList.add(f);
        }
        Object[][] ret = ConvertUtil.listToArr(financeList,Finance.class);
        System.out.println("");
//

//        System.out.println(SQLUtil.batch(batchInsert, data));
    }
}
