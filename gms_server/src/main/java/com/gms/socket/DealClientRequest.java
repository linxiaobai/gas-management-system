package com.gms.socket;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.Device;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.bean.vo.TransJsonObject;
import com.gms.service.*;
import com.gms.service.impl.*;
import com.gms.util.ApiResultBuilder;
import com.gms.util.ConstantsUtil;
import com.gms.util.GmsException;
import com.gms.util.error.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by Kevin on 2015/4/16.
 * 服务器端接收到客户端请求数据后作出相应的处理类
 */
public class DealClientRequest {

    private static Logger logger = LoggerFactory.getLogger(DealClientRequest.class);

    private UserService userService = new UserServiceImpl();
    private MenuService menuService = new MenuServiceImpl();
    private DeviceService deviceService = new DeviceServiceImpl();
    private EnvironmentalParamService environmentalParamService = new EnvironmentalParamServiceImpl();
    private RuntimeDeviceService runtimeDeviceService = new RuntimeDeviceServiceImpl();
    private RepairedRecordService repairedRecordService = new RepairedRecordServiceImpl();

    /**
     * @param transMsg
     * @return
     * @throws GmsException
     */
    public String DealRequest(String transMsg) throws GmsException {
        TransJsonObject transJsonObject = JSONObject.parseObject(transMsg, TransJsonObject.class);
        if (transJsonObject == null) {
            throw new GmsException("客户端请求服务器端数据转换异常!");
        }
        int code = transJsonObject.getCode();
        logger.info("客户端请求指令对应码：{}", code); //详细去ConstantsUtil查看
        StringBuffer responseMsg = new StringBuffer("");
        switch (code) { //针对transJsonObject里面的object需要再转一次
            case 1:
                responseMsg.append(userService.login(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        LoginInfo.class)));
                break;
            case 2:
                responseMsg.append(menuService.queryAll(Byte.valueOf(transJsonObject.getDataObject().toString()), false));
                break;
            case 3:
                responseMsg.append(menuService.distributeAuth(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        Set.class)));
                break;
            case 4:
                responseMsg.append(userService.addUser(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        User.class)));
                break;
            case 5:
                responseMsg.append(userService.queryAll());
                break;
            case 6:
                responseMsg.append(userService.deleteUser(transJsonObject.getDataObject().toString()));
                break;
            case 7:
                responseMsg.append(deviceService.queryAll());
                break;
            case 8:
                responseMsg.append(deviceService.addDevice(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        Device.class)));
                break;
            case 9:
                String str = transJsonObject.getDataObject().toString();
                Integer deviceId = Integer.valueOf(str.substring(0, str.indexOf(",")));
                String userName = str.substring(str.indexOf(",") + 1);
                responseMsg.append(deviceService.fixDevice(deviceId, userName));
                break;
            case 10:
                responseMsg.append(environmentalParamService.queryAll());
                break;
            case 11:
                responseMsg.append(menuService.queryAll(Byte.valueOf(transJsonObject.getDataObject().toString()), true));
                break;
            case 12:
                responseMsg.append(environmentalParamService.queryBySize(ConstantsUtil.DATA_SIZE));
                break;
            case 13:
                responseMsg.append(runtimeDeviceService.queryAvgData());
                break;
            case 14:
                responseMsg.append(repairedRecordService.queryAll());
                break;
            case 15:
                responseMsg.append(runtimeDeviceService.queryAll());
                break;
            default:
                logger.warn("请求码‘{}’不存在，请对比查看ConstantsUtils", code);
                ApiResultBuilder defaultRet = new ApiResultBuilder(1);
                defaultRet.withRet(false).withErrcode(ErrorMessage.REQ_CODE_NOT_EXIST).withErrmsg(ErrorMessage.REQ_CODE_NOT_EXIST_MSG);
                responseMsg.append(JSONObject.toJSONString(defaultRet.getApiResult()));
        }

        return responseMsg.toString();
    }
}
