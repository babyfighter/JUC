package com.adam.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 特点：工作窃取
 * 双端队列，如果一个线程完成工作，会把另一个线程未完成工作拿来做，提升效率
 * forkjoinpoll
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
//        test3();//146
    }
//    Forkjoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L,10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println( "sum"+sum+"time total: " +(end-start));
    }
//    Stream并行流
    public static void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();

        System.out.println("sum= "+sum+"||time total: " + (end-start));
    }

}
