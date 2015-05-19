package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.RepairedRecord;
import com.gms.service.RepairedRecordService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.dbutil.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kevin on 2015/5/10.
 */
public class RepairedRecordServiceImpl implements RepairedRecordService {
    private static final Logger logger = LoggerFactory.getLogger(RepairedRecordServiceImpl.class);

    /*插入一条记录*/
    private static final String INSERT_SQL = "INSERT INTO REPAIRED_RECORD(DEVICE_ID, USER_NAME, REPAIRED_TIME) VALUES(?, ?, now())";
    /*查询所有的修理记录*/
    private static final String SELECT_SQL = "SELECT * FROM REPAIRED_RECORD";

    @Override
    public void addRepairedRecord(RepairedRecord repairedRecord) {
        int ret = SQLUtil.insertOne(INSERT_SQL, repairedRecord.getDeviceId(), repairedRecord.getUserName());
        logger.info("记录设备编号为{}维修结果：{}", repairedRecord.getDeviceId(), ret > 0 ? "成功" : "失败");
    }

    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<RepairedRecord> repairedRecords = SQLUtil.selectBeanList(SELECT_SQL, RepairedRecord.class);
        apiResultBuilder.withRet(true).withData(repairedRecords);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
