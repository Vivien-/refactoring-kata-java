package com.sipios.refactoring.entities.customers;

import com.sipios.refactoring.entities.CustomerType;

public interface Customer {
    double getDiscountRate();
    CustomerType getType();
    double getMaximumOrderPrice();
}
