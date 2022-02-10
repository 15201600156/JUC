package com.study.juc.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目要求：一分钟完成此题，只能用一行代码实现
 * 现在有5个用户！筛选：
 * 1、ID必须式偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒叙排列
 * 5、只能输出一个用户
 */
public class Test1 {

    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);
        User u6 = new User(6, "f", 26);
        List<User> users = Arrays.asList(u1, u2, u3, u4, u5, u6);
        users.stream().filter(w -> w.getId() % 2 == 0)
                .filter(w -> w.getAge() > 23)
                .map(w -> w.getName().toLowerCase())
                .sorted((uu1, uu2) -> uu2.compareTo(uu1))
                .limit(1).forEach(System.out::println);
    }
}

@Data
@AllArgsConstructor
class User {
    private Integer id;
    private String name;
    private Integer age;
}
