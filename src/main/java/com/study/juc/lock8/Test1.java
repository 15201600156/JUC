package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 1、标准情况下，两个线程相打印，发短信和打电话， 发短信和打电话哪个先执行
 * 2、sendSms延迟4s，两个线程相打印，发短信和打电话， 发短信和打电话哪个先执行
 * 以上两个答案都是发短信先执行，打电话后执行
 *     synchronized 锁的对象是方法的调用者
 *     两个方法用的是同一个锁，谁先拿到谁先执行
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.seneSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.call();
        }, "B ").start();

    }
}

class Phone {

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

}