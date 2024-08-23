package com.capstone.ezpay.controller;

import com.capstone.ezpay.repo.DeviceDAO;
import com.capstone.ezpay.service.UserInterfaceService;

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
		int deviceId = scanner.nextInt();

		// Initialize UserInterfaceService with the selected device ID
		DeviceDAO deviceDAO = new DeviceDAO();
		UserInterfaceService uiService = new UserInterfaceService(deviceId,deviceDAO);

		// Fetch the UserInterface object to display the device type
		String deviceType = uiService.getUI().getDeviceType();
		System.out.println("Device type selected: " + deviceType + "\n\n");

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