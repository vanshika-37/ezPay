package com.ezPay.service;

import com.ezPay.service.*;
import com.ezPay.repo.*;
import com.ezPay.controller.*;
import com.ezPay.model.*;

public class UserInterfaceService {
	private UserInterface UI ;
    private MainMenu mainMenu ;
    private SupportDAO supportDAO = new SupportDAO();
    private SupportService supportService = new SupportService(supportDAO);
    private SupportController supportController = new SupportController(supportService);
    
    
    public UserInterfaceService(String deviceType, double deviceWidth, double deviceHeight){
    	UI = new UserInterface(deviceType ,deviceWidth,  deviceHeight);
    	mainMenu = UI.getMainMenu();
    }
    public String getMenuService() {   //getting menu screen
    	return mainMenu.displayMainMenu();
    }
    
    public String selectOptionService(int option) {  // services acc to user input
        switch (option) {
            case 1:
                return mainMenu.goToProfile();
               
            case 2:
                return mainMenu.goToCheckBalance();
               
            case 3:
                return mainMenu.goToPayment();
               
            case 4:
                 mainMenu.goToHelp(supportDAO, supportService, supportController);
                 return "Support Ticket";
                
                
            case 5:
                return "Exiting app";
            default:
                return "Invalid option selected";
        }
    }
}
