package com.sipios.refactoring.entities;

public enum CustomerType {
    STANDARD_CUSTOMER("STANDARD_CUSTOMER"),
    PREMIUM_CUSTOMER("PREMIUM_CUSTOMER"),
    PLATINUM_CUSTOMER("PLATINUM_CUSTOMER");

    private final String type;

    /**
     * @param type
     */
    CustomerType(final String type) {
        this.type = type;
    }

}
