package com.sipios.refactoring.entities.customers.impl;

import com.sipios.refactoring.entities.CustomerType;
import com.sipios.refactoring.entities.customers.Customer;

public class PremiumCustomer implements Customer {
    @Override
    public double getDiscountRate() {
        return 0.9d;
    }

    @Override
    public CustomerType getType() {
        return CustomerType.PREMIUM_CUSTOMER;
    }

    @Override
    public double getMaximumOrderPrice() {
        return 800d;
    }
}
