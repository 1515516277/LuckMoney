package com.practice.design.impl.amount;

import com.practice.design.CallPrice;
import com.practice.interfaces.PriceRegion;

/**
 * 原价
 */
@PriceRegion(max = 10000)
public class OrdinaryPlayer implements CallPrice {
    @Override
    public double callPrice(double price) {
        return price;
    }
}
