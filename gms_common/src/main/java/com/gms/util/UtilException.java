package com.gms.util;

/**
 * Created by Kevin on 2015/3/30.
 */
public class UtilException extends RuntimeException{
    public UtilException(String s) {
        super(s);
    }

    public UtilException(String s, Throwable e) {
        super(s, e);
    }

    public UtilException(Throwable e) {
        super(e);
    }
}
