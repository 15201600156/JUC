package com.study.juc.lock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        reentrantLock.unlock();

        //底层使用自旋锁  CAS
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnLock();
            }
        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(() -> {
            spinLockDemo.myLock();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnLock();
            }
        }, "T2").start();

    }
}
