package com.sipios.refactoring.repositories;

import com.sipios.refactoring.entities.CustomerType;
import com.sipios.refactoring.entities.customers.Customer;
import com.sipios.refactoring.entities.customers.impl.PlatiniumCustomer;
import com.sipios.refactoring.entities.customers.impl.PremiumCustomer;
import com.sipios.refactoring.entities.customers.impl.StandardCustomer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRegistry {

    private final Map<String, Customer> customerClasses = new HashMap<>();

    public CustomerRegistry() {
        customerClasses.put(CustomerType.STANDARD_CUSTOMER.name(), new StandardCustomer());
        customerClasses.put(CustomerType.PREMIUM_CUSTOMER.name(), new PremiumCustomer());
        customerClasses.put(CustomerType.PLATINUM_CUSTOMER.name(), new PlatiniumCustomer());

    }

    public Customer getCustomerByType(String type) {
        return customerClasses.getOrDefault(type, new StandardCustomer());
    }

}
