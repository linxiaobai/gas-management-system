package com.gms.bean.po;

import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/3/30.
 * 燃气使用居民用户缴费记录表
 */
public class Bill {
    private Integer id;

    /**
     * 订单号
     */
    private String billSequence;

    /**
     * 缴费者手机号
     */
    private String payerPhone;

    /**
     * 应缴费金额
     */
    private Double shdPayMoney;

    /**
     * 处理员
     */
    private Long handlerId;

    /**
     * 处理员姓名 (冗余字段，考虑后期可能会比较常用这个)
     */
    private String handlerName;

    /**
     * 滞纳金
     */
    private Double lateFee;

    /**
     * 实际缴纳金额
     */
    private Double actPayMoney;

    /**
     * 应缴费日期
     */
    private Timestamp shdPayDate;

    /**
     * 实际缴费日期
     */
    private Timestamp actPayDate;

    /**
     * 账单状态 0 未缴费  1 已缴费
     */
    private Byte status;

    /**
     * 燃气使用量
     */
    private Double gasUseAmount;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillSequence() {
        return billSequence;
    }

    public void setBillSequence(String billSequence) {
        this.billSequence = billSequence;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public Double getShdPayMoney() {
        return shdPayMoney;
    }

    public void setShdPayMoney(Double shdPayMoney) {
        this.shdPayMoney = shdPayMoney;
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

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    public Double getActPayMoney() {
        return actPayMoney;
    }

    public void setActPayMoney(Double actPayMoney) {
        this.actPayMoney = actPayMoney;
    }

    public Timestamp getShdPayDate() {
        return shdPayDate;
    }

    public void setShdPayDate(Timestamp shdPayDate) {
        this.shdPayDate = shdPayDate;
    }

    public Timestamp getActPayDate() {
        return actPayDate;
    }

    public void setActPayDate(Timestamp actPayDate) {
        this.actPayDate = actPayDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getGasUseAmount() {
        return gasUseAmount;
    }

    public void setGasUseAmount(Double gasUseAmount) {
        this.gasUseAmount = gasUseAmount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
