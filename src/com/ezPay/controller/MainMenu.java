package com.ezPay.controller;
import com.ezPay.repo.SupportDAO;
import com.ezPay.service.*;
import java.util.*;
public class MainMenu{
	
	
	public String displayMainMenu() {
		return "Welcome User Name\n-------------------\n      MAIN MENU     \n-------------------\n1. View Profile\n2. Check Balance\n3. Make Payment\n4. Get Help\n5. Exit App";
	}
	
	public String goToProfile() {   // returning profile page
		return "Navigating to Profiles";
	}
	
	public String goToCheckBalance() {   // returning balance page
		return "Navigating to Balance";
	}

	
	public String goToPayment() {        // returning payment page
		return "Navigating to Payment";
	}
	
	public void goToHelp(SupportDAO supportDAO, SupportService supportService, SupportController supportController) {    // support system to raise tickets for issues
		Scanner s = new Scanner(System.in);
		int userId;
		String issueDescription;
		
		

		System.out.println("Hii !! How may I help you ?");
		System.out.println("1. Create Ticket \n 2. View Ticket \n 3. Resolve Ticket \n 4. Exit \nPlease Enter choice: ");
		
		int choice = s.nextInt();
		s.nextLine();
        
		switch (choice) {
        	case 1:
		        supportController.createTicket();
		        System.out.println("\n\n");
		        break;
        	case 2:
        		System.out.println("Please mention your user ID");
				userId = s.nextInt();
				System.out.println("Here is your ticket info.");
				supportController.viewTickets(userId);
				System.out.println("\n\n");
				break;
        	case 3:
        		System.out.println("Please mention your user ID");
				userId = s.nextInt();
        		supportController.viewTickets(userId);
        		System.out.println("Choose appropriate ticket id to resolve.");
		        supportController.resolveTicket(4);
		        System.out.println("\n\n");
		        break;
		}
		//s.close();
		return;
		
	}
	
	
	

	
}
















