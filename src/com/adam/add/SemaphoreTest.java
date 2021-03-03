package com.adam.add;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//一个计数信号量
public class SemaphoreTest {
    public static void main(String[] args) {
//        线程数量：停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i =1; i<=6; i++){
            new Thread(()->{
                //acquire() 得到
                //release() 释放
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"  Get spot");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"  Leave spot");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
/*
    原理：
       semaphore.acquire(); 假设已经满了，就会等待直到位置释放
       semaphore.release(); 释放当前的信号量，然后唤醒等待的线程

      多个共享资源互斥的使用； 并发限流，控制最大的线程数
 */