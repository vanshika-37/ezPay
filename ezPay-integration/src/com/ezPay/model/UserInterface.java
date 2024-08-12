package com.ezPay.model;
import com.ezPay.controller.*;


public class UserInterface{   
	
	private String deviceType;
	private double deviceWidth;
	private double deviceHeight;
	private MainMenu mainMenu;
	
	public UserInterface(String deviceType, double deviceWidth, double deviceHeight){
		this.deviceType = deviceType;
		this.deviceWidth = deviceWidth;
		this.deviceHeight = deviceHeight;
	    this.mainMenu = new MainMenu();
		
	}
	
	public MainMenu getMainMenu() {  // main menu with services
		return this.mainMenu;
	}
	public String getDeviceType() {  
		return this.deviceType;
	}
	
	public double getDeviceWidth() {
		return this.deviceWidth;
	}
	
	public double getDeviceHeight() {
		return this.deviceHeight;
	}
	
	public void setDeviceType(String type) {
		this.deviceType = type;
		
	}
	
	public void setDeviceWidth(double width) {
		this.deviceWidth = width;
		
	}
	
	public void setDeviceHeight(double height) {
		this.deviceHeight = height;
		
	
		
	}
	
}





