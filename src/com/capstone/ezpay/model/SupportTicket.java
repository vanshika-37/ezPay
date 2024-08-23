package com.capstone.ezpay.model;

import java.util.Date;

/**
 * Represents a support ticket with its details.
 * 
 * Each support ticket is associated with a user and contains information such
 * as the issue description, status, and dates of creation and resolution.
 * 
 * 
 * @author vanshika
 * @since 2024-08-19
 */
public class SupportTicket {
	private int ticketId; // Unique identifier for the ticket
	private int userId; // ID of the user who created the ticket
	private String issueDescription; // Description of the issue reported
	private String status; // Current status of the ticket (e.g., OPEN, RESOLVED)
	private Date createdDate; // Date when the ticket was created
	private Date resolvedDate; // Date when the ticket was resolved

	/**
	 * Constructs a SupportTicket with all details.
	 * 
	 * @param ticketId         Unique identifier for the ticket.
	 * @param userId           ID of the user who created the ticket.
	 * @param issueDescription Description of the issue reported.
	 * @param status           Current status of the ticket (e.g., OPEN, RESOLVED).
	 * @param createdDate      Date when the ticket was created.
	 * @param resolvedDate     Date when the ticket was resolved.
	 */
	public SupportTicket(int ticketId, int userId, String issueDescription, String status, Date createdDate,
			Date resolvedDate) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.issueDescription = issueDescription;
		this.status = status;
		this.createdDate = createdDate;
		this.resolvedDate = resolvedDate;
	}

	/**
	 * Constructs a SupportTicket without a resolved date.
	 * 
	 * @param userId           ID of the user who created the ticket.
	 * @param issueDescription Description of the issue reported.
	 * @param status           Current status of the ticket (e.g., OPEN, RESOLVED).
	 * @param createdDate      Date when the ticket was created.
	 */
	public SupportTicket(int userId, String issueDescription, String status, Date createdDate) {
		this.userId = userId;
		this.issueDescription = issueDescription;
		this.status = status;
		this.createdDate = createdDate;
	}

	/**
	 * Gets the unique identifier for the ticket.
	 * 
	 * @return The ticket ID.
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * Sets the unique identifier for the ticket.
	 * 
	 * @param ticketId The ticket ID to set.
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * Gets the ID of the user who created the ticket.
	 * 
	 * @return The user ID.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the ID of the user who created the ticket.
	 * 
	 * @param userId The user ID to set.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the description of the issue reported.
	 * 
	 * @return The issue description.
	 */
	public String getIssueDescription() {
		return issueDescription;
	}

	/**
	 * Sets the description of the issue reported.
	 * 
	 * @param issueDescription The issue description to set.
	 */
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	/**
	 * Gets the current status of the ticket.
	 * 
	 * @return The status of the ticket.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the current status of the ticket.
	 * 
	 * @param status The status to set (e.g., OPEN, RESOLVED).
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the date when the ticket was created.
	 * 
	 * @return The creation date of the ticket.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the date when the ticket was created.
	 * 
	 * @param createdDate The creation date to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the date when the ticket was resolved.
	 * 
	 * @return The resolved date of the ticket, or null if unresolved.
	 */
	public Date getResolvedDate() {
		return resolvedDate;
	}

	/**
	 * Sets the date when the ticket was resolved.
	 * 
	 * @param resolvedDate The resolved date to set.
	 */
	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
}
