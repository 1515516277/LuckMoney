package com.practice.design.impl.amount;

import com.practice.design.CallPrice;
import com.practice.interfaces.PriceRegion;
import org.springframework.stereotype.Component;

/**
 * 七折
 */
@Component
@PriceRegion(min = 20000,max = 30000)
public class GoldMember implements CallPrice {
    @Override
    public double callPrice(double price) {
        return price*0.7;
    }
}
