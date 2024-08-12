package com.ezp.Ticketing.Controller;

import java.util.List;

import com.ezp.Ticketing.Model.SupportTicket;
import com.ezp.Ticketing.Service.SupportService;

public class SupportController {
    private SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    public void createTicket(int userId, String issueDescription) {
        supportService.createTicket(userId, issueDescription);
        System.out.println("Support ticket created successfully.");
    }

    public void viewTickets(int userId) {
        List<SupportTicket> tickets = supportService.viewTickets(userId);
        if (tickets != null && !tickets.isEmpty()) {
            for (SupportTicket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Issue: " + ticket.getIssueDescription());
                System.out.println("Status: " + ticket.getStatus());
                System.out.println("Created Date: " + ticket.getCreatedDate());
                System.out.println("Resolved Date: " + ticket.getResolvedDate());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("No tickets found for the user.");
        }
    }

    public void resolveTicket(int ticketId) {
        supportService.resolveTicket(ticketId);
        System.out.println("Ticket ID " + ticketId + " has been resolved.");
    }
}

