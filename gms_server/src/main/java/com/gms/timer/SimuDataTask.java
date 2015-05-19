package com.gms.timer;

import com.gms.service.EnvironmentalParamService;
import com.gms.service.RuntimeDeviceService;
import com.gms.service.impl.EnvironmentalParamServiceImpl;
import com.gms.service.impl.RuntimeDeviceServiceImpl;

import java.util.TimerTask;

/**
 * Created by Kevin on 2015/5/5.
 * 模拟数据定时任务(防止服务器端控制导致频繁实例化写成单例)
 */
public class SimuDataTask extends TimerTask {
    private static SimuDataTask instance = null;
    private RuntimeDeviceService runtimeDeviceService = new RuntimeDeviceServiceImpl();
    private EnvironmentalParamService environmentalParamService = new EnvironmentalParamServiceImpl();
    private SimuDataTask() {}
    @Override
    public void run() {
        runtimeDeviceService.insertData();
        environmentalParamService.insertData();
    }

    public static SimuDataTask getInstance() {
//        if (instance == null) {  发现并不能用单例
//            instance = new SimuDataTask();
//        }
        return new SimuDataTask();
    }
}
