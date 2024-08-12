package com.ezpay.dashboard.service;

import com.ezpay.dashboard.repo.MainMenu;

public class UserInterfaceService {
    private MainMenu mainMenu = new MainMenu();
    
    public String getMenuService() {
    	return mainMenu.displayMainMenu();
    }
    
    public String selectOptionService(int option) {
        switch (option) {
            case 1:
                return mainMenu.goToProfile();
            case 2:
                return mainMenu.goToCheckBalance();
            case 3:
                return mainMenu.goToPayment();
            case 4:
                return mainMenu.goToHelp();
            case 5:
                return "Exiting app";
            default:
                return "Invalid option selected";
        }
    }
}
