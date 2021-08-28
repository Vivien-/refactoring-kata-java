package com.sipios.refactoring.entities.customers.impl;

import com.sipios.refactoring.entities.CustomerType;
import com.sipios.refactoring.entities.customers.Customer;

public class PlatiniumCustomer implements Customer {
    @Override
    public double getDiscountRate() {
        return 0.5d;
    }

    @Override
    public CustomerType getType() {
        return CustomerType.PLATINUM_CUSTOMER;
    }

    @Override
    public double getMaximumOrderPrice() {
        return 2000d;
    }
}
