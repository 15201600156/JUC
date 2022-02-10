package com.study.juc.function;

import java.util.function.Supplier;

/**
 * Supplier供给型接口
 */
public class Demo4 {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "测试";
            }
        };
        System.out.println(supplier.get());
    }
//    public static void main(String[] args) {
//        Supplier<String> supplier = () -> { return "测试";};
//        System.out.println(supplier.get());
//    }
}
