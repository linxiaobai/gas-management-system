package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.EnvironmentalParam;
import com.gms.service.EnvironmentalParamService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.FormatUtils;
import com.gms.util.dbutil.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 2015/5/9.
 */
public class EnvironmentalParamServiceImpl implements EnvironmentalParamService {
    private static Logger logger = LoggerFactory.getLogger(EnvironmentalParamServiceImpl.class);

    /*插入一条记录*/
    private static String INSERT_SQL = "INSERT INTO ENVIRONMENTAL_PARAM(TEMP_VAL, FEED_WATER_TEMP_VAL, OUT_WATER_TEMP_VAL, CREATE_TIME) VALUES(?, ?, ?, now())";

    private static String QUERY_ALL_SQL = "SELECT * FROM ENVIRONMENTAL_PARAM";

    private static String QUERY_BY_SIZE_SQL ="SELECT * FROM ENVIRONMENTAL_PARAM ORDER BY CREATE_TIME DESC LIMIT ?";
    @Override
    public void insertData() {
        double[] simulateData = getRandomData();
        SQLUtil.insertOne(INSERT_SQL, simulateData[0], simulateData[1], simulateData[2]);
    }

    private double[] getRandomData() {
        double[] data = new double[3];
        Random random = new Random();
        for(int i = 0; i < data.length; i++) {
            int intVal = random.nextInt(20) + 1;
            data[i] = FormatUtils.formatDecimal(random.nextDouble() * intVal, 1);
        }
        return data;
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<EnvironmentalParam> environmentalParams = SQLUtil.selectBeanList(QUERY_ALL_SQL, EnvironmentalParam.class);
        apiResultBuilder.withRet(true).withData(environmentalParams);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    @Override
    public String queryBySize(Integer dataSize) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<EnvironmentalParam> environmentalParams = SQLUtil.selectBeanList(QUERY_BY_SIZE_SQL, EnvironmentalParam.class, dataSize);
        apiResultBuilder.withRet(true).withData(environmentalParams);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
