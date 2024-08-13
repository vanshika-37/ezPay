package com.ezPay.controller;

import java.util.List;
import java.util.Scanner;

import com.ezPay.model.SupportTicket;
import com.ezPay.service.SupportService;

public class SupportController {
    private SupportService supportService;
    private int userId;

    public SupportController(int userId) {
    	supportService = new SupportService();
    	this.userId = userId;
    }
    
    public void showSupportMenu() {
    	@SuppressWarnings("resource")
    	Scanner s = new Scanner(System.in);
    	System.out.println("\tHii !! How may I help you ?\n"
				+ "\t1. Create Ticket \n"
				+ "\t2. View Ticket \n"
				+ "\t3. Resolve Ticket \n"
				+ "\t4. Exit \n"
				+ "Please Enter choice: ");
		
		int choice = s.nextInt();
		s.nextLine();
        
		switch (choice) {
        	case 1:
		        this.createTicket();
		        System.out.println("\n\n");
		        break;
        	case 2:
				this.viewTickets();
				System.out.println("\n\n");
				break;
        	case 3:
				this.viewTickets();
				System.out.println("\n\n");
        		this.resolveTicket();
		        System.out.println("\n\n");
		        break;
		    default:
		    	System.out.println("\tInvalid option\n\n");
		}
    }

    public void createTicket() {
    	@SuppressWarnings("resource")
    	Scanner sc = new Scanner(System.in);
		System.out.println("\t\tPlease mention your user issue");
		String issueDescription = sc.nextLine();
        supportService.createTicket(userId, issueDescription);
        System.out.println("\t\tSupport ticket created successfully.");
        //sc.close();
    }

    public void viewTickets() {
    	System.out.println("\t\tHere is your ticket info.\n"
    			+ "\t\t--------------------------------------");
        List<SupportTicket> tickets = supportService.viewTickets(userId);
        if (tickets != null && !tickets.isEmpty()) {
            for (SupportTicket ticket : tickets) {
                System.out.println("\t\tTicket ID: " + ticket.getTicketId());
                System.out.println("\t\tIssue: " + ticket.getIssueDescription());
                System.out.println("\t\tStatus: " + ticket.getStatus());
                System.out.println("\t\tCreated Date: " + ticket.getCreatedDate());
                System.out.println("\t\tResolved Date: " + ticket.getResolvedDate());
                System.out.println("\t\t--------------------------------------");
            }
        } else {
            System.out.println("\t\tNo tickets found for the user.");
        }
    }

    public void resolveTicket() {
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	System.out.println("\t\tChoose appropriate ticket id to resolve:");
    	int ticketId = sc.nextInt();
        supportService.resolveTicket(ticketId);
        System.out.println("\t\tTicket ID " + ticketId + " has been resolved.");
        
    }
}
