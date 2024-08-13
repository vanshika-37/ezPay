package com.ezPay.controller;

import com.ezPay.service.*;
import java.util.Scanner;

public class UserInterfaceController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Device \n 1. smartphone \n 2. tablet \n 3. desktop");
        int deviceSelection = scanner.nextInt();
        double deviceHeight;   // values of screen sizes in inches
        double deviceWidth;
        String deviceType = "";
        switch (deviceSelection) {
            case 1:
                deviceType = "smartPhone";  
                deviceHeight = 6.5;
                deviceWidth = 5;
                break;
            case 2:
                deviceType = "tablet";
                deviceHeight =7;
                deviceWidth = 8;
                break;
            case 3:
                deviceType = "desktop";
                deviceHeight =14;
                deviceWidth = 16;
                break;
            default:
                deviceType = "smartPhone";
                deviceHeight =7;
                deviceWidth = 8;
                break;
        }

        System.out.println("Device type selected: " + deviceType + "\n\n");
        UserInterfaceService uiService = new UserInterfaceService(deviceType,deviceHeight,deviceWidth);
        System.out.println("----------------------\n"
        		+ "LOGIN HERE\n"
        		+ "----------------------\n"
        		+ "Enter userId: ");
        int userId = scanner.nextInt();
        System.out.println(uiService.registeredUserId(userId)+"\n");
        
        int selection;
        boolean exit = false;
        while (!exit) {
            // Display the main menu before each selection
            System.out.println("Welcome User\n"
            		+ "-------------------\n"
            		+ "      MAIN MENU     \n"
            		+ "-------------------\n"
            		+ "1. View Profile\n"
            		+ "2. Check Balance\n"
            		+ "3. Make Payment\n"
            		+ "4. Get Help\n"
            		+ "5. Exit App"
            		);

            selection = scanner.nextInt();

            switch (selection) {     //Services according to the user input
                case 1:
                    System.out.println(uiService.goToProfile());
                    break;
                case 2:
                    System.out.println(uiService.goToCheckBalance());
                    break;
                case 3:
                    System.out.println(uiService.goToPayment());
                    break;
                case 4:
                	System.out.println(uiService.goToHelp());
                    break;
                case 5:
                    System.out.println(uiService.exitApplication());
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid menu option.");
            }
        }
        scanner.close();
    }
}
