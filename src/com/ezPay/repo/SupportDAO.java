package com.ezPay.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ezPay.model.SupportTicket;

/**
 * Manages the storage and retrieval of support tickets.
 */
public class SupportDAO {
	private List<SupportTicket> tickets = new ArrayList<>(); // List to store support tickets
	private int ticketCounter = 3; // Counter for generating unique ticket IDs

	// Initializes the SupportDAO with some sample support tickets.
	public SupportDAO() {
		// Adding some initial support tickets to the list
		tickets.add(new SupportTicket(1, 1, "Issue with UPI payment", "OPEN", new Date(), null));
		tickets.add(new SupportTicket(2, 2, "Bank transfer failed", "RESOLVED", new Date(), new Date()));
		tickets.add(new SupportTicket(3, 1, "Unable to login", "OPEN", new Date(), null));
	}

	// Creates a new support ticket and adds it to the list.
	public void createSupportTicket(SupportTicket ticket) {
		ticket.setTicketId(++ticketCounter); // Set a unique ticket ID
		tickets.add(ticket); // Add the new ticket to the list
	}

	// Retrieves all support tickets for a specific user.
	public List<SupportTicket> getTicketsByUserId(int userId) {
		List<SupportTicket> userTickets = new ArrayList<>(); // List to store tickets for the user
		for (SupportTicket ticket : tickets) {
			if (ticket.getUserId() == userId) {
				userTickets.add(ticket); // Add matching tickets to the list
			}
		}
		return userTickets; // Return the list of tickets
	}

	// Updates the status of a ticket with the specified ticket ID.
	public void updateTicketStatus(int ticketId, String status) {
		for (SupportTicket ticket : tickets) {
			if (ticket.getTicketId() == ticketId) {
				ticket.setStatus(status); // Update the status of the ticket
				if ("RESOLVED".equals(status)) {
					ticket.setResolvedDate(new Date()); // Set the resolved date if status is "RESOLVED"
				}
				break; // Exit loop once the ticket is found and updated
			}
		}
	}
}
