package com.gms.socket;

import com.alibaba.fastjson.JSONObject;
import com.gms.bean.vo.TransJsonObject;
import com.gms.util.ServerRet;

/**
 * Created by Kevin on 2015/4/18.
 */
public class SSLClientUtil {

    /**
     * 传入封装好的transJsonObject对象，获取服务器端处理的结果并转成ServerRet类型对象返回
     * @param transJsonObject
     * @return 服务器端处理的结果
     */
    public static ServerRet sendAndReciveMsg(TransJsonObject transJsonObject) {
        String transData = JSONObject.toJSONString(transJsonObject);
        SSLClient sslClient = SSLClientFactory.fetchSSLClient();
        sslClient.init();
        String returnMsg = sslClient.process(transData);
        ServerRet successRet = JSONObject.parseObject(returnMsg, ServerRet.class);
        return successRet;
    }
}
