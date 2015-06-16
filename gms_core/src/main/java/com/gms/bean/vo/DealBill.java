package com.gms.bean.vo;

/**
 * Created by Kevin on 2015/6/8.
 * 处理订单数据传输
 */
public class DealBill extends BaseBean{
    private String orderSequence;

    private Double payMoney;

    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }
}
