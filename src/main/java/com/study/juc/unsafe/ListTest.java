package com.study.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException  多线程ArrayList插入数据会报此异常
public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList不安全的

        /**
         * 解决方案：
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、JUC的解决方案 List<String> list = new CopyOnWriteArrayList<>();
         */

//        List<String> list = new ArrayList<>();
        //CopyOnWrite  写入时复制，  COW：计算机程序设计领域的一种优化策略
        //多个线程调用的时候 List，读取的时候，是固定的，写入的时候是先复制再替换上去
        //在写入的时候，避免覆盖，造成数据问题

        //CopyOnWriteArrayList比Vector厉害在哪里？
        //Vector是synchronized的，有这个的基本效率会低，CopyOnWriteArrayList使用的lock
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
