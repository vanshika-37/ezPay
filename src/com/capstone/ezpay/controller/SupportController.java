package com.capstone.ezpay.controller;

import java.util.List;
import java.util.Scanner;

import com.capstone.ezpay.model.SupportTicket;
import com.capstone.ezpay.service.SupportService;

/**
 * @author vanshika
 * @since 2024-08-19
 * 
 * Handles user support interactions, including ticket creation, viewing,
 * and resolution.
 */

public class SupportController {
	private SupportService supportService; // Service for handling support tickets
	private int userId; // ID of the user seeking support

	/**
	 * Initializes SupportController with a user ID.
	 * 
	 * @param userId The ID of the user for whom support is being managed.
	 */
	public SupportController(int userId) {
		supportService = new SupportService();
		this.userId = userId;
	}

	/**
	 * Displays the support menu and handles user input. This method loops until the
	 * user chooses to exit.
	 */
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

	/**
	 * Prompts the user to enter an issue description and creates a support ticket.
	 * This method encapsulates the logic of gathering the issue description and
	 * delegating ticket creation to the SupportService.
	 */
	public void createTicket() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tPlease mention your user issue");
		String issueDescription = sc.nextLine();
		supportService.createTicket(userId, issueDescription);
		System.out.println("\t\tSupport ticket created successfully.");
	}

	/**
	 * Displays all support tickets for the user. This method retrieves tickets from
	 * the SupportService and formats them for display.
	 */
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
			System.out.println("\t\tNo tickets found for the user."); // Handle No tickets case
		}
	}

	/**
	 * Prompts the user to enter a ticket ID and resolves the selected ticket. This
	 * method encapsulates the logic for ticket resolution, ensuring that the ticket
	 * ID is valid and the resolution process is handled correctly.
	 */
	public void resolveTicket() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tChoose appropriate ticket id to resolve:");
		int ticketId = sc.nextInt();
		supportService.resolveTicket(ticketId);
		System.out.println("\t\tTicket ID " + ticketId + " has been resolved.");
	}
}
