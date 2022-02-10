package com.study.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用
 * AJAX
 * 1、异步执行
 * 2、成功回调
 * 3、失败回调
 */
public class Demo01 {
    //没有返回值的runAsync的异步回调
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>void");
//        });
//        System.out.println("1111111");
//        completableFuture.get();//获取阻塞执行结果
//    }

    //有返回值的异步回调supplyAsync
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
            int o=1/0;
            return  1024;
        });

        completableFuture.whenComplete((t,u)->{
            System.out.println("t=>"+t);  //t代表正常的返回结果
            System.out.println("u=>"+u);  //u代表错误的信息 u=>java.util.concurrent.CompletionException:

        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 233;
        }).get();

    }

}
