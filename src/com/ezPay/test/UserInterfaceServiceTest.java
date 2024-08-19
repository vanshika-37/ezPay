package com.ezPay.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ezPay.model.UserInterface;
import com.ezPay.service.UserInterfaceService;
import com.ezPay.controller.SupportController;

public class UserInterfaceServiceTest {

    private UserInterfaceService userInterfaceService;

    @Before
    public void setUp() {
        // Initialize UserInterfaceService with mock values
        userInterfaceService = new UserInterfaceService(1,"smartPhone", 7.5, 6.0);
        userInterfaceService.registeredUserId(1); // Register a user ID for testing
    }

    @Test
    public void testRegisteredUserId() {
        String expectedOutput = "User successfully logged in!";
        String actualOutput = userInterfaceService.registeredUserId(1);
        assertEquals("The output should match the registration success message", expectedOutput, actualOutput);
    }

    @Test
    public void testGoToProfile() {
        String expectedOutput = "Navigating user 1 to Profiles";
        String actualOutput = userInterfaceService.goToProfile();
        assertEquals("The output should match for navigating to profile", expectedOutput, actualOutput);
    }

    @Test
    public void testGoToCheckBalance() {
        String expectedOutput = "Navigating user 1 to Balance";
        String actualOutput = userInterfaceService.goToCheckBalance();
        assertEquals("The output should match for checking balance", expectedOutput, actualOutput);
    }

    @Test
    public void testGoToPayment() {
        String expectedOutput = "Navigating user 1 to Payment";
        String actualOutput = userInterfaceService.goToPayment();
        assertEquals("The output should match for making payment", expectedOutput, actualOutput);
    }

    @Ignore
    @Test
    public void testGoToHelpWithNullSupportController() {
        // Test when SupportController is not set
        userInterfaceService.setSupportController(null);
        userInterfaceService.goToHelp();
        SupportController supportController = userInterfaceService.getSupportController();
        assertNotNull("The support controller should be initialized when accessing help", supportController);
    }

    @Ignore
    @Test
    public void testGoToHelpWithExistingSupportController() {
        // Test when SupportController is already set
        SupportController existingSupportController = new SupportController(1);
        userInterfaceService.setSupportController(existingSupportController);
        userInterfaceService.goToHelp();
        assertEquals("The support controller should match the existing instance", existingSupportController, userInterfaceService.getSupportController());
    }

    @Test
    public void testExitApplication() {
        String expectedOutput = "Logging out user 1....\nLogged out succesfully!\n";
        String actualOutput = userInterfaceService.exitApplication();
        assertEquals("The output should match for exiting the application", expectedOutput, actualOutput);
    }

    @Test
    public void testGetUI() {
        // Testing getter for UserInterface
        assertNotNull("The UI should be initialized", userInterfaceService.getUI());
    }

    @Test
    public void testSetUI() {
        // Testing setter for UserInterface
        UserInterface mockUI = new UserInterface(2, "tablet", 8.0, 7.0);
        userInterfaceService.setUI(mockUI);
        assertEquals("The UI should be updated to the mock UI", mockUI, userInterfaceService.getUI());
    }
    
    @Test
    public void testSetSupportController() {
        // Testing setter for SupportController
        SupportController mockSupportController = new SupportController(1);
        userInterfaceService.setSupportController(mockSupportController);
        assertEquals("The support controller should be updated to the mock controller", mockSupportController, userInterfaceService.getSupportController());
    }
}
