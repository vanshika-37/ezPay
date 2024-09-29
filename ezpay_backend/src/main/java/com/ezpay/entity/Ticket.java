package com.ezpay.entity;

import java.util.Date;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.*;


/**
 * Entity class representing the support_ticket table in the database.
 * This class maps to the "support_ticket" table with fields like ticketId, userId, issueDescription, dateCreated, dateResolved, and status.
 * 
 * @author Yash Gaikwad
 * @since: 04-09-2024
 */
@Entity
@Table(name="support_ticket")
public class Ticket {
	
    /**
     * The unique ID of the ticket. It is generated using a sequence.
     */
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ticket_seq")
	@SequenceGenerator(name = "ticket_seq", sequenceName = "TICKET_SEQ", allocationSize = 1)
	private Long ticketId;
	
    /**
     * The ID of the user who created the ticket.
     */
	@Column(name="user_id")
	private Long userId;
	
    /**
     * Description of the issue related to the ticket.
     */
	@Column(name="issue_description")
	private String issueDescription;
	
    /**
     * The date the ticket was created. Default value is set to the current date.
     */
	@Column(name="date_created")
	private Date dateCreated = new Date();
	
    /**
     * The date when the ticket was resolved. Can be null.
     */
	@Column(name="date_resolved", nullable= true)
	private Date dateResolved;
 
    /**
     * Status of the ticket. Default value is "PENDING".
     */
	@Column(name ="status")
	private String status = "PENDING";
	
	// Getters and setters
	
	public Long getTicketId() {
		return ticketId;
	}
     
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(Date dateResolved) {
		this.dateResolved = dateResolved;
	}
}
