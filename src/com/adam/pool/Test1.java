package com.adam.pool;

import java.util.concurrent.*;

/**
 * Executors 工具类、三大方法
 * 使用线程池来创建线程
 * 最大线程如何定义？
 * 1.CPU 密集型： 几核就是几，保证CPU效率最高 Runtime.getRuntime().availableProcessors()
 * 2.IO 密集型： 判断程序中十分损耗IO的线程，大于这个值
 */

public class Test1 {
    public static void main(String[] args) {
//        ExecutorService threadPoll = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPoll = Executors.newFixedThreadPool(5);//固定的大小线程池
//       ExecutorService threadPoll = Executors.newCachedThreadPool();//可伸缩

        ExecutorService threadPoll = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//不处理，抛出异常
//        new ThreadPoolExecutor.AbortPolicy();//哪来的去哪里
//        new ThreadPoolExecutor.DiscardPolicy();//队列满了，丢掉任务，不抛出异常
//        new ThreadPoolExecutor.DiscardOldestPolicy();队列满了，尝试去和最早的竞争不会抛出异常


        try {
            for (int i =0; i<100; i++){
            threadPoll.execute(()->{
                System.out.println(Thread.currentThread().getName()+" ok");
            });
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoll.shutdown();
        }
    }
}
