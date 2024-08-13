package com.ezPay.controller;

import java.util.List;
import java.util.Scanner;

import com.ezPay.model.SupportTicket;
import com.ezPay.service.SupportService;

/**
 * Handles user support interactions, including ticket creation, viewing, and
 * resolution.
 */
public class SupportController {
	private SupportService supportService; // Service for handling support tickets
	private int userId; // ID of the user seeking support

	// Initializes SupportController with a user ID.
	public SupportController(int userId) {
		supportService = new SupportService();
		this.userId = userId;
	}

	// Displays the support menu and handles user input.
	public void showSupportMenu() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("\tHii !! How may I help you ?\n" + "\t1. Create Ticket \n" + "\t2. View Ticket \n"
				+ "\t3. Resolve Ticket \n" + "\t4. Exit \n" + "Please Enter choice: ");

		int choice = s.nextInt();
		s.nextLine();

		switch (choice) {
		case 1:
			this.createTicket(); // Create a new support ticket
			System.out.println("\n\n");
			break;
		case 2:
			this.viewTickets(); // View existing support tickets
			System.out.println("\n\n");
			break;
		case 3:
			this.viewTickets(); // View tickets before resolving
			System.out.println("\n\n");
			this.resolveTicket(); // Resolve a selected ticket
			System.out.println("\n\n");
			break;
		default:
			System.out.println("\tInvalid option\n\n"); // Handle invalid choices
		}
	}

	// Prompts user to enter issue description and creates a support ticket.
	public void createTicket() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tPlease mention your user issue");
		String issueDescription = sc.nextLine();
		supportService.createTicket(userId, issueDescription);
		System.out.println("\t\tSupport ticket created successfully.");
		// sc.close(); // No need to close Scanner, it's managed elsewhere
	}

	// Displays all support tickets for the user.
	public void viewTickets() {
		System.out.println("\t\tHere is your ticket info.\n" + "\t\t--------------------------------------");
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
			System.out.println("\t\tNo tickets found for the user."); // No tickets case
		}
	}

	// Prompts user to enter a ticket ID and resolves the selected ticket.
	public void resolveTicket() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tChoose appropriate ticket id to resolve:");
		int ticketId = sc.nextInt();
		supportService.resolveTicket(ticketId);
		System.out.println("\t\tTicket ID " + ticketId + " has been resolved.");
	}
}
