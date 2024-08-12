package com.ezPay.test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import com.ezPay.service.*;

@RunWith(Parameterized.class)
public class UserInterfaceServiceTest {

    private static UserInterfaceService userInterfaceService;
    private int inputOption;
    private String expectedOutput;

    public UserInterfaceServiceTest(int inputOption, String expectedOutput) {
        this.inputOption = inputOption;
        this.expectedOutput = expectedOutput;
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userInterfaceService = new UserInterfaceService("smartPhone",7.5,6);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        userInterfaceService = null;
    }

    @Parameters
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
            {1, "Navigating to Profile"},
            {2, "Navigating to Balance"},
            {3, "Navigating to Payment"},
            {4, "Navigating to Customer Care"},
            {5, "Exiting app"},
            {0, "Invalid option selected"},
            {6, "Invalid option selected"}
        });
    }

    @Test
    public void testSelectOptionService() {
        assertEquals("Output should match for the given input option", expectedOutput, userInterfaceService.selectOptionService(inputOption));
    }
}
