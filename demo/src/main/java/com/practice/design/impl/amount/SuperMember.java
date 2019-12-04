package com.practice.design.impl.amount;

import com.practice.design.CallPrice;

/**
 * 超级会员 8折
 */
public class SuperMember implements CallPrice {
    @Override
    public double callPrice(double price) {
        return price*0.8;
    }
}
