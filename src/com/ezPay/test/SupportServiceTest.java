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
        try {
            List<SupportTicket> ticketsBefore = mockSupportDAO.getTicketsByUserId(1);
            supportService.createTicket(1, "Test issue");
            List<SupportTicket> ticketsAfter = mockSupportDAO.getTicketsByUserId(1);
            assertEquals(ticketsBefore.size() + 1, ticketsAfter.size());
            assertEquals("Test issue", ticketsAfter.get(ticketsAfter.size() - 1).getIssueDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testViewTickets() {
        try {
            List<SupportTicket> tickets = supportService.viewTickets(1);
            assertEquals(2, tickets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testResolveTicket() {
        try {
            supportService.resolveTicket(1);
            List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(1);
            assertEquals("RESOLVED", tickets.get(0).getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNoTicketsForUser() {
        try {
            List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(99);
            assertEquals(0, tickets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
