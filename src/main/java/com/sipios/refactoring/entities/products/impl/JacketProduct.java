package com.sipios.refactoring.entities.products.impl;

import com.sipios.refactoring.entities.ProductType;
import com.sipios.refactoring.entities.products.Product;

public class JacketProduct implements Product {
    @Override
    public ProductType getType() {
        return ProductType.JACKET;
    }

    @Override
    public double getPrice() {
        return 50d;
    }

    @Override
    public double getSalesDiscount() {
        return 0.9;
    }

}
