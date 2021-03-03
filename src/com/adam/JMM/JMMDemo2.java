package com.adam.JMM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 * 不加lock和 synchronized，如何保证原子性？
 * javap -c 反编译
 * JUC中atomic类
 * 底层：直接和操作系统挂钩，在内存中修改值 Unsafe类是很特殊的存在！
 */

public class JMMDemo2 {
//    volatile不保证原子性
    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add(){
//        num++;//不是原子性操作
        num.getAndIncrement();// AtomicInteger +1 方法. CAS
    }

    public static void main(String[] args) {
//        num理论上为20000
        for (int i= 1;i<=20; i++){
            new Thread(()->{
                for (int j=0; j<1000;j++){
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){// main GC
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }

}
