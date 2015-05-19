package com.gms.util;

/**
 * Created by Kevin on 2015/3/30.
 */
public class GmsException extends Exception{

    private String msg;

    public GmsException(String msg) {
        super(msg);
        this.msg = msg;
    }




    @Override
    public String getMessage() {
        return msg;
    }
}
