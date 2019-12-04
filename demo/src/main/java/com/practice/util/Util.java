package com.practice.util;

import com.practice.annotation.Exceptions;
import com.practice.controller.TestController;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * 集合分割
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List> splitList(List list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List subList = list.subList(i * len, (Math.min((i + 1) * len, size)));
            result.add(subList);
        }
        return result;
    }


    @Test
    public void test() {
        System.out.println(this.getClass().getName());
        Class<TestController> clazz = TestController.class;
//        Exceptions annotation = clazz.getAnnotation(Exceptions.class);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Exceptions annotation = method.getAnnotation(Exceptions.class);
            if (annotation != null) {
                System.out.println(method.getName());
            }
        }

    }

    @Test
    public void str() {
        String str = "json";
        int i = str.indexOf("?");
        if (i != -1) {
            str+="&login=1";
        }else{
            str+="?login=1";
        }
        System.out.println(str);
    }


}
