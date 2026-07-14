package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {

    OrderService orderService = new OrderService();

    @Test
    @Order(1)
    void testCreateOrder() {
        System.out.println("Creating Order...");
        assertEquals("Order Created", orderService.createOrder());
    }

    @Test
    @Order(2)
    void testProcessOrder() {
        System.out.println("Processing Order...");
        assertEquals("Order Processed", orderService.processOrder());
    }

    @Test
    @Order(3)
    void testDeliverOrder() {
        System.out.println("Delivering Order...");
        assertEquals("Order Delivered", orderService.deliverOrder());
    }
}
