package com.ezpay.dashboard.controller;

import com.ezpay.dashboard.service.UserInterfaceService;
import java.util.Scanner;

public class UserInterfaceController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Device \n 1. smartphone \n 2. tablet \n 3. desktop");
        int deviceSelection = scanner.nextInt();

        String deviceType = "";
        switch (deviceSelection) {
            case 1:
                deviceType = "smartPhone";
                break;
            case 2:
                deviceType = "tablet";
                break;
            case 3:
                deviceType = "desktop";
                break;
            default:
                deviceType = "smartPhone";
                break;
        }

        System.out.println("Device type selected: " + deviceType);
        UserInterfaceService uiService = new UserInterfaceService();

        boolean exit = false;
        while (!exit) {
            // Display the main menu before each selection
            System.out.println(uiService.getMenuService());

            int selection = scanner.nextInt();

            switch (selection) {
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
                    System.out.println(uiService.selectOptionService(4));
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
