package com.sipios.refactoring.entities.products.impl;

import com.sipios.refactoring.entities.ProductType;
import com.sipios.refactoring.entities.products.Product;

public class DressProduct implements Product {
    @Override
    public ProductType getType() {
        return ProductType.DRESS;
    }

    @Override
    public double getPrice() {
        return 100d;
    }

    @Override
    public double getSalesDiscount() {
        return 0.8;
    }
}
