package com.study.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new Runnable()).start();
        //new Thread(new FutureTask<V>()).start();
        //new Thread(new FutureTask<V>(Callable)).start();
        MyThread thread=new MyThread();
        FutureTask futureTask=new FutureTask(thread); //设备类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        String o = (String) futureTask.get();//获取Callable的返回结果     这个get方法可能产生阻塞，所以把他放在最后
        //或者使用异步通信来处理
        System.out.println(o);

    }
}

class MyThread implements Callable<String>{

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        //在此中间有可能有耗时操作
        System.out.println(111);
        return "hello word";
    }
}
