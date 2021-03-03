package com.adam.CPP;

public class Integer_Swap {
    private Integer a;
    private Integer b;

    public Integer_Swap(Integer a, Integer b) {
       System.out.println("a=" + a + "；b=" + b);
       int c = a;
       a = b;
       b = c;
       System.out.println("a=" + a + "；b=" + b);
    }

}