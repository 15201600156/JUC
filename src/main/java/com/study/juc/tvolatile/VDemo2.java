package com.study.juc.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

//不保证原子性
public class VDemo2 {
    //volatile不保证原子性
//    private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger(0);

    public  static void add() {
        //num++; //不是原子性操作
        num.getAndIncrement();//+1的操作  CAS锁解决的
    }

    public static void main(String[] args) {
        //理论上num的结果应该是2万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        //判断存活的线程数量
        while (Thread.activeCount()>2) //java 有两个线程是默认存在的分别为：main和GC
        {
            Thread.yield();
        }
        System.out.println(num);
    }
}
