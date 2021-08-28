package com.sipios.refactoring.entities.products;

import com.sipios.refactoring.entities.ProductType;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public interface Product {
    default double getDiscount() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        if (
            cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                cal.get(Calendar.MONTH) == Calendar.JUNE || cal.get(Calendar.MONTH) == Calendar.JANUARY
        ) {
            return getSalesDiscount();
        }
        return 1;
    }

    ProductType getType();

    double getPrice();

    double getSalesDiscount();
}
