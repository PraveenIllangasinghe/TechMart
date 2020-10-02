//IT19124636
package com.example.techmart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {

    private Cart testCart;

    @Before
    public void setUp() throws Exception {
        testCart = new Cart();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateNetAmountOfItem() {

        float result = testCart.calculateNetAmountOfItem(2, 10000);
        assertEquals(20000,result,1e-15);

        result = testCart.calculateNetAmountOfItem(6, 27000);
        assertEquals(162000,result,1e-15);

        result = testCart.calculateNetAmountOfItem(4, 77000);
        assertEquals(308000,result,1e-15);

        result = testCart.calculateNetAmountOfItem(12, 12000);
        assertEquals(144000,result,1e-15);

        result = testCart.calculateNetAmountOfItem(8, 31000);
        assertEquals(248000,result,1e-15);

    }
}