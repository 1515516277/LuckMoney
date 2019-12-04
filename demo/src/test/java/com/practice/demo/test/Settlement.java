package com.practice.demo.test;

import com.practice.design.CallPrice;
import com.practice.design.PriceFactory;
import org.junit.Test;

import java.util.ServiceLoader;

public class Settlement {

    /**
     * 总价
     */
    private double totalPrice = 0;
    /**
     * 单次消费的金额
     */
    private double amount = 0;

    /**
     * 购买的方法
     * @param amount    商品价格
     * @return double   支付的价格
     */
    public double buy(double amount) throws Exception {
        this.amount = amount;
        this.totalPrice += this.amount;
        return PriceFactory.getInstance().getCallPrice(this.amount).callPrice(this.amount);
    }

    @Test
    public void test() throws Exception {
//        buy(5000);
//        System.out.println(PriceFactory.class.getPackage().getName());
        ServiceLoader<CallPrice> loader = ServiceLoader.load(CallPrice.class);
        for (CallPrice lo:loader){
            System.out.println(1);
            System.out.println(lo.getClass());
        }

        System.out.println();
    }

}
