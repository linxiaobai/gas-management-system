package com.gms.mainframe;

import com.gms.swing.GmsFrame;

/**
 * Created by Kevin on 2015/4/8.
 */
public class JFrameTest {
    public static void main(String[] args) {
        GmsFrame gmsFrame = new GmsFrame.Builder(500,600,"用户登录").build();
    }
}
