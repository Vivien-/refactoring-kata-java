package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.entities.Order;
import com.sipios.refactoring.entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.web.server.ResponseStatusException;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Order(new Item[]{}, "STANDARD_CUSTOMER"))
        );
    }

    @Test
    void testPrices() {
        double tShirtPrice = 30.0;

        Order customer = new Order(new Item[]{new Item("TSHIRT", 2)}, "STANDARD_CUSTOMER");
        Assertions.assertEquals("60.0", controller.getPrice(customer));

        // eventually ...
//        Assertions.assertThrows(SomeKindOfException.class, () -> controller.getPrice(new Body(new Item[]{new Item("TSHIRT", -1)}, "STANDARD_CUSTOMER")));

        customer = new Order(new Item[]{new Item("TSHIRT", 1)}, "STANDARD_CUSTOMER");
        Assertions.assertEquals(String.valueOf(tShirtPrice), controller.getPrice(customer));

        final Order finalCustomer = new Order(new Item[]{new Item("TSHIRT", 9)}, "STANDARD_CUSTOMER");
        Assertions.assertThrows(ResponseStatusException.class, () -> controller.getPrice(finalCustomer));

        finalCustomer.setType("PREMIUM_CUSTOMER");
        customer = new Order(new Item[]{new Item("TSHIRT", 1)}, "PREMIUM_CUSTOMER");
        Assertions.assertEquals(String.valueOf(tShirtPrice * 0.9), controller.getPrice(customer));
        Assertions.assertDoesNotThrow(() -> controller.getPrice(finalCustomer));

    }
}
