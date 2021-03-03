package com.adam.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
//        获取cpu核数
//        CPU密集型/IO密集型
//        System.out.println(Runtime.getRuntime().availableProcessors());
        Ticket ticket = new Ticket();
        new Thread(()->{for (int i=1; i<40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i=1; i<40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i=1; i<40; i++) ticket.sale();},"C").start();
    }
}

//Synchronized 锁
//class Ticket{
//    private int number = 30;
//
//    public synchronized void sale(){
//        if(number>0){
//            System.out.println(Thread.currentThread().getName()+"sale"+(number--)+"tickets available: "+ number);
//        }
//    }
//}

//Lock 锁
class Ticket{
    private int number = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
    try{
        if (number>0) {
            System.out.println(Thread.currentThread().getName()+"sale"+(number--)+"tickets available: "+ number);
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 * Synchronized 内置的Java关键字， Lock是一个Java类
 * Synchronized 无法判断获取锁的状态， Lock可以判断是否获取到锁
 * Synchronized 自动释放锁 ， Lock 锁必须手动释放，否则会死锁
 * Synchronized Thread1（获得锁，阻塞），Thread（等待）；Lock锁： lock.trylock() 尝试获取锁，不一定一直等待
 * Synchronized 可重入锁，不可中断，非公平；Lock 可重入锁，可以判断锁，可以设置公平锁
 * Synchronized 适合少量的代码同步； Lock适合锁大量的同步代码
*/