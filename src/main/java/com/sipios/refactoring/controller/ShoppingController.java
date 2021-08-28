package com.sipios.refactoring.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.sipios.refactoring.entities.Body;
import com.sipios.refactoring.entities.Item;
import com.sipios.refactoring.entities.customers.Customer;
import com.sipios.refactoring.entities.customers.impl.StandardCustomer;
import com.sipios.refactoring.repositories.CustomerClassRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    private final CustomerClassRegistry reg = new CustomerClassRegistry();

    @PostMapping
    public String getPrice(@RequestBody Body b) {
        double p = 0;
        double d;

        if (b.getItems() == null) {
            return "0";
        }

        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        // Compute discount for customer
        Class<? extends Customer> customerClass = reg.getCustomerClassByType(b.getType());
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

        d = customer.getDiscountRate();

        // Compute total amount depending on the types and quantity of product and
        // if we are in winter or summer discounts periods
        if (
            !(
                cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                    cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                    cal.get(Calendar.MONTH) == 5
            ) &&
                !(
                    cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                        cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                        cal.get(Calendar.MONTH) == 0
                )
        ) {
            for (int i = 0; i < b.getItems().length; i++) {
                Item it = b.getItems()[i];

                if (it.getType().equals("TSHIRT")) {
                    p += 30 * it.getNb() * d;
                } else if (it.getType().equals("DRESS")) {
                    p += 50 * it.getNb() * d;
                } else if (it.getType().equals("JACKET")) {
                    p += 100 * it.getNb() * d;
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getNb();
                // }
            }
        } else {
            for (int i = 0; i < b.getItems().length; i++) {
                Item it = b.getItems()[i];

                if (it.getType().equals("TSHIRT")) {
                    p += 30 * it.getNb() * d;
                } else if (it.getType().equals("DRESS")) {
                    p += 50 * it.getNb() * 0.8 * d;
                } else if (it.getType().equals("JACKET")) {
                    p += 100 * it.getNb() * 0.9 * d;
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getNb();
                // }
            }
        }

        if (p > customer.getMaximumOrderPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price (" + p + ") is too high for " + customer.getType() + " customer");
        }

        return String.valueOf(p);
    }
}

