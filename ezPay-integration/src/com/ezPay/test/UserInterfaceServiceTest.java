package com.ezPay.test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ezPay.service.UserInterfaceService;

import java.util.Arrays;
import java.util.Collection;

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
		userInterfaceService = new UserInterfaceService("smartPhone", 7.5, 6);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userInterfaceService = null;
	}

	@Parameters
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] { 
			{ 1, "Navigating to Profile" }, 
			{ 2, "Navigating to Balance" },
			{ 3, "Navigating to Payment" }, 
			{ 4, "Support Ticket" }, 
			{ 5, "Exiting app" },
			{ 0, "Invalid option selected" }, 
			{ 6, "Invalid option selected" }
		});
	}

	@Test
	public void testSelectOptionService() {
		assertEquals(expectedOutput, userInterfaceService.selectOptionService(inputOption));
	}


	@Test
    public void testGetMenuService() {
        // Expected output from the displayMainMenu() method in MainMenu class
        String expectedMenu = "Welcome User Name\n" +
                "-------------------\n" +
                "      MAIN MENU     \n" +
                "-------------------\n" +
                "1. View Profile\n" +
                "2. Check Balance\n" +
                "3. Make Payment\n" +
                "4. Get Help\n" +
                "5. Exit App";
        
        String actualMenu = userInterfaceService.getMenuService();
        
        // Assertion to check if the actual output matches the expected output
        assertEquals("The menu should match the expected string", expectedMenu, actualMenu);
    }
}
