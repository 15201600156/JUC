package com.study.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(() -> {
            phone.sms();
        }, "A").start();
        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone1 {
    Lock lock = new ReentrantLock();
    public void sms() {

        lock.lock();  //细节问题：lock.lock();   lock.unlock();//lock锁必须配对，否则会死锁到里面。加几次锁必须解几次锁
        try {
            System.out.println(Thread.currentThread().getName() + "->sms");
            call();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    public void call() {

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "->call");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}