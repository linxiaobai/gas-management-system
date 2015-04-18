package com.gms.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2014/8/30.
 */
public class ApiResultBuilder {

    private Map<String, Object> apiResultMap = new HashMap<String, Object>();

    public ApiResultBuilder() {}

    public ApiResultBuilder(Integer version){
        apiResultMap.put("ver", version);
    }

    public ApiResultBuilder withRet(boolean ret){
        apiResultMap.put("ret", ret);
        return this;
    }

    public ApiResultBuilder withErrcode(int errcode){
        apiResultMap.put("errcode", errcode);
        return this;
    }

    public ApiResultBuilder withErrmsg(String errmsg){
        apiResultMap.put("errmsg", errmsg);
        return this;
    }

    public ApiResultBuilder withData(Object data){
        apiResultMap.put("data", data);
        return this;
    }

    public ApiResultBuilder withVersion(int version){
        apiResultMap.put("ver", version);
        return this;
    }

    public Map<String, Object> getApiResult(){
        return apiResultMap;
    }

    public Boolean ret(boolean b){
        return (Boolean)apiResultMap.get("ret");
    }

    public int errcode(){
        return (Integer) apiResultMap.get("errcode");
    }

    public String errmsg(){
        return (String) apiResultMap.get("errmsg");
    }

    public Integer version(){
        return (Integer) apiResultMap.get("ver");
    }

    public Object data(){
        return apiResultMap.get("data");
    }
}
