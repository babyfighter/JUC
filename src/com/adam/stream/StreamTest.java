package com.adam.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 1.ID偶数
 * 2.年龄大于13
 * 3.用户名转为大写
 * 4.用户名字母倒着排序
 * 5.只输出一个用户
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a",21);
        User u2 = new User(2, "b",22);
        User u3 = new User(3, "c",23);
        User u4 = new User(4, "d",24);
        User u5 = new User(5, "e",25);
        User u6 = new User(6, "f",26);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5,u6);
//      计算交给Stream
        list.stream()
                .filter(user -> {return user.getId()%2==0;})
                .filter(user -> {return user.getAge()>23;})
                .map(user -> {return user.getName().toUpperCase();})
                .sorted((uu1,uu2)->{return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);
    }
}

