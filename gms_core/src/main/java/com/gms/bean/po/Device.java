package com.gms.bean.po;

import java.io.Serializable;

/**
 * Created by Kevin on 2015/3/30.
 * 设备记录表
 */
public class Device implements Serializable {
    /**
     * 设备号
     */
    private Integer id;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 当前压强值
     */
    private Double paVal;

    private Double paMinVal;

    private Double paMaxVal;

    /**
     *  设备当前温度
     */
    private Double tempVal;

    private Double tempMinVal;

    private Double tempMaxVal;

    /**
     * 当前水位值
     */
    private Double waterLevelVal;

    private Double wlMinVal;

    private Double wlMaxVal;

    private Byte isFailed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getPaVal() {
        return paVal;
    }

    public void setPaVal(Double paVal) {
        this.paVal = paVal;
    }

    public Double getPaMinVal() {
        return paMinVal;
    }

    public void setPaMinVal(Double paMinVal) {
        this.paMinVal = paMinVal;
    }

    public Double getPaMaxVal() {
        return paMaxVal;
    }

    public void setPaMaxVal(Double paMaxVal) {
        this.paMaxVal = paMaxVal;
    }

    public Double getTempVal() {
        return tempVal;
    }

    public void setTempVal(Double tempVal) {
        this.tempVal = tempVal;
    }

    public Double getTempMinVal() {
        return tempMinVal;
    }

    public void setTempMinVal(Double tempMinVal) {
        this.tempMinVal = tempMinVal;
    }

    public Double getTempMaxVal() {
        return tempMaxVal;
    }

    public void setTempMaxVal(Double tempMaxVal) {
        this.tempMaxVal = tempMaxVal;
    }

    public Double getWaterLevelVal() {
        return waterLevelVal;
    }

    public void setWaterLevelVal(Double waterLevelVal) {
        this.waterLevelVal = waterLevelVal;
    }

    public Double getWlMinVal() {
        return wlMinVal;
    }

    public void setWlMinVal(Double wlMinVal) {
        this.wlMinVal = wlMinVal;
    }

    public Double getWlMaxVal() {
        return wlMaxVal;
    }

    public void setWlMaxVal(Double wlMaxVal) {
        this.wlMaxVal = wlMaxVal;
    }

    public Byte getIsFailed() {
        return isFailed;
    }

    public void setIsFailed(Byte isFailed) {
        this.isFailed = isFailed;
    }

    @Override
    public String toString() {
        return "Device{" +
                "paVal=" + paVal +
                ", tempVal=" + tempVal +
                ", waterLevelVal=" + waterLevelVal +
                ", isFailed=" + isFailed +
                '}';
    }
}
