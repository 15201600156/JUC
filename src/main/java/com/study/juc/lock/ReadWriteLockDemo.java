package com.study.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该同时进行
 * <p>
 * 但是如果一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读和写
 * <p>
 * 小总结：
 * 读-读共存
 * 读-写不能功能
 * 写-写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCahce myCahce = new MyCahce();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCahce.put(String.valueOf(finalI), finalI);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCahce.get(String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }

    }
}

//资源类
class MyCahce {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入：" + key);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {

        lock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取：" + key);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }


}
