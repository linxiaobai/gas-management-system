package com.gms.bean.vo;

/**
 * Created by Kevin on 2015/4/17.
 */
public class TransJsonObject {
    private Object dataObject; //传输的数据封装对象
    private int code;

    public TransJsonObject(){}

    public TransJsonObject(Object object, int code) {
        this.dataObject = object;
        this.code = code;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
