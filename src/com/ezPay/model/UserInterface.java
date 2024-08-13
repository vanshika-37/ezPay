package com.ezPay.model;

/**
 * Represents the user interface configuration for different devices.
 */
public class UserInterface {
	private String deviceType; // Type of the device (e.g., smartphone, tablet, desktop)
	private double deviceWidth; // Width of the device screen
	private double deviceHeight; // Height of the device screen

	// Constructs a UserInterface with device type and dimensions.
	public UserInterface(String deviceType, double deviceWidth, double deviceHeight) {
		this.deviceType = deviceType;
		this.deviceWidth = deviceWidth;
		this.deviceHeight = deviceHeight;
	}

	// Getters and setters for the device attributes
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String type) {
		this.deviceType = type;
	}

	public double getDeviceWidth() {
		return this.deviceWidth;
	}

	public void setDeviceWidth(double width) {
		this.deviceWidth = width;
	}

	public double getDeviceHeight() {
		return this.deviceHeight;
	}

	public void setDeviceHeight(double height) {
		this.deviceHeight = height;
	}
}
