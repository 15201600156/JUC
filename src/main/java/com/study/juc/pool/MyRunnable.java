package com.study.juc.pool;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {
    private String command;
    AtomicInteger atomicInteger=new AtomicInteger();

    public MyRunnable(String s) {

        this.command = s;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time =" + new Date().getTime());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date().getTime());
    }
    private void processCommand() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return this.command;
    }
}
