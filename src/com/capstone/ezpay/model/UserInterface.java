package com.capstone.ezpay.model;

/**
 * Represents the user interface configuration for different devices.
 */
public class UserInterface {
	private int id;
	private String deviceType; // Type of the device (e.g., smart phone, tablet, desktop)
	private double deviceWidth; // Width of the device screen
	private double deviceHeight; // Height of the device screen

	public UserInterface() {}
	
	// Constructs a UserInterface with device type and dimensions.
	public UserInterface(int id, String deviceType, double deviceWidth, double deviceHeight) {
		this.id = id;
		this.deviceType = deviceType;
		this.deviceWidth = deviceWidth;
		this.deviceHeight = deviceHeight;
	}

	// Getters and setters for the device attributes
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	@Override 
	 public boolean equals(Object obj) { 
		UserInterface UI =  (UserInterface) obj;
		if(this.id ==UI.getId() && this.deviceType==UI.getDeviceType() && this.deviceHeight == UI.getDeviceHeight() && this.deviceWidth == UI.getDeviceWidth()) {
			return true;
		}
		return false;
		
		
		
	}
}

