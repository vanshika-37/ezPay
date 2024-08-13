package com.ezPay.model;

import java.util.Date;

/**
 * Represents a support ticket with its details.
 */
public class SupportTicket {
	private int ticketId; // Unique identifier for the ticket
	private int userId; // ID of the user who created the ticket
	private String issueDescription; // Description of the issue reported
	private String status; // Current status of the ticket (e.g., OPEN, RESOLVED)
	private Date createdDate; // Date when the ticket was created
	private Date resolvedDate; // Date when the ticket was resolved

	// Constructs a SupportTicket with all details.
	public SupportTicket(int ticketId, int userId, String issueDescription, String status, Date createdDate,
			Date resolvedDate) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.issueDescription = issueDescription;
		this.status = status;
		this.createdDate = createdDate;
		this.resolvedDate = resolvedDate;
	}

	// Constructs a SupportTicket without a resolved date.
	public SupportTicket(int userId, String issueDescription, String status, Date createdDate) {
		this.userId = userId;
		this.issueDescription = issueDescription;
		this.status = status;
		this.createdDate = createdDate;
	}

	// Getters and setters for the ticket attributes
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
}
