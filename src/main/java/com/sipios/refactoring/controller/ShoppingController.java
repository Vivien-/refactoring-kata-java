package com.sipios.refactoring.controller;

import com.sipios.refactoring.entities.Body;
import com.sipios.refactoring.entities.Item;
import com.sipios.refactoring.entities.customers.Customer;
import com.sipios.refactoring.entities.customers.impl.StandardCustomer;
import com.sipios.refactoring.entities.products.Product;
import com.sipios.refactoring.repositories.CustomerClassRegistry;
import com.sipios.refactoring.repositories.ProductsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    private final CustomerClassRegistry customerClassRegistry = new CustomerClassRegistry();
    private final ProductsRegistry productsRegistry = new ProductsRegistry();

    @PostMapping
    public String getPrice(@RequestBody Body b) {
        double price = 0;
        double customerDiscount;

        if (b.getItems() == null) {
            return "0";
        }


        Class<? extends Customer> customerClass = customerClassRegistry.getCustomerClassByType(b.getType());
        if (customerClass == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Customer customer;
        try {
            customer = customerClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("Could not instantiate customer for type {}. Assuming this is a standard customer", b.getType());
            customer = new StandardCustomer();
        }

        customerDiscount = customer.getDiscountRate();

        for (int i = 0; i < b.getItems().length; i++) {
            Item it = b.getItems()[i];
            Product product = productsRegistry.getProductByType(it.getType());
            if (product != null) {
                price += product.getPrice() * it.getNb() * product.getDiscount();
            }
        }

        price *= customerDiscount;

        if (price > customer.getMaximumOrderPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price (" + price + ") is too high for " + customer.getType() + " customer");
        }

        return String.valueOf(price);
    }
}

