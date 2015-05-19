package com.gms.bean.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/5/8.
 * 环境参数实时表
 */
public class EnvironmentalParam implements Serializable {
    private Integer id;

    private Double tempVal;

    private Double feedWaterTempVal;

    private Double outWaterTempVal;

    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTempVal() {
        return tempVal;
    }

    public void setTempVal(Double tempVal) {
        this.tempVal = tempVal;
    }

    public Double getFeedWaterTempVal() {
        return feedWaterTempVal;
    }

    public void setFeedWaterTempVal(Double feedWaterTempVal) {
        this.feedWaterTempVal = feedWaterTempVal;
    }

    public Double getOutWaterTempVal() {
        return outWaterTempVal;
    }

    public void setOutWaterTempVal(Double outWaterTempVal) {
        this.outWaterTempVal = outWaterTempVal;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
