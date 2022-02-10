package com.study.juc.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    //不加volatile程序就会死循环。 没有通知主内存num值已经变为0
    //家volatile可以保证可见性
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {//线程1 对主内存的变化不知道
            while (num==0)
            {
            }
        },"线程一").start();
        TimeUnit.SECONDS.sleep(1);
        num=1;
        System.out.println(num);
    }
}
