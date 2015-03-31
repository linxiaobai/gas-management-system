package com.gms.util.threadpool;

import com.gms.util.UtilException;

/**
 * Created by Kevin on 2015/3/31.
 */
public class ThreadPoolManager {
    public static final int THREADPOOLSIZE = 15;
    private static ThreadPool threadPool;

    static {
        threadPool =  new ThreadPool(THREADPOOLSIZE);
        if (threadPool == null) {
            throw new UtilException("初始化线程池失败");
        }
    }

    public static ThreadPool getThreadPool() {
        return threadPool;
    }

}
