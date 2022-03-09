package com.study.juc.add;


import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //倒计时   总数是：6    必须要执行任务得时候才去使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " GO OUT");
                countDownLatch.countDown(); //计数器减一
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();//等待计数器归0
        System.out.println("关闭");
    }
}
