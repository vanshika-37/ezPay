package com.ezPay.controller;

import com.ezPay.service.*;
import java.util.Scanner;

public class UserInterfaceController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Device \n 1. smartphone \n 2. tablet \n 3. desktop");
        int deviceSelection = scanner.nextInt();
        double deviceHeight;
        double deviceWidth;
        String deviceType = "";
        switch (deviceSelection) {
            case 1:
                deviceType = "smartPhone";  
                deviceHeight = 6.5;   /// hard coded values
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

        System.out.println("Device type selected: " + deviceType);
        UserInterfaceService uiService = new UserInterfaceService(deviceType,deviceHeight,deviceWidth);

        boolean exit = false;
        while (!exit) {
            // Display the main menu before each selection
            System.out.println(uiService.getMenuService());

            int selection = scanner.nextInt();

            switch (selection) {     //Services according to the user input
                case 1:
                    System.out.println(uiService.selectOptionService(1));
                    break;
                case 2:
                    System.out.println(uiService.selectOptionService(2));
                    break;
                case 3:
                    System.out.println(uiService.selectOptionService(3));
                    break;
                case 4:
                	uiService.selectOptionService(4);
                    break;
                case 5:
                    System.out.println(uiService.selectOptionService(5));
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid menu option.");
            }

            if (!exit) {
                System.out.println("1. Exit app    2. Go back to MAIN MENU");
                int nextAction = scanner.nextInt();
                if (nextAction == 1) {
                    exit = true;
                }
            }
        }

        System.out.println("THANK YOU");
        scanner.close();
    }
}
