package com.gms.bean.po;

import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/3/30.
 * 设备记录表
 */
public class Device {
    private Integer id;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 正常范围的临界最小值
     */
    private Double minValue;

    /**
     * 正常范围的临界最大值
     */
    private Double maxValue;

    /**
     * 是否损坏
     */
    private Byte isFailed;

    /**
     * 检修时间点
     */
    private Timestamp repairTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Byte getIsFailed() {
        return isFailed;
    }

    public void setIsFailed(Byte isFailed) {
        this.isFailed = isFailed;
    }

    public Timestamp getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Timestamp repairTime) {
        this.repairTime = repairTime;
    }
}
