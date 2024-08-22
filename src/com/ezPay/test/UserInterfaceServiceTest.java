package com.ezPay.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ezPay.model.UserInterface;
import com.ezPay.repo.DeviceDAO;
import com.ezPay.service.UserInterfaceService;
import com.ezPay.controller.SupportController;

public class UserInterfaceServiceTest {

    private UserInterfaceService userInterfaceService;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        // Initialize UserInterfaceService with mock DAO
    	DeviceDAO deviceDAO = mock(DeviceDAO.class);
    	ResultSet resultSet = mock(ResultSet.class);
		
        when(resultSet.next()).thenReturn(true); 
        when(resultSet.getString("device_type")).thenReturn("smartphone");
        when(resultSet.getDouble("device_width")).thenReturn(6.5);
        when(resultSet.getDouble("device_height")).thenReturn(5.0);
        when(resultSet.getInt("id")).thenReturn(1);
        when(deviceDAO.getDevice(1)).thenReturn(resultSet);
        userInterfaceService = new UserInterfaceService(1,deviceDAO);
        userInterfaceService.registeredUserId(1); // Register a user ID for testing
    }

    @Test
    public void testRegisteredUserId() {
        String expectedOutput = "User successfully logged in!\n";
        String actualOutput = userInterfaceService.registeredUserId(1);
        assertEquals("The output should match the registration success message", expectedOutput, actualOutput);
    }

    @Test
    public void testFetchDeviceFromDB() throws SQLException, ClassNotFoundException {
		DeviceDAO deviceDAO = mock(DeviceDAO.class);
		
		ResultSet resultSet1 = mock(ResultSet.class);
		ResultSet resultSet2 = mock(ResultSet.class);
		
        when(resultSet1.next()).thenReturn(true); 
        when(resultSet1.getString("device_type")).thenReturn("smartphone");
        when(resultSet1.getDouble("device_width")).thenReturn(6.5);
        when(resultSet1.getDouble("device_height")).thenReturn(5.0);
        when(resultSet1.getInt("id")).thenReturn(1);
        when(deviceDAO.getDevice(1)).thenReturn(resultSet1);
        
        when(resultSet2.next()).thenReturn(true); 
        when(resultSet2.getString("device_type")).thenReturn("tablet");
        when(resultSet2.getDouble("device_width")).thenReturn(7.0);
        when(resultSet2.getDouble("device_height")).thenReturn(8.0);
        when(resultSet2.getInt("id")).thenReturn(2);
        when(deviceDAO.getDevice(2)).thenReturn(resultSet2);
        UserInterfaceService userInterfaceService = new UserInterfaceService(1,deviceDAO);
		
        assertEquals(userInterfaceService.getUI(),new UserInterface(1,"smartphone",6.5,5.0));
        userInterfaceService = new UserInterfaceService(2,deviceDAO);
        assertEquals(userInterfaceService.getUI(),new UserInterface(2,"tablet",7.0,8.0));
        
	}
    @Test
    public void testGoToProfile() {
        String expectedOutput = "Navigating user 1 to Profiles\n";
        String actualOutput = userInterfaceService.goToProfile();
        assertEquals("The output should match for navigating to profile", expectedOutput, actualOutput);
    }

    @Test
    public void testGoToCheckBalance() {
        String expectedOutput = "Navigating user 1 to Balance\n";
        String actualOutput = userInterfaceService.goToCheckBalance();
        assertEquals("The output should match for checking balance", expectedOutput, actualOutput);
    }

    @Test
    public void testGoToPayment() {
        String expectedOutput = "Navigating user 1 to Payment\n";
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
        String expectedOutput = "Logging out user 1....\nLogged out successfully!\n";
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
