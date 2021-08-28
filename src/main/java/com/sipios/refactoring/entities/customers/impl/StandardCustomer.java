package com.sipios.refactoring.entities.customers.impl;

import com.sipios.refactoring.entities.CustomerType;
import com.sipios.refactoring.entities.customers.Customer;

public class StandardCustomer implements Customer {
    @Override
    public double getDiscountRate() {
        return 1d;
    }

    @Override
    public CustomerType getType() {
        return CustomerType.STANDARD_CUSTOMER;
    }

    @Override
    public double getMaximumOrderPrice() {
        return 200d;
    }
}
