package com.gms.util;

/**
 * Created by Kevin on 2015/4/18.
 * 封装server端通过ApiResultBuilder返回的结果
 * @see com.gms.util.ApiResultBuilder
 */
public class ServerRet {
    private Object data;
    private boolean ret;
    private int ver;
    private String errcode;
    private String errmsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ServerRet{" +
                "data=" + data +
                ", ret=" + ret +
                ", ver=" + ver +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
