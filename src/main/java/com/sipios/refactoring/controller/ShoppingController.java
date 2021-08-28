package com.sipios.refactoring.controller;

import com.sipios.refactoring.entities.Item;
import com.sipios.refactoring.entities.Order;
import com.sipios.refactoring.entities.customers.Customer;
import com.sipios.refactoring.entities.products.Product;
import com.sipios.refactoring.repositories.CustomerRegistry;
import com.sipios.refactoring.repositories.ProductsRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final CustomerRegistry customerRegistry = new CustomerRegistry();
    private final ProductsRegistry productsRegistry = new ProductsRegistry();

    @PostMapping
    public String getPrice(@RequestBody Order order) {
        double price = 0;
        double customerDiscount;

        Item[] items = order.getItems();

        if (items == null || items.length == 0) {
            return "0";
        }

        Customer customer = customerRegistry.getCustomerByType(order.getType());
        customerDiscount = customer.getDiscountRate();

        for (Item it : items) {
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

