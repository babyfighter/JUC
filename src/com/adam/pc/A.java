package com.adam.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题： 生产者消费者  等待唤醒/通知唤醒
 * 线程交替执行A B操作同一个变量 num=0
 * A num+1
 * B num-1
 */
public class A {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            try {
                for (int i=1; i<40; i++) data.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i=1; i<40; i++) data.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i=1; i<40; i++) data.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i=1; i<40; i++) data.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}

//判断等待，业务，通知
//如果4条线程，8条线程，需要使用while 防止虚假唤醒，保证线程安全
//Synchronized: wait,notify Condition: await, signal
class Data {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+"->"+number);
    }
}

//可以设置多个condition来监视每一个线程
class Data2 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
       try{
           while (number != 0) {
               condition.await();
           }
           number++;
           System.out.println(Thread.currentThread().getName()+"->"+number);
           condition.signalAll();
       } catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"->"+number);
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}