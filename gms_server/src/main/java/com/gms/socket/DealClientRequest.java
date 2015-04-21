package com.gms.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gms.bean.po.User;
import com.gms.bean.vo.LoginInfo;
import com.gms.bean.vo.TransJsonObject;
import com.gms.service.MenuService;
import com.gms.service.UserService;
import com.gms.util.ApiResultBuilder;
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

    private UserService userService = new UserService();
    private MenuService menuService = new MenuService();

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
                responseMsg.append(menuService.queryAll());
                break;
            case 3:
                responseMsg.append(menuService.distributeAuth(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        Set.class)));
                break;
            case 4:
                responseMsg.append(userService.addUser(JSONObject.parseObject(transJsonObject.getDataObject().toString(),
                        User.class)));
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
