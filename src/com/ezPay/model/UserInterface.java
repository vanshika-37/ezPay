package com.ezPay.model;




public class UserInterface{   
	
	private String deviceType;
	private double deviceWidth;
	private double deviceHeight;
	
	
	public UserInterface(String deviceType, double deviceWidth, double deviceHeight){
		this.deviceType = deviceType;
		this.deviceWidth = deviceWidth;
		this.deviceHeight = deviceHeight;
	    
		
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





