package com.study.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    //CAS compareAndSet :比较并交换。     compareAndSet和getAndIncrement的底层都是调用compareAndSwapInt方法。
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);
        //第一参数：期望值，第二个参数：修改后得值
        //public final boolean compareAndSet(int expect, int update)
        //如果我期望得值达到了，那么就更新，否则不更新，CAS是CPU并发得原语也就是CPU的指令
        //============捣乱的线程====================
        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2023, 2022));
        System.out.println(atomicInteger.get());
        //============期望的线程====================
        System.out.println(atomicInteger.compareAndSet(2022, 8888));
        System.out.println(atomicInteger.get());
    }
}
