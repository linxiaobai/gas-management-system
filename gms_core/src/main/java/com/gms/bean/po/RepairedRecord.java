package com.gms.bean.po;

import java.sql.Timestamp;

/**
 * Created by Kevin on 2015/5/10.
 * 修理记录
 */
public class RepairedRecord {
    private Integer id;

    private Integer deviceId;

    private String userName;

    private Timestamp repairedTime;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getRepairedTime() {
        return repairedTime;
    }

    public void setRepairedTime(Timestamp repairedTime) {
        this.repairedTime = repairedTime;
    }
}
