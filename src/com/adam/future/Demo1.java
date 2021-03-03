package com.adam.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        没有返回值的 runAsync 异步回调
//        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync");
//        });
//
//        System.out.println("==================");
//        future.get();// 获取阻塞执行结果

//        有返回值的 supplyAsync 异步回调
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync");
            int i =10/0;
            return 1024;
        });

        future.whenComplete((t,u)->{
            System.out.println("t=> "+t);
            System.out.println("u=> "+u);
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 233;
        }).get();
    }
}
