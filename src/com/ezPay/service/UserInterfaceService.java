package com.ezPay.service;

import com.ezPay.model.UserInterface;
import com.ezPay.repo.DeviceDAO;
import com.ezPay.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ezPay.controller.SupportController;

/**
 * Manages user interactions with the application's user interface.
 */
public class UserInterfaceService {

	private SupportController supportController; // Manages support actions
	private UserInterface UI;
	private int userId;
	private DeviceDAO deviceDAO;

	public UserInterfaceService(int deviceId, DeviceDAO deviceDAO) {
		try {
			this.deviceDAO = deviceDAO;
			UI = fetchDeviceFromDB(deviceId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private UserInterface fetchDeviceFromDB(int deviceId) throws ClassNotFoundException, SQLException, Exception {
	  
	     
		ResultSet resultSet = deviceDAO.getDevice(deviceId);
		
		UserInterface userInterface = new UserInterface();

		if (resultSet.next()) {
			userInterface.setId(resultSet.getInt("id"));
			userInterface.setDeviceType(resultSet.getString("device_type"));
			userInterface.setDeviceWidth(resultSet.getDouble("device_width"));
			userInterface.setDeviceHeight(resultSet.getDouble("device_height"));
		}

	 
		return userInterface;
	}

	public UserInterfaceService(int id, String deviceType, double deviceWidth, double deviceHeight) {
		UI = new UserInterface(id, deviceType, deviceWidth, deviceHeight);
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
