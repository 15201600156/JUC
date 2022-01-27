package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 3、增加一个普通hello方法后，两个线程相打印，发短信和hello， 发短信和hello哪个先执行
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.seneSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.hello();
        }, "B ").start();

    }
}

class Phone2 {

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