package com.ezPay.controller;

import java.util.List;
import java.util.Scanner;

import com.ezPay.model.SupportTicket;
import com.ezPay.service.SupportService;

public class SupportController {
    private SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    public void createTicket() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Please mention your user ID");
		int userId = sc.nextInt(); 
		sc.nextLine();
		System.out.println("Please mention your user issue");
		String issueDescription = sc.nextLine();
		sc.close();
        supportService.createTicket(userId, issueDescription);
        System.out.println("Support ticket created successfully.");
    }

    public void viewTickets(int userId) {
        List<SupportTicket> tickets = supportService.viewTickets(userId);
        if (tickets != null && !tickets.isEmpty()) {
            for (SupportTicket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Issue: " + ticket.getIssueDescription());
                System.out.println("Status: " + ticket.getStatus());
                System.out.println("Created Date: " + ticket.getCreatedDate());
                System.out.println("Resolved Date: " + ticket.getResolvedDate());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("No tickets found for the user.");
        }
    }

    public void resolveTicket(int ticketId) {
        supportService.resolveTicket(ticketId);
        System.out.println("Ticket ID " + ticketId + " has been resolved.");
    }
}
