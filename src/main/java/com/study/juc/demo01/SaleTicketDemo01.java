package com.study.juc.demo01;

//基本的卖票例子

/**
 * 真正的多线程开发，公司的开发，要降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1、属性、方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源类
        Ticket ticket = new Ticket();
        //FunctionalInterface 函数式接口   jdk1.8  lambda表达式   (参数)->{方法体}
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}

//资源类  OOP（面向对象编程）
class Ticket {
    //属性、方法
    private int number = 30;
    //卖票的方式
    //synchronized 本质：队列，锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票,剩余：" + number);
        }
    }
}
