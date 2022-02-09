package com.study.juc.queue;


import java.util.AbstractQueue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test4();

    }

    /**
     * 抛出异常
     */
    public static void  test1(){
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //添加超过队列大小，抛出此异常java.lang.IllegalStateException: Queue full
       // System.out.println(blockingQueue.add("d"));
        System.out.println("============================");//

        Object element = blockingQueue.element();  //element查看队首元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //移除的时候队列当中没有元素还在继续移除，抛出此异常java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
    }

    /**
     * 不抛出异常
     */
    public static void  test2(){
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // System.out.println(blockingQueue.offer("d"));  //不抛出异常
        Object peek = blockingQueue.peek();//peek查看队首元素
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //
        System.out.println(blockingQueue.poll());   //返回null,不抛出异常
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    public static void  test3() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        //一直阻塞
         blockingQueue.put("a");
         blockingQueue.put("b");
         blockingQueue.put("c");
         //blockingQueue.put("d");  //队列没有为止，一直阻塞等待，直到有了位置


        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); //队列中没有元素后，要是取得情况下，也会一直阻塞到这

    }
    /**
     * 等待，阻塞（等待超时）
     */
    public static void  test4() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        //一直阻塞
        System.out.println(blockingQueue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 1, TimeUnit.SECONDS));
      //  System.out.println(blockingQueue.offer("d", 1, TimeUnit.SECONDS));
        // blockingQueue.offer("d",1, TimeUnit.SECONDS);  //队列没有位置，1秒后如果还没有位置，则自动退出
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        //System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS)); //队列中没有元素后，1秒后如果还没有元素，则自动退出
    }
}
