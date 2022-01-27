package com.study.juc.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 *java.util.ConcurrentModificationException
 */
public class MapList {

    public static void main(String[] args) {
        /**
         * map是这样用的吗？ 不是？工作当中不用hashmap
         * 默认等价于new HashMap<>(16,0.75)
         */
//        Map<String, String> map = new HashMap<>();
        Map<String,String> map= new ConcurrentHashMap<>( );
        for (int i = 0; i < 30; i++) {

            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI),UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
