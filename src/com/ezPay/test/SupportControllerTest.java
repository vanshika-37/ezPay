package com.ezPay.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.ezPay.controller.SupportController;
import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;
import com.ezPay.service.SupportService;

import java.util.List;

public class SupportControllerTest {

    private SupportDAO supportDAO;
    private SupportService supportService;
    private SupportController supportController;

    @Before
    public void setUp() {
        supportDAO = new SupportDAO();
        supportService = new SupportService(supportDAO);
        supportController = new SupportController(supportService);
    }

    @Test
    public void testCreateTicket() {
        int initialSize = supportDAO.getTicketsByUserId(1).size();
        supportController.createTicket(1, "Issue with account settings.");

        List<SupportTicket> tickets = supportDAO.getTicketsByUserId(1);
        assertEquals(initialSize + 1, tickets.size());
        assertEquals("Issue with account settings.", tickets.get(tickets.size() - 1).getIssueDescription());
    }

    @Test
    public void testViewTickets() {
        supportController.createTicket(1, "Issue with payment gateway.");
        supportController.createTicket(1, "Unable to update profile.");

        List<SupportTicket> tickets = supportDAO.getTicketsByUserId(1);
        assertEquals(4, tickets.size());
        assertEquals("Issue with payment gateway.", tickets.get(2).getIssueDescription());
        assertEquals("Unable to update profile.", tickets.get(3).getIssueDescription());
    }

    @Test
    public void testResolveTicket() {
        supportController.createTicket(1, "Issue with app crashing.");
        SupportTicket ticket = supportDAO.getTicketsByUserId(1).get(0);

        supportController.resolveTicket(ticket.getTicketId());
        assertEquals("RESOLVED", ticket.getStatus());
        assertNotNull(ticket.getResolvedDate());
    }

    @Test
    public void testNoTicketsForUser() {
        List<SupportTicket> tickets = supportDAO.getTicketsByUserId(99); // Assuming user 99 has no tickets
        assertEquals(0, tickets.size());
    }
}

