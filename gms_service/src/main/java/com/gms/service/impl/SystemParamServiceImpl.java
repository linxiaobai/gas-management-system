package com.gms.service.impl;

import com.gms.service.SystemParamService;
import com.gms.util.dbutil.SQLUtil;

/**
 * Created by Kevin on 2015/6/8.
 */
public class SystemParamServiceImpl implements SystemParamService{
    private static final String UPDATE_ONE = "UPDATE SYSTEM_PARAM SET PARAM_VALUE = ? WHERE PARAM_NAME = ?";
    @Override
    public void updateSequence() {
        SQLUtil.updateOne(UPDATE_ONE, OrderSequenceService.fetchOrderCount(), "orderSequence");
    }
}
