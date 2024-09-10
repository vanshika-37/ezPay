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




@Entity
@Table(name="support_ticket")
@Access(AccessType.FIELD)
public class Ticket {
	
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ticket_seq")
	 @SequenceGenerator(name = "ticket_seq", sequenceName = "TICKET_SEQ", allocationSize = 1)
	private Long ticketId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="issue_description")
	private String issueDescription;
	
	@Column(name="date_created")
	private Date dateCreated = new Date();
	
	@Column(name="date_resolved", nullable= true)
	private Date dateResolved ;
 
	@Column(name ="status")
	private String status = "OPEN";
	
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
