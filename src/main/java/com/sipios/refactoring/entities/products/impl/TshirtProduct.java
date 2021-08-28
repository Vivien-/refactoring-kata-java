package com.sipios.refactoring.entities.products.impl;

import com.sipios.refactoring.entities.ProductType;
import com.sipios.refactoring.entities.products.Product;

public class TshirtProduct implements Product {
    @Override
    public ProductType getType() {
        return ProductType.TSHIRT;
    }

    @Override
    public double getPrice() {
        return 30d;
    }

    @Override
    public double getSalesDiscount() {
        return 1;
    }
}
