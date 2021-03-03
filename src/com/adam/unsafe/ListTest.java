package com.adam.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","2","3");
//        list.forEach(System.out::println);
//        List<String> list = new ArrayList<>();
//        并发下Arraylist不安全，解决方案：
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
//        多线程调用时，list读取时是固定的，写入时避免覆盖操作
//        COW使用Lock锁，没有使用synchronized，比Vector好
        List<String> list = new CopyOnWriteArrayList<>();



        for (int i =0; i<10; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
