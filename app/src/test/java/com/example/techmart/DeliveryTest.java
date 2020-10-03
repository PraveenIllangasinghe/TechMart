package com.example.techmart;

import com.example.techmart.ishini.OrderModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DeliveryTest {

    private OrderModel orderModal;

    @Before
    public void setUp() throws Exception {
        orderModal = new OrderModel();
        orderModal.setDeliveryFee((float) 200.0);
        orderModal.setTotalAmount((float) 1000.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateTotalCost() {

        float result = orderModal.calculateTotal();
        assertEquals(1200.0,result,1e-15);

        orderModal.setDeliveryFee((float) 150.0);
        orderModal.setTotalAmount((float) 2000.0);
        result = orderModal.calculateTotal();
        assertNotEquals(2000.0,result,1e-15);


    }

}
