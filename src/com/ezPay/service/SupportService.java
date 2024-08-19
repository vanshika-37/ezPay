package com.ezPay.service;

import java.util.Date;
import java.util.List;

import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;

/**
 * Service class for managing support tickets, including creation, viewing, and resolution.
 * This class acts as an intermediary between the controller and the data access layer (DAO).
 */
public class SupportService {
	private SupportDAO supportDAO;

	/**
	 * Default constructor initializing the SupportDAO instance.
	 * This allows the service layer to interact with the data layer.
	 */
	public SupportService() {
		this.supportDAO = new SupportDAO();
	}

	/**
	 * Retrieves the current SupportDAO instance.
	 * This can be useful for cases where the DAO needs to be accessed or replaced.
	 *
	 * @return the current instance of SupportDAO
	 */
	public SupportDAO getSupportDAO() {
		return supportDAO;
	}

	/**
	 * Sets a custom SupportDAO instance.
	 * This method allows for dependency injection or replacement of the DAO instance.
	 *
	 * @param supportDAO the new SupportDAO instance to be used by this service
	 */
	public void setSupportDAO(SupportDAO supportDAO) {
		this.supportDAO = supportDAO;
	}

	/**
	 * Creates a new support ticket with the provided user ID and issue description.
	 * The ticket is initialized with a status of "OPEN" and the current timestamp.
	 *
	 * @param userId the ID of the user creating the ticket
	 * @param issueDescription a description of the issue to be reported
	 */
	public void createTicket(int userId, String issueDescription) {
		try {
			SupportTicket ticket = new SupportTicket(userId, issueDescription, "OPEN", new Date());
			supportDAO.createSupportTicket(ticket);
		} catch (Exception e) {
			// Logs the exception to the console for debugging purposes
			// In a production environment, consider logging this to a file or monitoring system
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the list of support tickets associated with the specified user ID.
	 * If an exception occurs, it logs the error and returns null.
	 *
	 * @param userId the ID of the user whose tickets are to be retrieved
	 * @return a list of SupportTicket objects, or null if an error occurs
	 */
	public List<SupportTicket> viewTickets(int userId) {
		try {
			return supportDAO.getTicketsByUserId(userId);
		} catch (Exception e) {
			// Logs the exception and returns null to indicate a failure in retrieving the tickets
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Resolves the support ticket with the given ticket ID by updating its status to "RESOLVED".
	 * If an exception occurs, it logs the error.
	 *
	 * @param ticketId the ID of the ticket to be resolved
	 */
	public void resolveTicket(int ticketId) {
		try {
			supportDAO.updateTicketStatus(ticketId, "RESOLVED");
		} catch (Exception e) {
			// Logs the exception to the console for debugging purposes
			// In a production environment, consider logging this to a file or monitoring system
			e.printStackTrace();
		}
	}
}
