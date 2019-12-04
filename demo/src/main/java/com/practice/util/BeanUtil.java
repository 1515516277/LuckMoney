package com.practice.util;

import com.practice.pojo.dto.RedPacketDTO;
import com.practice.pojo.model.RedPacketModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class BeanUtil {
    private static final Logger log = LoggerFactory.getLogger(BeanMapperUtil.class);
    // 使用多线程安全的Map来缓存BeanCopier，由于读操作远大于写，所以性能影响可以忽略
    public static ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

    /**
     * 通过cglib BeanCopier形式
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopierMap.putIfAbsent(beanKey, copier);// putIfAbsent已经实现原子操作了。
        copier = beanCopierMap.get(beanKey);
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    public static <T> T map(Object source, Object target) {
        copyProperties(source, target);
        return (T) target;
    }

    /**
     * ×通过常规反射形式
     * DTO对象转换为实体对象。如命名不规范或其他原因导致失败。
     *
     * @param t 源转换的对象
     * @param e 目标转换的对象
     */
    public static <T, E> void transalte(T t, E e) {
        Method[] tms = t.getClass().getDeclaredMethods();
        Method[] tes = e.getClass().getDeclaredMethods();
        for (Method m1 : tms) {
            if (m1.getName().startsWith("get")) {
                String mNameSubfix = m1.getName().substring(3);
                String forName = "set" + mNameSubfix;
                for (Method m2 : tes) {
                    if (m2.getName().equals(forName)) {
                        // 如果类型一致，或者m2的参数类型是m1的返回类型的父类或接口
                        boolean canContinue = m2.getParameterTypes()[0].isAssignableFrom(m1.getReturnType());
                        if (canContinue) {
                            try {
                                m2.invoke(e, m1.invoke(t));
                                break;
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                                // TODO Auto-generated catch block
                                log.debug("DTO 2 Entity转换失败");
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }

        }
        log.debug("转换完成");

    }




    public static void timer1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
                try {
                    Thread.sleep(2000);
                    System.out.println("登陆");
                    timer.cancel();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒

    }

    @Test
    public void test() {
        System.out.println(1);
        System.out.println(3);
    }


    @Async
    public void test2(){
        try {
            Thread.sleep(110000);
            System.out.println(222);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        timer1();
    }
}
