package com.cognizant.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    EvenCheckerTest.class,
    CalculatorTest.class,
    OrderServiceTest.class,
    DivisionServiceTest.class,
    PerformanceServiceTest.class
})
public class AllTests {
}