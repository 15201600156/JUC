package com.study.juc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样，SynchronousQueue不存储元素
 * put了一个元素，必须从里面先take取出来，否则put不进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();//同步队列

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                synchronousQueue.put(1);
                System.out.println(Thread.currentThread().getName() + " put 2");
                synchronousQueue.put(2);
                System.out.println(Thread.currentThread().getName() + " put 3");
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take=>" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }

}
