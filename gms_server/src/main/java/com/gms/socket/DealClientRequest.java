package com.gms.socket;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.vo.LoginInfo;
import com.gms.bean.vo.TransJsonObject;
import com.gms.service.UserService;
import com.gms.util.GmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kevin on 2015/4/16.
 * 服务器端接收到客户端请求数据后作出相应的处理类
 */
public class DealClientRequest {

    private static Logger logger = LoggerFactory.getLogger(DealClientRequest.class);
    private UserService userService = new UserService();

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
        StringBuffer responseMsg = new StringBuffer("");
        switch (code) { //针对transJsonObject里面的object需要再转一次
            case 1:
                responseMsg.append(userService.login(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        LoginInfo.class)));
        }

        return responseMsg.toString();
    }
}
