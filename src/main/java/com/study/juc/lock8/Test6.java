package com.study.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是锁的8个问题
 * 7、 1个静态同步方法，1个普通同步方法，只有一个对象， 发短信和call哪个先执行    发打电话先执行，发短信后执行    原因：打电话是普通同步方法，他锁的是调用者，发短信是静态同步方法，他锁的是class模板。 两个不是一把锁，而由于中间又沉睡了1秒 所以打电话先执行
 *
 */
public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        Phone6 phone1 = new Phone6();

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
class Phone6 {

    //静态同步发放
    public static synchronized void seneSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("发短信");
    }
    //普通同步方法
    public  synchronized void call() {
        System.out.println("打电话");
    }

}