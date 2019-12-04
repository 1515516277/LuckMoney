package com.practice.service;


import com.practice.design.CallPrice;
import com.practice.interfaces.PriceRegion;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ServiceLocator implements ApplicationContextAware {
    /**
     * 用于保存接口实现类名及对应的类
     */
    private Map<String, CallPrice> map;

    /**
     * 获取应用上下文并获取相应的接口实现类
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //根据接口类型返回相应的所有bean
       map = applicationContext.getBeansOfType(CallPrice.class);
    }

    public Map<String, CallPrice> getMap(){
        return map;
    }

    /**
     * 获得对应金额的策略类
     *
     * @param price
     * @return
     * @throws Exception
     */
    public CallPrice getCallPrice(double price) throws Exception {
        for(Map.Entry<String,CallPrice> m:getMap().entrySet()){
            Class<? extends CallPrice> aClass = m.getValue().getClass();
            PriceRegion priceRegion = m.getValue().getClass().getAnnotation(PriceRegion.class);
            if (priceRegion.max() > price && priceRegion.min() < price) {
                return m.getValue();
            }
        }
        return null;
    }

    /**
     * 获得工厂实例
     *
     * @return
     */
    public static ServiceLocator getInstance() {
        return new ServiceLocator();
    }

}

