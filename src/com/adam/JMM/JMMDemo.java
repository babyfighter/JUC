package com.adam.JMM;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
//    不加volatile程序就会死循环
//    可以保证可见性
    private volatile static int num =0;
    public static void main(String[] args) {

        new Thread(()->{
            while (num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
