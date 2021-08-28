package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.entities.Body;
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
            () -> controller.getPrice(new Body(new Item[]{}, "STANDARD_CUSTOMER"))
        );
    }

    @Test
    void testPrices() {
        double tShirtPrice = 30.0;

        Body customer = new Body(new Item[]{new Item("TSHIRT", 2)}, "STANDARD_CUSTOMER");
        Assertions.assertEquals("60.0", controller.getPrice(customer));

        // eventually ...
//        Assertions.assertThrows(SomeKindOfException.class, () -> controller.getPrice(new Body(new Item[]{new Item("TSHIRT", -1)}, "STANDARD_CUSTOMER")));

        customer = new Body(new Item[]{new Item("TSHIRT", 1)}, "STANDARD_CUSTOMER");
        Assertions.assertEquals(String.valueOf(tShirtPrice), controller.getPrice(customer));

        final Body finalCustomer = new Body(new Item[]{new Item("TSHIRT", 9)}, "STANDARD_CUSTOMER");
        Assertions.assertThrows(ResponseStatusException.class, () -> controller.getPrice(finalCustomer));

        finalCustomer.setType("PREMIUM_CUSTOMER");
        customer = new Body(new Item[]{new Item("TSHIRT", 1)}, "PREMIUM_CUSTOMER");
        Assertions.assertEquals(String.valueOf(tShirtPrice * 0.9), controller.getPrice(customer));
        Assertions.assertDoesNotThrow(() -> controller.getPrice(finalCustomer));

    }
}
