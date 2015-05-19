package com.gms.util.threadpool;

import com.gms.util.UtilException;

/**
 * Created by Kevin on 2015/3/31.
 */
public class ThreadPoolManager {
    public static final int THREAD_POOL_SIZE = 15;
    private static ThreadPool threadPool;

    static { //只会在类初始化的时候加载一次
        threadPool =  new ThreadPool(THREAD_POOL_SIZE);
        if (threadPool == null) {
            throw new UtilException("初始化线程池失败");
        }
    }

    public static ThreadPool getThreadPool() {
        return threadPool;
    }

}
