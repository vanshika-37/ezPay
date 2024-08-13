package com.ezPay.service;

import com.ezPay.controller.SupportController;
import com.ezPay.model.UserInterface;

/**
 * Manages user interactions with the application's user interface.
 */
public class UserInterfaceService {

	private UserInterface UI; // User interface for the device
	private int userId; // Current user's ID
	private SupportController supportController; // Manages support actions

	// Initializes with device details.
	public UserInterfaceService(String deviceType, double deviceWidth, double deviceHeight) {
		UI = new UserInterface(deviceType, deviceWidth, deviceHeight);
	}

	// Gets the UserInterface object.
	public UserInterface getUI() {
		return UI;
	}

	// Sets the UserInterface object.
	public void setUI(UserInterface uI) {
		UI = uI;
	}

	// Gets the SupportController object.
	public SupportController getSupportController() {
		return supportController;
	}

	// Sets the SupportController object.
	public void setSupportController(SupportController supportController) {
		this.supportController = supportController;
	}

	// Logs in the user by setting the user ID.
	public String registeredUserId(int userId) {
		this.userId = userId;
		return "User successfully logged in!\n";
	}

	// Navigates to the profile page.
	public String goToProfile() {
		return "Navigating user " + userId + " to Profiles\n";
	}

	// Navigates to the balance page.
	public String goToCheckBalance() {
		return "Navigating user " + userId + " to Balance\n";
	}

	// Navigates to the payment page.
	public String goToPayment() {
		return "Navigating user " + userId + " to Payment\n";
	}

	// Navigates to the help section, creating a SupportController if needed.
	public String goToHelp() {
		if (supportController == null) {
			supportController = new SupportController(userId);
		}
		supportController.showSupportMenu();
		return "Navigating to dashboard from Help\n";
	}

	// Logs out the user.
	public String exitApplication() {
		return "Logging out user " + userId + "....\n" + "Logged out successfully!\n";
	}
}
