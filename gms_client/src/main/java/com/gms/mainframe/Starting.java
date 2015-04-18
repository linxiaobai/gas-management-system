package com.gms.mainframe;

import com.gms.swing.GmsStartWindow;

/**
 * Created by Kevin on 2015/4/17.
 */
public class Starting {
    public static void main(String[] args) {
        GmsStartWindow gmsStartWindow = new GmsStartWindow("pic/starting.png",new Login("客户端登录", 300, 200), 2000);
    }
}
