package com.gms.util;

import com.gms.util.threadpool.ThreadPool;
import com.gms.util.threadpool.ThreadPoolManager;

/**
 * Created by Kevin on 2015/3/31.
 */
public class T {
    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPoolManager.getThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });
    }
}
