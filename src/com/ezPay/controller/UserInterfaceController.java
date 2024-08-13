package com.ezPay.controller;

import com.ezPay.service.UserInterfaceService;
import java.util.Scanner;

/**
 * The UserInterfaceController class handles user interactions and manages the
 * flow of the application.
 */
public class UserInterfaceController {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Prompt user to select a device type
		System.out.println("Select Device \n 1. smartphone \n 2. tablet \n 3. desktop");
		int deviceSelection = scanner.nextInt();
		double deviceHeight; // Variables to store device dimensions
		double deviceWidth;
		String deviceType = "";

		// Determine device type and dimensions based on user selection
		switch (deviceSelection) {
		case 1:
			deviceType = "smartPhone";
			deviceHeight = 6.5; // Height in inches for smartphone
			deviceWidth = 5; // Width in inches for smartphone
			break;
		case 2:
			deviceType = "tablet";
			deviceHeight = 7; // Height in inches for tablet
			deviceWidth = 8; // Width in inches for tablet
			break;
		case 3:
			deviceType = "desktop";
			deviceHeight = 14; // Height in inches for desktop
			deviceWidth = 16; // Width in inches for desktop
			break;
		default:
			// Default to smartphone if an invalid option is selected
			deviceType = "smartPhone";
			deviceHeight = 7;
			deviceWidth = 8;
			break;
		}

		// Print selected device type
		System.out.println("Device type selected: " + deviceType + "\n\n");

		// Initialize UserInterfaceService with selected device type and dimensions
		UserInterfaceService uiService = new UserInterfaceService(deviceType, deviceHeight, deviceWidth);

		// Prompt user to enter their user ID for login
		System.out.println("----------------------\n" + "LOGIN HERE\n" + "----------------------\n" + "Enter userId: ");
		int userId = scanner.nextInt();

		// Display login message
		System.out.println(uiService.registeredUserId(userId) + "\n");

		int selection;
		boolean exit = false;

		// Main menu loop
		while (!exit) {
			// Display the main menu options
			System.out.println("Welcome User\n" + "-------------------\n" + "      MAIN MENU     \n"
					+ "-------------------\n" + "1. View Profile\n" + "2. Check Balance\n" + "3. Make Payment\n"
					+ "4. Get Help\n" + "5. Exit App");

			// Get user's menu selection
			selection = scanner.nextInt();

			// Process user's menu selection
			switch (selection) {
			case 1:
				// Display user profile
				System.out.println(uiService.goToProfile());
				break;
			case 2:
				// Display user balance
				System.out.println(uiService.goToCheckBalance());
				break;
			case 3:
				// Initiate payment process
				System.out.println(uiService.goToPayment());
				break;
			case 4:
				// Display help menu
				uiService.goToHelp();
				break;
			case 5:
				// Exit application
				System.out.println(uiService.exitApplication());
				exit = true;
				break;
			default:
				// Handle invalid menu option
				System.out.println("Invalid option. Please select a valid menu option.");
			}
		}

		// Close the scanner to free resources
		scanner.close();
	}
}