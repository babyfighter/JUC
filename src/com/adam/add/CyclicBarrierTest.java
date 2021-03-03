package com.adam.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
//      集齐七颗龙珠
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("Succeed!");
        });

        for (int i =1; i<=7; i++){
            final int  temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"collected"+temp+"DragonBall");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
/**
 * 加法计数器
 */
