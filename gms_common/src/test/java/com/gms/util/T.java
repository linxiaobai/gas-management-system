package com.gms.util;

import com.gms.swing.GmsStartWindow;

/**
 * Created by Kevin on 2015/3/31.
 */
public class T {
    public static void main(String[] args) {
        System.out.println(PathUtil.fetchPath(GmsStartWindow.class, "pic/Login.gif"));
    }
}
