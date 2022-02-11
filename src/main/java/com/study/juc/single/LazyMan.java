package com.study.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式
 * <p>
 * 单线程没有问题
 * public static LazyMan getInstance() {
 * if (LAZY_MAN == null) {
 * LAZY_MAN = new LazyMan();
 * }
 * return LAZY_MAN;
 * }
 *
 *
 * 道高一尺魔高一丈
 */
public class LazyMan {
    private static boolean flag = false;

    public LazyMan() {
        synchronized (LazyMan.class) {   //synchronized只采取这一种方式，对象都用反射进行创建也会出现创建两次。 需要再配合一个变量执行，列如：红绿灯
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("不要试图使用反射来进行破坏");
            }
            System.out.println(Thread.currentThread().getName() + "ok");
        }
    }

    public static volatile LazyMan LAZY_MAN;

    //双重检测懒汉式的 懒汉式单列 DCL懒汉式
    public static LazyMan getInstance() {
        if (LAZY_MAN == null) {
            synchronized (LazyMan.class) {
                if (LAZY_MAN == null) {
                    LAZY_MAN = new LazyMan(); //不是原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * 执行顺序有可能是123，也有能为132.
                     * 这个时候多线程的时候就会出现指令重拍， A线程创建完成后把这个对象还没有指向这个空间的时候，锁已经释放。 这个时候下一个线程已经过来了，判断的时候已经判断出
                     * LAZY_MAN还有没有为空。
                     */
                }
            }
        }
        return LAZY_MAN;
    }

    //多线程并发的时候如果没有使用synchronized 就会出现问题，创建多次。
//    public static void main(String[] args) {
//        for (int i = 0; i <= 20; i++) {
//            new Thread(() -> {
//                LazyMan.getInstance();
//            }, String.valueOf(i)).start();
//
//        }
//    }

    //反射可以破坏这种单列    反射不能破坏枚举
    public static void main(String[] args) throws Exception {
        // LazyMan lazyMan = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);//无视私有的构造器
        Field flag = LazyMan.class.getDeclaredField("flag");
        LazyMan lazyMan1 = declaredConstructor.newInstance();
        flag.set(lazyMan1,false);
        LazyMan lazyMan = declaredConstructor.newInstance();

        System.out.println(lazyMan);
        System.out.println(lazyMan1);
        System.out.println(lazyMan == lazyMan1);
        System.out.println(lazyMan.equals(lazyMan1));

    }
}
