package com.ezPay.service;

import java.util.Date;
import java.util.List;

import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;

/**
 * Provides services for managing support tickets, including creating, viewing,
 * and resolving tickets.
 */
public class SupportService {
	private SupportDAO supportDAO; // Data access object for support tickets

	// Constructs a SupportService instance and initializes the SupportDAO.
	public SupportService() {
		this.supportDAO = new SupportDAO();
	}

	// Gets the SupportDAO instance used by this service.
	public SupportDAO getSupportDAO() {
		return supportDAO;
	}

	// Sets the SupportDAO instance used by this service.
	public void setSupportDAO(SupportDAO supportDAO) {
		this.supportDAO = supportDAO;
	}

	// Creates a new support ticket with the given user ID and issue description.
	public void createTicket(int userId, String issueDescription) {
		SupportTicket ticket = new SupportTicket(userId, issueDescription, "OPEN", new Date()); // Create new ticket
		supportDAO.createSupportTicket(ticket); // Save ticket using SupportDAO
	}

	// Retrieves the list of support tickets for a specific user.
	public List<SupportTicket> viewTickets(int userId) {
		return supportDAO.getTicketsByUserId(userId); // Fetch tickets from SupportDAO
	}

	// Resolves the ticket with the specified ticket ID by updating its status to
	// "RESOLVED".
	public void resolveTicket(int ticketId) {
		supportDAO.updateTicketStatus(ticketId, "RESOLVED"); // Update ticket status in SupportDAO
	}
}