package com.gms.bean.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/5/5.
 * 设备某些参数在某些时间点的即使数据
 */
public class RuntimeDevice implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Double paVal;

    private Double tempVal;

    private Double waterLevelVal;

    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Double getPaVal() {
        return paVal;
    }

    public void setPaVal(Double paVal) {
        this.paVal = paVal;
    }

    public Double getTempVal() {
        return tempVal;
    }

    public void setTempVal(Double tempVal) {
        this.tempVal = tempVal;
    }

    public Double getWaterLevelVal() {
        return waterLevelVal;
    }

    public void setWaterLevelVal(Double waterLevelVal) {
        this.waterLevelVal = waterLevelVal;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
