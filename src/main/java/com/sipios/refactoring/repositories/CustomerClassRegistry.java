package com.sipios.refactoring.repositories;

import com.sipios.refactoring.entities.CustomerType;
import com.sipios.refactoring.entities.customers.Customer;
import com.sipios.refactoring.entities.customers.impl.PlatiniumCustomer;
import com.sipios.refactoring.entities.customers.impl.PremiumCustomer;
import com.sipios.refactoring.entities.customers.impl.StandardCustomer;

import java.util.HashMap;
import java.util.Map;

public class CustomerClassRegistry {

    private final Map<String, Class<? extends Customer>> customerClasses = new HashMap<>();

    public CustomerClassRegistry() {
        customerClasses.put(CustomerType.STANDARD_CUSTOMER.name(), StandardCustomer.class);
        customerClasses.put(CustomerType.PREMIUM_CUSTOMER.name(), PremiumCustomer.class);
        customerClasses.put(CustomerType.PLATINUM_CUSTOMER.name(), PlatiniumCustomer.class);

    }

    public Class<? extends Customer> getCustomerClassByType(String type) {
        System.out.println(customerClasses);
        System.out.println(customerClasses.get(type));
        return customerClasses.getOrDefault(type, null);
    }

}
