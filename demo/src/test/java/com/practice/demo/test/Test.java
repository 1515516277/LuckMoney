package com.practice.demo.test;

public class Test {

    public static<T> T test(T c){
        System.out.println(c);
        return c;
    }


    @org.junit.Test
    public void say(){
        test("xxx");
    }
}
