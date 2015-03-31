package com.gms.model;

import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/3/30.
 * 燃气使用居民用户缴费记录表
 */
public class Payment {
    private Integer id;

    /**
     * 缴费者手机号
     */
    private String payerPhone;

    /**
     * 缴费金额
     */
    private Double payMoney;

    /**
     * 处理员
     */
    private Long handlerId;

    /**
     * 处理员姓名 (冗余字段，考虑后期可能会比较常用这个)
     */
    private String handlerName;

    /**
     * 创建时间（即处理时间）
     */
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
