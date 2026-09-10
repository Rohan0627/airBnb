package com.rohan.airBnb.strategy;

import com.rohan.airBnb.Entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);

}
