package com.study.juc.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A执行完调用B，B执行完调用C，C执行完调用A
 */
public class Test4 {
    public static void main(String[] args) {
        Data4 data4 = new Data4();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data4.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data4.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data4.printC();
            }
        }, "C").start();

    }


}

class Data4 {
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int number = 1; //1 A执行，2 B执行，3 C执行

    public void printA() {
        lock.lock();
        try {
            //业务，判断——》执行——》通知
            while (number != 1) {//判断
                //等待
                condition1.await();
            }
            //通知，通知指定的人
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAAAAA");
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBBBBB");
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCCC");
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}