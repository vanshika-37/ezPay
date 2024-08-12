package com.ezp.Ticketing.Service;

import java.util.Date;
import java.util.List;

import com.ezp.Ticketing.Model.SupportTicket;
import com.ezp.Ticketing.Repo.SupportDAO;

public class SupportService {
    private SupportDAO supportDAO;

    public SupportService(SupportDAO supportDAO) {
        this.supportDAO = supportDAO;
    }

    public void createTicket(int userId, String issueDescription) {
        SupportTicket ticket = new SupportTicket(userId, issueDescription, "OPEN", new Date());
        supportDAO.createSupportTicket(ticket);
    }

    public List<SupportTicket> viewTickets(int userId) {
        return supportDAO.getTicketsByUserId(userId);
    }

    public void resolveTicket(int ticketId) {
        supportDAO.updateTicketStatus(ticketId, "RESOLVED");
    }
}
