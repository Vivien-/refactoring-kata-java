package com.sipios.refactoring.repositories;

import com.sipios.refactoring.entities.ProductType;
import com.sipios.refactoring.entities.products.Product;
import com.sipios.refactoring.entities.products.impl.DressProduct;
import com.sipios.refactoring.entities.products.impl.JacketProduct;
import com.sipios.refactoring.entities.products.impl.TshirtProduct;

import java.util.HashMap;
import java.util.Map;

public class ProductsRegistry {

    private final Map<String, Product> products = new HashMap<>();

    public ProductsRegistry() {
        products.put(ProductType.TSHIRT.name(), new TshirtProduct());
        products.put(ProductType.JACKET.name(), new JacketProduct());
        products.put(ProductType.DRESS.name(), new DressProduct());

    }

    public Product getProductByType(String type) {
        return products.getOrDefault(type, null);
    }

}
