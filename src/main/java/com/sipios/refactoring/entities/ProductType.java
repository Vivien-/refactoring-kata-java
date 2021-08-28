package com.sipios.refactoring.entities;

public enum ProductType {
    TSHIRT("TSHIRT"),
    DRESS("DRESS"),
    JACKET("JACKET");

    private final String type;

    ProductType(final String type) {
        this.type = type;
    }

}
