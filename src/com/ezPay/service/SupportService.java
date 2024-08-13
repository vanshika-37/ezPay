package com.ezPay.service;

import java.util.Date;
import java.util.List;

import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;

public class SupportService {
    private SupportDAO supportDAO;

    public SupportService() {
        this.supportDAO = new SupportDAO();
    }
    
    public SupportDAO getSupportDAO() {
		return supportDAO;
	}

	public void setSupportDAO(SupportDAO supportDAO) {
		this.supportDAO = supportDAO;
	}
	
    public void createTicket(int userId, String issueDescription) {  //creating ticket with user ID and issue Description
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
