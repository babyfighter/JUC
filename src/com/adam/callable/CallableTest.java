package com.adam.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(new Runnable()).start();
//        new Thread(new FutureTask<V>()).start();
        new Thread().start();//启动Callable

        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);//适配类

        new Thread(futureTask, "A").start();
        Integer o = (Integer) futureTask.get();//获取Callable的返回结果
        System.out.println(o);
    }
}

/**
 * 有缓存
 * 结果可能需要等待，会阻塞
 */
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 123;
    }
}
