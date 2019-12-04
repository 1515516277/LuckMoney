package com.practice.design.impl.amount;

import com.practice.design.CallPrice;
import com.practice.interfaces.PriceRegion;
import org.springframework.stereotype.Component;

/**
 * 会员 9折
 */
@Component
@PriceRegion(min = 10000,max = 20000)
public class Member implements CallPrice {
    @Override
    public double callPrice(double price) {
        return price*0.9;
    }
}
