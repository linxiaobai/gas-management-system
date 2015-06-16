package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Finance;
import com.gms.service.FinanceService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.dbutil.SQLUtil;

import java.util.List;

/**
 * Created by Kevin on 2015/6/2.
 */
public class FinanceServiceImpl implements FinanceService{

    private static String QUERY_ALL_SQL = "SELECT * FROM FINANCE";

    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<Finance> retList = SQLUtil.selectBeanList(QUERY_ALL_SQL, Finance.class);
        apiResultBuilder.withData(retList).withRet(true);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
