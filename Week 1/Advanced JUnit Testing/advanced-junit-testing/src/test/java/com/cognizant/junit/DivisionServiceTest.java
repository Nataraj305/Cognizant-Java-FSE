package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DivisionServiceTest {

    DivisionService service = new DivisionService();

    @Test
    void testDivision() {
        assertEquals(5, service.divide(10, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            service.divide(10, 0);
        });
    }
}
