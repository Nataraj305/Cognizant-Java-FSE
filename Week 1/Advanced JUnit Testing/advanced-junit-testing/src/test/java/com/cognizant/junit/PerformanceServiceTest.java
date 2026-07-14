package com.cognizant.junit;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import org.junit.jupiter.api.Test;

public class PerformanceServiceTest {

    PerformanceService service = new PerformanceService();

    @Test
    void testPerformance() {

        assertTimeout(Duration.ofSeconds(2), () -> {
            service.performTask();
        });

    }
}
