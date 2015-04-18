package com.gms.util;

import org.junit.Test;

/**
 * Created by Kevin on 2015/4/18.
 */
public class TestMD5 {
    @Test
    public void getCode() {
        System.out.println(MD5Util.GetMD5Code("123456"));
    }
}
