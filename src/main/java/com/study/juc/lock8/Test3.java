package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 4、两个对象，两个同步方法，发短信和call， 发短信和call哪个先执行    打电话先执行，因为中间沉睡了一秒
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        //两把锁，两个对象
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(() -> {
            phone1.seneSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone2.call();
        }, "B ").start();

    }
}

class Phone3 {

    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void seneSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
    //这里没有锁！ 不是同步方法，不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}