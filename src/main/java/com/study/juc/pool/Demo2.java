package com.study.juc.pool;

import java.util.concurrent.*;

/**
 * new ThreadPoolExecutor.AbortPolicy()  银行人员满了，可是还有人来，这个时候不处理这个人的，直接抛出异常 拒绝后的异常为：RejectedExecutionException
 * new ThreadPoolExecutor.CallerRunsPolicy()  哪里来的去哪，但是不爆出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy() 队列满了会尝试去跟最早的竞争，不会抛出异常
 *  new ThreadPoolExecutor.DiscardPolicy() 队列满了不会抛出异常
 */
public class Demo2 {
    public static void main(String[] args) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(3);
        //最大的线程到底如何定义
        //1、CPU密集型     几核，就是几，可以保持CPU的效率最高 （大量的计算）
        //2、IO密集型 判断你程序中十分IO的线程有多少个，最大线程数就设置为多少（涉及到网络、磁盘IO的任务都是IO密集型）
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,3, TimeUnit.SECONDS,queue,Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        System.out.println("CPU的核数："+Runtime.getRuntime().availableProcessors());
        try {
            //最大承载Deque和maxPoolSize
            //超过最大线程数RejectedExecutionException
            for (int i = 0; i <=9; i++) {
                //使用了线程池之后，使用线程池创建线程
                threadPool.execute(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "    OK");
                        }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完 ，线程池结束要关闭
            threadPool.shutdown();
        }
    }
}
