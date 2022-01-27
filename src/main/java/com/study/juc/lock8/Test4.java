package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 5、增加两个静态同步方法，只有一个对象， 发短信和call哪个先执行    发短信先执行，打电话后执行     static 静态方法，锁的是class，所以显示发短信持有锁
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 phone1 = new Phone4();

        new Thread(() -> {
            phone1.seneSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone1.call();
        }, "B ").start();

    }
}

//Phone4是唯一的一个class对象
class Phone4 {

    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行
    //static 静态方法，锁的是class
    public static synchronized void seneSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }

}