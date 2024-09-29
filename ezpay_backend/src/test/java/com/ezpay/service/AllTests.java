package com.ezpay.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite class that runs all unit tests for the specified classes.
 * This class combines multiple test classes into a suite to be run together.
 * It is used to organize and group related test cases in one place for convenience.
 * 
 * Classes selected for the test suite:
 * - ChatServiceTest
 * - DialogflowServiceTest
 * - TicketServiceTest
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Suite  // Indicates that this is a JUnit test suite.
@SelectClasses({ ChatServiceTest.class, DialogflowServiceTest.class, TicketServiceTest.class }) 
// Specifies the test classes to include in the suite.
public class AllTests {
    // This class does not require additional code as it only serves as a container for the test suite.
}
