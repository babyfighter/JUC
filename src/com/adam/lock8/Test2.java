package com.adam.lock8;


import java.util.concurrent.TimeUnit;

/**
 * static Class 类锁
 * synchronized 对象锁
 */
public class Test2 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone1{
    public static synchronized void sendSms(){
        System.out.println("sending message");
    }
    public static synchronized void call(){
        System.out.println("calling");
    }
}