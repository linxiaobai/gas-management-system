package com.gms.timer;

import java.util.Timer;

/**
 * Created by Kevin on 2015/5/8.
 */
public class TimerStart implements Runnable{
    private static final Long INTERVAL = 30000l;//间隔时间30秒
    private Timer timer = null;
    public TimerStart(Timer timer) {
        this.timer = timer;
    }
    @Override
    public void run() {
        timer.schedule(SimuDataTask.getInstance(), 0, INTERVAL);
    }
}
