package com.adam.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//hashmap 加载因子、初始化容量

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for (int i =0; i<10; i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
