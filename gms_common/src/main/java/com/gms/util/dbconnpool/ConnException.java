package com.gms.util.dbconnpool;

/**
 * Created by Kevin on 2015/3/30.
 */
public class ConnException extends RuntimeException{
    public ConnException(String s) {
        super(s);
    }

    public ConnException(String s, Throwable e) {
        super(s, e);
    }

    public ConnException(Throwable e) {
        super(e);
    }
}
