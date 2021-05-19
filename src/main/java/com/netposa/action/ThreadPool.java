package com.netposa.action;

import com.netposa.service.DataEngineFactory;
import com.netposa.service.DataEngine;
import com.netposa.utils.ArgumentsCheck;
import com.netposa.utils.Conf;
import com.netposa.utils.Global;
import com.netposa.utils.ThreadUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public class ThreadPool {
    private static Map<String, Long> map;
    private static CountDownLatch countDownLatch;

    public static void main(String[] args) throws Exception {
        ArgumentsCheck.checkArgumentsNumber(args);
        Conf.loadConf(args[0]);
//        Conf.loadConf("");
        countDownLatch = new CountDownLatch(Global.THREADPOOL_SIZE);
        map = new ConcurrentHashMap<>(Global.THREADPOOL_SIZE);
        ThreadUtil threadPool = new ThreadUtil(Global.THREADPOOL_SIZE);

        long startTime = System.currentTimeMillis();
        System.out.println(String.format("==========时间：%s,开始测试%s=======", String.valueOf(startTime), Global.TEST_MODE));
        if ("read".equalsIgnoreCase(Global.TEST_MODE)) {
            forQuery(Global.THREADPOOL_SIZE, threadPool, Global.TEST_DATAENGINE_NAME);
        } else if ("write".equalsIgnoreCase(Global.TEST_MODE)) {
            forWrite(Global.THREADPOOL_SIZE, threadPool, Global.TEST_DATAENGINE_NAME);
        }
        countDownLatch.await();
        threadPool.releaseThreadPool();

        long endTime = System.currentTimeMillis();
        long use = endTime - startTime;
        System.out.println(String.format("==========时间：%s,全部线程%s结束，共耗时：%s毫秒=======", String.valueOf(endTime), Global.TEST_MODE, String.valueOf(use)));
        Comparator<Long> comparator = Comparator.comparing(Long::longValue);
        long maxDuration = map.values().stream().max(comparator).get();
        long minDuration = map.values().stream().min(comparator).get();
        System.out.println(String.format("==========单线程最长运行时间：%s毫秒=========单线程最短运行时间：%s毫秒==========", maxDuration, minDuration));

    }

    private static void forQuery(int threadPoolSize, ThreadUtil threadPool, String engineType) {
        for (int i = 0; i < threadPoolSize; i++) {
            threadPool.submitRunnable(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    long use = getUseTime(engineType, threadName, "query");
                    System.out.println(String.format("测试读性能，线程：%s 本次读操作耗时：%s毫秒", threadName, String.valueOf(use)));
                } catch (Exception e) {
                    System.out.println(String.format("test mode is %s,at %s,thread %s catch an Exception", Global.TEST_MODE, String.valueOf(System.currentTimeMillis()), threadName));
                    e.printStackTrace();
                    System.exit(-1);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
    }

    private static void forWrite(int threadPoolSize, ThreadUtil threadPool, String engineType) {
        for (int i = 0; i < threadPoolSize; i++) {
            threadPool.submitRunnable(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    long use = getUseTime(engineType, threadName, "write");
                    System.out.println(String.format("测试写性能，线程：%s 本次写操作耗时：%s毫秒", threadName, String.valueOf(use)));
                } catch (Exception e) {
                    System.out.println(String.format("test mode is %s,at %s,thread %s catch an Exception", Global.TEST_MODE, String.valueOf(System.currentTimeMillis()), threadName));
                    e.printStackTrace();
                    System.exit(-1);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
    }

    private static long getUseTime(String engineType, String threadName, String execType) throws Exception {
        DataEngine dataEngineInstance = DataEngineFactory.getDataEngineInstance(engineType);
        dataEngineInstance.buildEnvironment();

        long startTime = System.currentTimeMillis();
        if ("query".equalsIgnoreCase(execType)) {
            dataEngineInstance.query();
        } else if ("write".equalsIgnoreCase(execType)) {
            dataEngineInstance.write();
        }

        long endTime = System.currentTimeMillis();
        long use = endTime - startTime;
        map.put(threadName, use);
        return use;
    }
}
