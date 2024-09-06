package com.ezpay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class contains integration tests for the EzPay backend application.
 * It ensures that the Spring application context loads successfully.
 * 
 * <p>The test verifies that the application can start up without issues,
 * meaning that all beans and configuration settings are correctly set.
 * 
 * @author Vanshika, Vaishnave
 * @since 5th September, 2024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {

    /**
     * This test method checks if the Spring application context loads correctly.
     * 
     * <p>If this test passes, it indicates that the application can initialize 
     * its components without any configuration errors.
     */
    @Test
    public void contextLoads() {
        // Empty method - test will pass if application context loads successfully
    }
}
