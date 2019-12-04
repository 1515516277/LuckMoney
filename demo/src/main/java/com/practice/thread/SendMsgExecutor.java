package com.practice.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SendMsgExecutor {

    private static final int COREPOOLSIZE = 100;//核心线程池大小
    private static final int MAXIMUMPOOLSIZE = 200;//最大线程池大小
    private static final long KEEPALIVETIME = 5L;//线程最大空闲时间
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;  //时间单位
    private static final BlockingQueue<Runnable> WORKQUEUE = new LinkedBlockingQueue<Runnable>();//线程等待队列
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(COREPOOLSIZE, MAXIMUMPOOLSIZE, KEEPALIVETIME, UNIT, WORKQUEUE);

    private SendMsgExecutor() {
    }

    /**
     * 获取线程池实例
     *
     * @return
     */
    public static ThreadPoolExecutor getInstance() {

        return threadPoolExecutor;
    }

    /*
     * 向线程池中添加任务方法
     */
    public static void addExecuteTask(Runnable task) {
        if (task != null) {
            threadPoolExecutor.execute(task);
        }
    }

    /*
     * 判断是否是最后一个任务
     */
    public static boolean isTaskEnd() {
        if (threadPoolExecutor.getActiveCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 获取线程池中的线程数目
     */
    public static int getPoolSize() {
        return threadPoolExecutor.getPoolSize();
    }


    /*
     * 获取已完成的任务数
     */
    public static long getCompletedTaskCount() {
        return threadPoolExecutor.getCompletedTaskCount();
    }

    /*
     * 关闭线程池，不在接受新的任务，会把已接受的任务执行玩
     */
    public static void shutdown() {
        threadPoolExecutor.shutdown();
    }


}
