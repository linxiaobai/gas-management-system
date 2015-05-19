package com.gms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Device;
import com.gms.bean.po.RepairedRecord;
import com.gms.service.DeviceService;
import com.gms.service.RepairedRecordService;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.dbutil.SQLUtil;
import com.gms.util.error.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kevin on 2015/5/5.
 */
public class DeviceServiceImpl implements DeviceService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /*查询全部设备*/
    private static final String SELECT_ALL_SQL = "SELECT * FROM DEVICE";

    /*查询所有正常的设备*/
    private static final String SELECT_FINE_SQL = "SELECT * FROM DEVICE WHERE IS_FAILED = ?";

    /*添加设备*/
    private static final String INSERT_SQL = "INSERT INTO DEVICE(DEVICE_NAME, PA_MIN_VAL, PA_MAX_VAL, TEMP_MIN_VAL, TEMP_MAX_VAL," +
            "WL_MIN_VAL, WL_MAX_VAL, IS_FAILED) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    /*更新设备的压强等值*/
    private static final String UPDATE_SQL = "UPDATE DEVICE SET PA_VAL = ? , TEMP_VAL = ?, WATER_LEVEL_VAL = ?, IS_FAILED = ? WHERE ID = ?";

    /*更改设备的损坏状态*/
    private static final String UPDATE_SQL2 = "UPDATE DEVICE SET IS_FAILED = ? WHERE ID = ?";

    private RepairedRecordService repairedRecordService = new RepairedRecordServiceImpl();


    @Override
    public String queryAll() {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        List<Device> devices = SQLUtil.selectBeanList(SELECT_ALL_SQL, Device.class);
        apiResultBuilder.withRet(true).withData(devices);
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    @Override
    public List<Device> queryAllDeviceList() {
        List<Device> devices = SQLUtil.selectBeanList(SELECT_FINE_SQL, Device.class, 0);
        return devices;
    }

    @Override
    public String addDevice(Device device) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        int ret = SQLUtil.insertOne(INSERT_SQL,device.getDeviceName(), device.getPaMinVal(), device.getPaMaxVal(), device.getTempMinVal(),
                device.getTempMaxVal(), device.getWlMinVal(), device.getWlMaxVal(), device.getIsFailed());
        if (ret > 0) {
            apiResultBuilder.withRet(true).withData("新设备信息添加成功");
        } else {
            apiResultBuilder.withRet(false).withErrcode(ErrorMessage.SQL_ERROR).withErrmsg(ErrorMessage.SQL_ERROR_MSG);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }

    @Override
    public void update(Device device) {
        SQLUtil.updateOne(UPDATE_SQL, device.getPaVal(), device.getTempVal(), device.getWaterLevelVal(), device.getIsFailed(), device.getId());
    }

    /**
     * 修理设备，即更新设备的损坏状态
     * @param deviceId
     * @return
     */
    @Override
    public String fixDevice(Integer deviceId, String userName) {
        ApiResultBuilder apiResultBuilder = new ApiResultBuilder(1);
        int ret = SQLUtil.updateOne(UPDATE_SQL2, ConstantsUtil.NORMAL_DEVICE, deviceId);
        RepairedRecord repairedRecord = new RepairedRecord();
        repairedRecord.setDeviceId(deviceId);
        repairedRecord.setUserName(userName);
        repairedRecordService.addRepairedRecord(repairedRecord);
        if (ret > 0) {
            apiResultBuilder.withRet(true).withData("设备修理成功~");
        } else {
            apiResultBuilder.withRet(false).withErrmsg(ErrorMessage.SQL_ERROR_MSG).withErrcode(ErrorMessage.SQL_ERROR);
        }
        return JSONObject.toJSONString(apiResultBuilder.getApiResult());
    }
}
