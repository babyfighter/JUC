package com.adam.function;

import java.util.function.Function;

/**
 * @FunctionalInterface 函数式接口 简化编程模型
 * 只要是函数型接口 可以用lambda表达式简化
 * Function函数型接口，有一个输入参数，有一个输出
 * Predicate判断型接口,有一个输入参数，返回值是布尔值
 * Supplier供给型接口，没有参数，只有返回值
 * Consumer消费型接口，只有输入，没有返回值 foreach()
 */
public class FunctionTest {
    public static void main(String[] args) {
//        工具类： 输出输入的值
//        Function<Object, Object> objectObjectFunction = new Function<Object, Object>() {
//            @Override
//            public Object apply(Object o) {
//                return null;
//            }
//        };
        Function function = (str)->{return str;};
        System.out.println(function.apply("asd"));

    }
}
