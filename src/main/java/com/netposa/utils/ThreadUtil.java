package com.netposa.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public class ThreadUtil {
    private ThreadPoolExecutor threadPoolExecutor;
    private int poolSize;

    /**
     * 构造方法初始化线程池
     * @param poolSize 要设置的线程池大小
     */
    public ThreadUtil(int poolSize){
        try {
            buildExecutorService(poolSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPoolSize() {
        return poolSize;
    }

    /**
     * 创建线程池，赋值线程池调度器
     *
     * @param poolSize 线程池大小.<p>注意：poolSize必须为大于0的整数
     */
    private void buildExecutorService(int poolSize) throws Exception {
        if (poolSize <= 0) {
            throw new Exception("线程池大小不可为0");
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    }

    /**
     * 提交Callable，获得返回值引用
     * @param task
     * @return
     */
    public <T> Future<T> submitCallable( Callable<T> task) {
        Future<T> re = threadPoolExecutor.submit(task);
        return re;
    }

    /**
     * 提交一个RunnableTask进线程池
     * @param task
     */
    public void submitRunnable( Runnable task) {
        threadPoolExecutor.execute(task);
    }

    /**
     * 当线程池内的所有子线程任务运行完毕，手动关闭线程池。若开启了线程池，非手动关闭JVM退出程序前必须调用
     *
     */
    public void releaseThreadPool() {
        threadPoolExecutor.shutdown();
    }
}
