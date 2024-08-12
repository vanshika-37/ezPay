package com.ezPay.model;

import java.util.Date;

public class SupportTicket {
    private int ticketId;
    private int userId;
    private String issueDescription;
    private String status;
    private Date createdDate;
    private Date resolvedDate;

    public SupportTicket(int ticketId, int userId, String issueDescription, String status, Date createdDate, Date resolvedDate) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.issueDescription = issueDescription;
        this.status = status;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
    }

    public SupportTicket(int userId, String issueDescription, String status, Date createdDate) {
        this.userId = userId;
        this.issueDescription = issueDescription;
        this.status = status;
        this.createdDate = createdDate;
    }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getResolvedDate() { return resolvedDate; }
    public void setResolvedDate(Date resolvedDate) { this.resolvedDate = resolvedDate; }
}
