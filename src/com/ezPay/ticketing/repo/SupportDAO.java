package com.ezPay.ticketing.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ezPay.ticketing.model.SupportTicket;

public class SupportDAO {
    private List<SupportTicket> tickets = new ArrayList<>();
    private int ticketCounter = 3;

    public SupportDAO() {
        tickets.add(new SupportTicket(1, 1, "Issue with UPI payment", "OPEN", new Date(), null));
        tickets.add(new SupportTicket(2, 2, "Bank transfer failed", "RESOLVED", new Date(), new Date()));
        tickets.add(new SupportTicket(3, 1, "Unable to login", "OPEN", new Date(), null));
    }

    public void createSupportTicket(SupportTicket ticket) {
        ticket.setTicketId(++ticketCounter);
        tickets.add(ticket);
    }

    public List<SupportTicket> getTicketsByUserId(int userId) {
        List<SupportTicket> userTickets = new ArrayList<>();
        for (SupportTicket ticket : tickets) {
            if (ticket.getUserId() == userId) {
                userTickets.add(ticket);
            }
        }
        return userTickets;
    }

    public void updateTicketStatus(int ticketId, String status) {
        for (SupportTicket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                ticket.setStatus(status);
                if ("RESOLVED".equals(status)) {
                    ticket.setResolvedDate(new Date());
                }
                break;
            }
        }
    }
}
