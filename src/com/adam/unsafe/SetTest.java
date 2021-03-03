package com.adam.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ConcurrentModificationException
 * 底层是map，无序不重复
 */
public class SetTest {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i =0; i<10; i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
