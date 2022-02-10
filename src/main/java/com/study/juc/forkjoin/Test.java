package com.study.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 同一个任务，别人比你告诉几十倍
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
         //test1();//sum=500000000500000000,耗时：5352
        // test2(); //sum=500000000500000000,耗时：5407
        // test3(); //sum=500000000500000000,耗时：118
    }
    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 100_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时：" + (end - start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinDemo(0L, 100_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时：" + (end - start));
    }
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时：" + (end - start));
    }
}
