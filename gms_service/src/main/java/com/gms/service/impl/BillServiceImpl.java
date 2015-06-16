package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Bill;
import com.gms.service.BillService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.dbutil.SQLUtil;
import com.gms.util.error.ErrorMessage;

import java.util.List;

/**
 * Created by Kevin on 2015/6/8.
 */
public class BillServiceImpl implements BillService{
    private static final String INSERT_ONE = "INSERT INTO BILL(BILL_SEQUENCE, PAYER_PHONE, SHD_PAY_MONEY, HANDLER_ID, HANDLER_NAME, STATUS, GAS_USE_AMOUNT, CREATE_TIME, MODIFY_TIME)" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, now(), now())";
    private static final String QUERY_ALL_BY_STATUS = "SELECT * FROM BILL WHERE STATUS = ?";
    private static final String UPDATE_BILL_STATUS = "UPDATE BILL SET STATUS = ? WHERE BILL_SEQUENCE = ?";
    private static final String INSERT_FINANCE_ONE = "INSERT INTO FINANCE(FEE, DETAIL, CREATE_TIME) VALUES(?, ?, now())";
    @Override
    public String createOrder(Bill bill) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        //生成订单序号
        bill.setBillSequence(OrderSequenceService.fetchOrderSequence());
        int ret = SQLUtil.insertOne(INSERT_ONE, bill.getBillSequence(), bill.getPayerPhone(), bill.getShdPayMoney(), bill.getHandlerId(), bill.getHandlerName(), bill.getStatus(), bill.getGasUseAmount());
        if (ret > 0) {
            apiResultBuilder.withData("订单创建成功，对应订单号：" + bill.getBillSequence()).withRet(true);
        } else {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SQL_ERROR).withErrmsg(ErrorMessage.SQL_ERROR_MSG);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 查询所有未缴费的订单
     * @return
     */
    @Override
    public String queryAllUnPaidBill() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<Bill> bills = SQLUtil.selectBeanList(QUERY_ALL_BY_STATUS, Bill.class, 0); //0表示未缴费
        apiResultBuilder.withData(bills).withRet(true);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 处理订单,即修改订单状态
     * @param orderSequence
     * @return
     */
    @Override
    public String dealBill(String orderSequence, Double payMoney) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        int billRet = SQLUtil.updateOne(UPDATE_BILL_STATUS, 1, orderSequence);
        SQLUtil.insertOne(INSERT_FINANCE_ONE, payMoney, "用户缴费");
        if (billRet > 0) {
            apiResultBuilder.withData("处理缴费成功！").withRet(true);
        } else {
            apiResultBuilder.withErrmsg(ErrorMessage.SQL_ERROR_MSG).withErrcode(ErrorMessage.SQL_ERROR).withRet(false);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
