package com.gms.model;

import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/3/30.
 * 系统开支记录表
 */
public class Finance {
    private Integer id;

    /**
     * 费用（收入是正数，支出是负数）
     */
    private Double fee;

    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
