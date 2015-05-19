package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Device;
import com.gms.bean.po.RuntimeDevice;
import com.gms.bean.vo.DeviceAvgData;
import com.gms.service.DeviceService;
import com.gms.service.RuntimeDeviceService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.FormatUtils;
import com.gms.util.GmsException;
import com.gms.util.dbutil.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 2015/5/5.
 */
public class RuntimeDeviceServiceImpl implements RuntimeDeviceService {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeDeviceServiceImpl.class);

    /*添加设备*/
    private static final String INSERT_SQL = "INSERT INTO RUNTIME_DEVICE(DEVICE_ID, PA_VAL, TEMP_VAL, WATER_LEVEL_VAL, CREATE_TIME) VALUES(?, ?, ?, ?, now())";
    /*获取各个设备的平均值*/
    private static final String QUERY_CHART_SQL = "SELECT DEVICE_ID AS deviceId, AVG(PA_VAL) AS avgPaVal, AVG(TEMP_VAL) AS avgTempVal, AVG(WATER_LEVEL_VAL) AS avgWaterLevelVal FROM RUNTIME_DEVICE GROUP BY DEVICE_ID;";
    /*查询设备的所有数据值*/
    private static final String QUERY_ALL_SQL = "SELECT * FROM RUNTIME_DEVICE";

    private DeviceService deviceServiceImpl = new DeviceServiceImpl();

    /**
     * 模拟插入设备即使参数数据
     */
    @Override
    public void insertData() {
        List<Device> devices = deviceServiceImpl.queryAllDeviceList();
        if (devices == null || devices.size() == 0) { //设备所有参数均不正常
            return;
        }
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            //随机数据获取
            double[] paVal = new double[0];
            double[] tempVal = new double[0];
            double[] wlVal = new double[0];
            try {
                paVal = getRandom(device.getPaMaxVal(), device.getPaMinVal());
                tempVal = getRandom(device.getTempMaxVal(), device.getTempMinVal());
                wlVal = getRandom(device.getWlMaxVal(), device.getWlMinVal());
            } catch (GmsException e) {
                logger.error("模拟设备随机值出错！原因：{}", e.getMessage());
            }
            byte isFailed = 0;
            if (paVal[1] == 1.0 || tempVal[1] == 1.0 || wlVal[1] == 1.0) {
                isFailed = 1;
            }
            device.setPaVal(paVal[0]);
            device.setTempVal(tempVal[0]);
            device.setWaterLevelVal(wlVal[0]);
            device.setIsFailed(isFailed);
            logger.info("模拟设备编号为{}的数据:{}", device.getId(), device);
            deviceServiceImpl.update(device);
            int insertRet = SQLUtil.insertOne(INSERT_SQL, device.getId(), paVal[0], tempVal[0], wlVal[0]);
            logger.info("保存及时设备数据结果{}", insertRet);
        }
        
    }

    /**
     * 查询各个设备的参数的平均值
     * @return
     */
    @Override
    public String queryAvgData() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<DeviceAvgData> deviceAvgData = SQLUtil.selectBeanList(QUERY_CHART_SQL, DeviceAvgData.class);
        apiResultBuilder.withData(deviceAvgData).withRet(true);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    /**
     * 查询设备运行时各个时刻的所有记录
     * @return
     */
    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<RuntimeDevice> runtimeDevices = SQLUtil.selectBeanList(QUERY_ALL_SQL, RuntimeDevice.class);
        apiResultBuilder.withData(runtimeDevices).withRet(true);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }


    private double[] getRandom(double max, double min) throws GmsException {
        double oldMax = max;//保留下来用于判断设备好坏
        double oldMin = min;
        max = max + 0.1;
        min = min - 0.1;
        double randomVal;
        double[] ret = new double[2];
        if (max < min) {
            throw new GmsException("最大值小于最小值");
        }
        if (min == max) {
            randomVal = min;
            ret[0] = FormatUtils.formatDecimal(min, 1);

        } else {
            randomVal = min + ((max - min) * new Random().nextDouble());
            ret[0] = FormatUtils.formatDecimal(randomVal,1);
        }

        if (randomVal < oldMin || randomVal > oldMax) {
            ret[1] = 1.0;
        } else {
            ret[1] = 0.0;
        }
        return ret;
    }
}
