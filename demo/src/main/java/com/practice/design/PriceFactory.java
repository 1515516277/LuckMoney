package com.practice.design;

import com.practice.interfaces.PriceRegion;
import com.practice.service.ServiceLocator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceFactory implements ApplicationContextAware {
    /**
     * 用于保存接口实现类名及对应的类
     */
    private Map<String, CallPrice> map;
    @Autowired
    private ServiceLocator serviceLocator;
    /**
     * 获得对应金额的策略类
     *
     * @param price
     * @return
     * @throws Exception
     */
    public CallPrice getCallPrice(double price) throws Exception {
        for(Map.Entry<String,CallPrice> m:map.entrySet()){
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
    public static PriceFactory getInstance() {
        return new PriceFactory();
    }

    /**
     * 获取此接口所有实现类   需要被spring托管
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.map = applicationContext.getBeansOfType(CallPrice.class);
    }

    public Map<String, CallPrice> getMap() {
        return map;
    }
}
