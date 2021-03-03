package com.adam.add;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        总数是6 必须要执行任务的时候 使用
      CountDownLatch countDownLatch = new CountDownLatch(6);

      for (int i = 1; i<=6; i++){
          new Thread(()->{
              System.out.println(Thread.currentThread().getName()+" Out");
              countDownLatch.countDown();//数量-1
          }, String.valueOf(i)).start();
      }

        countDownLatch.await();//等待计数器归零，然后再向下执行
        System.out.println("Close Door");
    }
}
/**
 * 每次有线程调用countDown（）数量-1，计数器变为0时， countDownLatch.await()就会被唤醒
 * 继续执行
 */
