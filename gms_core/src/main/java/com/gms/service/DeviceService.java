package com.gms.service;

import com.gms.bean.po.Device;

import java.util.List;

/**
 * Created by Kevin on 2015/5/7.
 */
public interface DeviceService {
    /**
     * 查询全部设备参数信息
     * @return
     */
    String queryAll();

    /**
     * 查询全部正常运行的设备参数信息
     * @return
     */
    List<Device> queryAllDeviceList();

    String addDevice(Device device);

    void update(Device device);

    String fixDevice(Integer deviceId, String userName);
}
