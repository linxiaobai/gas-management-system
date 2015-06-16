package com.gms.service.impl;

import com.gms.bean.po.SystemParam;
import com.gms.util.GmsException;
import com.gms.util.date.DateUtils;
import com.gms.util.dbutil.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kevin on 2015/6/8.
 * 订单号生成
 */
public class OrderSequenceService {
    private static final Logger logger = LoggerFactory.getLogger(OrderSequenceService.class);

    private static final String QUERY_NEWEST_ORDER_SEQ = "SELECT PARAM_VALUE FROM SYSTEM_PARAM WHERE PARAM_NAME = ?";
    private static AtomicInteger orderSequence;

    static {
        SystemParam systemParam = SQLUtil.selectBean(QUERY_NEWEST_ORDER_SEQ, SystemParam.class, "orderSequence");
        if (systemParam == null) {
            try {
                throw new GmsException("订单序号获取失败!");
            } catch (GmsException e) {
                logger.error(e.getMessage());
            }
        }
        orderSequence = new AtomicInteger(Integer.valueOf(systemParam.getParamValue()));
    }

    /**
     * 获取订单序号
     */
    public static synchronized String fetchOrderSequence() {
        Integer value = orderSequence.incrementAndGet();
        String date = DateUtils.format8Date(new Date());
        return date + String.format("%08d", value);
    }

    public static synchronized int fetchOrderCount() {
        return orderSequence.get();
    }
}
