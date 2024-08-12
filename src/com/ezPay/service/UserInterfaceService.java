package com.ezPay.service;
import com.ezPay.controller.SupportController;
import com.ezPay.model.UserInterface;
public class UserInterfaceService{
	@SuppressWarnings("unused")
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
		return "User successfully logged in!";
	}
	
	public String goToProfile() {   // returning profile page
		return "Navigating user " + userId + " to Profiles";
	}
	
	public String goToCheckBalance() {   // returning balance page
		return "Navigating user " + userId + " to Balance";
	}

	
	public String goToPayment() {        // returning payment page
		return "Navigating user " + userId + " to Payment";
	}
	
	public void goToHelp() {
		if(supportController == null) supportController = new SupportController(userId);
		supportController.showSupportMenu();
		return;
		
	}
	
	public String exitApplication() {
		return "Logging out user " + userId +"....\n"
				+ "Logged out succesfully!\n";
	}
	
}
















