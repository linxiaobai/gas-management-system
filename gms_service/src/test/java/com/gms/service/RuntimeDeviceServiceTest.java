package com.gms.service;

import com.gms.bean.vo.DeviceAvgData;
import com.gms.service.impl.RuntimeDeviceServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by Kevin on 2015/5/10.
 */
public class RuntimeDeviceServiceTest {
    private RuntimeDeviceService runtimeDeviceService = new RuntimeDeviceServiceImpl();
    @Test
    public void selectTest() {
        String list = runtimeDeviceService.queryAvgData();
        System.out.println(list);

    }
}
