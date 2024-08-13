package com.ezPay.service;
import com.ezPay.controller.SupportController;
import com.ezPay.model.UserInterface;
public class UserInterfaceService{
	
	private UserInterface UI ;
	private int userId;
    private SupportController supportController;
	
	public UserInterfaceService(String deviceType, double deviceWidth, double deviceHeight){
    	UI = new UserInterface(deviceType ,deviceWidth,  deviceHeight);
    }
	
	public UserInterface getUI() {
		return UI;
	}

	public void setUI(UserInterface uI) {
		UI = uI;
	}

	public SupportController getSupportController() {
		return supportController;
	}

	public void setSupportController(SupportController supportController) {
		this.supportController = supportController;
	}

	public String registeredUserId(int userId) {  
		this.userId = userId;
		return "User successfully logged in!\n";
	}
	
	public String goToProfile() {   // navigating user to profile page
		return "Navigating user " + userId + " to Profiles\n";
	}
	
	public String goToCheckBalance() {   // navigating user to balance page
		return "Navigating user " + userId + " to Balance\n";
	}

	
	public String goToPayment() {        // returning payment page
		return "Navigating user " + userId + " to Payment\n";
	}
	
	public String goToHelp() {
		if(supportController == null) supportController = new SupportController(userId);
		supportController.showSupportMenu();
		return "Navigating to dashboard from Help\n";
		
	}
	
	public String exitApplication() {
		return "Logging out user " + userId +"....\n"
				+ "Logged out succesfully!\n";
	}
	
}
















