package com.gms.util.threadpool;

import java.util.LinkedList;

/**
 * Created by Kevin on 2015/3/26.
 */
public class ThreadPool extends ThreadGroup{
    private boolean isClosed = false;  //线程池是否关闭
    private LinkedList<Runnable> workQueue; //表示工作队列
    private static int threadPoolID;//表示线程的ID
    private int threadID; //表示工作线程的ID

    public ThreadPool(int poolSize){  //指定线程池中工作线程的数目
        super("ThreadPool-"+(threadPoolID++));
        setDaemon(true);  //守护线程
        workQueue = new LinkedList<Runnable>();
        for(int i = 0;i < poolSize;i++){
            new WorkThread().start();
        }
    }

    /**向工作队列中加入一个新任务，由工作线程去执行该任务*/
    public synchronized void execute(Runnable task){
        if(isClosed){      //如果线程池被关闭，则抛出异常
            throw new IllegalStateException();
        }
        if(task != null){
            workQueue.add(task);
            notify();    //唤醒正在getTask方法中等待任务的工作线程
        }
    }

    /**从工作队列中取出一个任务，工作线程会调用此方法
     * @throws InterruptedException */
    protected synchronized Runnable getTask() throws InterruptedException{
        while(workQueue.size()==0){
            if(isClosed){
                return null;
            }
            wait();        //如果工作队列中没有任务，就等待任务
        }
        return workQueue.removeFirst();
    }

    /**关闭线程池*/
    public synchronized void close(){
        if(!isClosed){
            isClosed = true;
            workQueue.clear(); //清空工作队列
            interrupt();         //中断所有的工作线程，该方法继承自ThreadGroup
        }
    }

    /**等待工作线程把所有的任务执行完*/
    public void join(){
        synchronized (this) {
            isClosed = true;
            notifyAll();      //唤醒还在getTask方法中等待任务的工作线程
        }
        Thread[] threads = new Thread[activeCount()];
        //enumerate()方法继承自ThreadGroup类，获得线程中当前所有活着的工作线程
        int count = enumerate(threads);
        for(int i=0; i<count; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** 内部类：工作线程*/
    private class WorkThread extends Thread{
        public WorkThread(){
            //加入当前的ThreadPool线程组中
            super(ThreadPool.this,"WorkThread-"+(threadID++));   //在线程组中创建线程
        }

        public void run(){
            while(!isInterrupted()){      //判断线程是否被中断
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(task == null) return;
                task.run();
            }
        }
    }

}
