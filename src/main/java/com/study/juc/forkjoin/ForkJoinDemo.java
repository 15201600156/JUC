package com.study.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * 1、传统For循环相加
 * 2、ForkJoin
 * 3、Stream并行流做计算
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long temp = 10000L;//临界值

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        if ((end - start) > temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //ForkJoin递归
            long middle = (start + end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork();
            return  task1.join()+task2.join();
        }
    }
}
