package com.gms.bean.vo;

/**
 * Created by Kevin on 2015/5/10.
 */
public class DeviceAvgData extends BaseBean{
    private Integer deviceId;

    private Double avgPaVal;

    private Double avgTempVal;

    private Double avgWaterLevelVal;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Double getAvgPaVal() {
        return avgPaVal;
    }

    public void setAvgPaVal(Double avgPaVal) {
        this.avgPaVal = avgPaVal;
    }

    public Double getAvgTempVal() {
        return avgTempVal;
    }

    public void setAvgTempVal(Double avgTempVal) {
        this.avgTempVal = avgTempVal;
    }

    public Double getAvgWaterLevelVal() {
        return avgWaterLevelVal;
    }

    public void setAvgWaterLevelVal(Double avgWaterLevelVal) {
        this.avgWaterLevelVal = avgWaterLevelVal;
    }

    @Override
    public String toString() {
        return "DeviceAvgData{" +
                "deviceId=" + deviceId +
                ", avgPaVal=" + avgPaVal +
                ", avgTempVal=" + avgTempVal +
                ", avgWaterLevelVal=" + avgWaterLevelVal +
                '}';
    }
}
