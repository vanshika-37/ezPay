package com.ezPay.test;

import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;
import com.ezPay.service.SupportService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SupportServiceTest {

    private SupportService supportService;
    private SupportDAO mockSupportDAO;

    @Before
    public void setUp() {
        supportService = new SupportService();
        mockSupportDAO = supportService.getSupportDAO();
    }

    @Test
    public void testCreateTicket() {
    	List<SupportTicket> ticketsBefore = mockSupportDAO.getTicketsByUserId(1);
        supportService.createTicket(1, "Test issue");
        List<SupportTicket> ticketsAfter = mockSupportDAO.getTicketsByUserId(1);
        assertEquals(ticketsBefore.size() + 1, ticketsAfter.size()); // Check if a new ticket was added
        assertEquals("Test issue", ticketsAfter.get(ticketsAfter.size() - 1).getIssueDescription()); // Updated to check the last added ticket
   }

    @Test
    public void testViewTickets() {
        List<SupportTicket> tickets = supportService.viewTickets(1);
        assertEquals(2, tickets.size()); // Check for the number of tickets for user 1
    }

    @Test
    public void testResolveTicket() {
        supportService.resolveTicket(1);
        List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(1);
        assertEquals("RESOLVED", tickets.get(0).getStatus()); // Check if the ticket status is updated
    }
    
    @Test
    public void testNoTicketsForUser() {
        List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(99); // Assuming user 99 has no tickets
        assertEquals(0, tickets.size()); // Verify that no tickets are returned
    }
}
