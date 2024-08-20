package com.ezPay.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ezPay.controller.SupportController;
import com.ezPay.model.UserInterface;
import com.ezPay.service.UserInterfaceService;

public class UserInterfaceServiceTest {

    private UserInterfaceService userInterfaceService;

     /**
     * Set up method that runs before each test. Initializes the UserInterfaceService 
     * with mock values and registers a user ID for testing purposes.
     */
    @Before
    public void setUp() {
        // Initialize UserInterfaceService with mock values
        userInterfaceService = new UserInterfaceService("smartPhone", 7.5, 6);
        userInterfaceService.registeredUserId(1); // Register a user ID for testing
    }
    
    /**
     * Test the registeredUserId method to ensure it returns the correct success message 
     * when a user is logged in.
     */
    @Test
    public void testRegisteredUserId() {
        String expectedOutput = "User successfully logged in!";
        String actualOutput = userInterfaceService.registeredUserId(1);
        assertEquals("The output should match the registration success message", expectedOutput, actualOutput);
    }

    /**
     * Test the goToProfile method to verify that the correct navigation message 
     * is returned when the user navigates to the profile.
     */
    @Test
    public void testGoToProfile() {
        String expectedOutput = "Navigating user 1 to Profiles";
        String actualOutput = userInterfaceService.goToProfile();
        assertEquals("The output should match for navigating to profile", expectedOutput, actualOutput);
    }

    /**
     * Test the goToCheckBalance method to ensure that the correct message is returned 
     * when the user navigates to the balance checking screen.
     */
    @Test
    public void testGoToCheckBalance() {
        String expectedOutput = "Navigating user 1 to Balance";
        String actualOutput = userInterfaceService.goToCheckBalance();
        assertEquals("The output should match for checking balance", expectedOutput, actualOutput);
    }

    /**
     * Test the goToPayment method to verify that the correct navigation message 
     * is returned when the user navigates to the payment screen.
     */
    @Test
    public void testGoToPayment() {
        String expectedOutput = "Navigating user 1 to Payment";
        String actualOutput = userInterfaceService.goToPayment();
        assertEquals("The output should match for making payment", expectedOutput, actualOutput);
    }

    /**
     * Test the goToHelp method when SupportController is not set. 
     */
    @Ignore
    @Test
    public void testGoToHelpWithNullSupportController() {
        // Test when SupportController is not set
        userInterfaceService.setSupportController(null);
        userInterfaceService.goToHelp();
        SupportController supportController = userInterfaceService.getSupportController();
        assertNotNull("The support controller should be initialized when accessing help", supportController);
    }

    /**
     * Test the goToHelp method when SupportController is already set. 
     */
    @Ignore
    @Test
    public void testGoToHelpWithExistingSupportController() {
        // Test when SupportController is already set
        SupportController existingSupportController = new SupportController(1);
        userInterfaceService.setSupportController(existingSupportController);
        userInterfaceService.goToHelp();
        assertEquals("The support controller should match the existing instance", existingSupportController, userInterfaceService.getSupportController());
    }

    /**
     * Test the exitApplication method to verify that the correct messages 
     * are returned when the user logs out and exits the application.
     */
    @Test
    public void testExitApplication() {
        String expectedOutput = "Logging out user 1....\nLogged out succesfully!\n";
        String actualOutput = userInterfaceService.exitApplication();
        assertEquals("The output should match for exiting the application", expectedOutput, actualOutput);
    }

    /**
     * Test the getUI method to ensure that the UserInterface instance is properly 
     * initialized and returned by the UserInterfaceService.
     */
    @Test
    public void testGetUI() {
        // Testing getter for UserInterface
        assertNotNull("The UI should be initialized", userInterfaceService.getUI());
    }
    
    /**
     * Test the setUI method to verify that the UserInterface instance can be 
     * successfully updated with a new instance.
     */
    @Test
    public void testSetUI() {
        // Testing setter for UserInterface
        UserInterface mockUI = new UserInterface("tablet", 8.0, 7.0);
        userInterfaceService.setUI(mockUI);
        assertEquals("The UI should be updated to the mock UI", mockUI, userInterfaceService.getUI());
    }
    
    /**
     * Test the setSupportController method to ensure that the SupportController instance 
     * can be successfully updated with a new instance.
     */
    @Test
    public void testSetSupportController() {
        // Testing setter for SupportController
        SupportController mockSupportController = new SupportController(1);
        userInterfaceService.setSupportController(mockSupportController);
        assertEquals("The support controller should be updated to the mock controller", mockSupportController, userInterfaceService.getSupportController());
    }
}
