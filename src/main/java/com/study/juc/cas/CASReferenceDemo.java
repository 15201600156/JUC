package com.study.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASReferenceDemo {

    public static void main(String[] args) {
        //1就是初始版本号
        //AtomicStampedReference注意：如果泛型是包装类，注意引用
        //正常的业务操作泛型当中放入的都是一个对象   如果要是使用大的值比如操纵127的，提前声明变量，在参数当中放入变量
        AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(1,1);
         new Thread(()->{
             int stamp = atomicStampedReference.getStamp();
             System.out.println("a1=>"+stamp);
             try {
                 TimeUnit.SECONDS.sleep(2);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
             System.out.println("a2=>"+atomicStampedReference.getStamp());
             Lock lock=new ReentrantLock(false);
             System.out.println(atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
             System.out.println("a2=>"+atomicStampedReference.getStamp());

         },"A线程").start();
        //与乐观锁的原理相同
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("b2=>"+atomicStampedReference.getStamp());



        },"A线程").start();

    }
}
