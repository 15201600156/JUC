package com.study.juc.function;

import java.util.function.Predicate;

/**
 * 断定型接口；有一个输入参数，返回值式布尔值
 */
public class Demo2 {
    //        public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
//    }
    public static void main(String[] args) {
        Predicate<String> predicate = s -> { return s.isEmpty();};
    }
}
