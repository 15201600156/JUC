package com.study.juc.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class SpinLockDemo {

    AtomicReference<Thread> atomicReference=new AtomicReference<Thread>();
    //加锁
    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>myLock");
        //自旋锁
        while (!atomicReference.compareAndSet(null,thread)){
        }
    }

    //解锁

    public void myUnLock() {
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>myUnLock");
        atomicReference.compareAndSet(thread,null);
    }
}
