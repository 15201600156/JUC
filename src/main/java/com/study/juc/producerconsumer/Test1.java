package com.study.juc.producerconsumer;

/**
 * 线程之间的通信问题：生产者与消费组问题   等待唤醒和通知
 * 线程交替执行  AB操作同一个变量   num=0
 * A  num+1
 * B  num-1
 */
public class Test1 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

//判断等待，通知其他业务
class Data {//数字 资源类
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        if (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        //通知其他线程，我+1完毕了
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        //通知其他线程，我-1完毕了
        this.notifyAll();
    }
}
