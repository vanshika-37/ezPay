package com.ezPay.test;

import com.ezPay.model.SupportTicket;
import com.ezPay.repo.SupportDAO;
import com.ezPay.service.SupportService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SupportServiceTest {

    private SupportService supportService;  // Instance of SupportService to be tested
    private SupportDAO mockSupportDAO;  // Mock instance of SupportDAO for testing

    @Before
    public void setUp() {
        // Initialize SupportService and its DAO before each test
        supportService = new SupportService();
        mockSupportDAO = supportService.getSupportDAO();
    }

    @Test
    public void testCreateTicket() {
        // Test the creation of a new support ticket
        List<SupportTicket> ticketsBefore = mockSupportDAO.getTicketsByUserId(1);  // Get tickets for user 1 before creation
        supportService.createTicket(1, "Test issue");  // Create a new ticket for user 1
        List<SupportTicket> ticketsAfter = mockSupportDAO.getTicketsByUserId(1);  // Get tickets for user 1 after creation
        assertEquals(ticketsBefore.size() + 1, ticketsAfter.size());  // Check if a new ticket was added
        assertEquals("Test issue", ticketsAfter.get(ticketsAfter.size() - 1).getIssueDescription());  // Verify the issue description of the last added ticket
   }

    @Test
    public void testViewTickets() {
        // Test viewing tickets for a specific user
        List<SupportTicket> tickets = supportService.viewTickets(1);  // Get tickets for user 1
        assertEquals(2, tickets.size());  // Check if the number of tickets is as expected (assuming 2 for this test)
    }

    @Test
    public void testResolveTicket() {
        // Test resolving a ticket
        supportService.resolveTicket(1);  // Resolve ticket with ID 1
        List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(1);  // Get tickets for user 1
        assertEquals("RESOLVED", tickets.get(0).getStatus());  // Check if the status of the ticket has been updated to "RESOLVED"
    }
    
    @Test
    public void testNoTicketsForUser() {
        // Test for a user with no tickets
        List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(99);  // Get tickets for a user with no existing tickets (e.g., user 99)
        assertEquals(0, tickets.size());  // Verify that no tickets are returned
    }
}
