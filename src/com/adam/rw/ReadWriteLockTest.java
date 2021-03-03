package com.adam.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁：（写锁） 一次只能被一个线程占有
 * 共享锁：（读锁） 多个线程可以同时占有
 * 读-读可以共存
 * 读-写 不能共存
 * 写-写 不能共存
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();

        for (int i =1; i<=5; i++){
            final int temp =i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i =1; i<=5; i++){
            final int temp =i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();

    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName()+"write"+key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"write OK");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"read"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"read OK");
    }
}

class MyCache2{
    private volatile Map<String,Object> map = new HashMap<>();
//    读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();
//      写入的时候同时间只有一个线程写
    public void put(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"write"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"write OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
//      读所有人都可以读
    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"read"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"read OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }
}
