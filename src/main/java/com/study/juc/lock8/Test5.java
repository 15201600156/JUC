package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 6、增加两个静态同步方法，有两个对象， 发短信和call哪个先执行    发短信先执行，打电话后执行
 */
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象的class类模板只有一个，锁的是class
        Phone5 phone1 = new Phone5();
        Phone5 phone2 = new Phone5();

        new Thread(() -> {
            phone1.seneSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone2.call();
        }, "B ").start();

    }
}

//Phone4是唯一的一个class对象
class Phone5 {

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