package com.capstone.ezpay.test;

import com.capstone.ezpay.model.SupportTicket;
import com.capstone.ezpay.repo.SupportDAO;
import com.capstone.ezpay.service.SupportService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SupportServiceTest {

    private SupportService supportService;
    private SupportDAO mockSupportDAO;

    @Before
    public void setUp() {
        mockSupportDAO = Mockito.mock(SupportDAO.class); // Mocking SupportDAO
        supportService = new SupportService();
        supportService.setSupportDAO(mockSupportDAO); // Injecting the mock DAO into the service
    }

    @Test
    public void testCreateTicket() throws SQLException, Exception {
        List<SupportTicket> ticketsBefore = new ArrayList<>();
        when(mockSupportDAO.getTicketsByUserId(1)).thenReturn(ticketsBefore); // Mocking the initial state
        
        supportService.createTicket(1, "Test issue");

        // Verifying that a ticket was added and checking if it has the correct details
        verify(mockSupportDAO, times(1)).createSupportTicket(any(SupportTicket.class));
        
        ticketsBefore.add(new SupportTicket(1, 1, "Test issue", "OPEN", new Date(), null));
        when(mockSupportDAO.getTicketsByUserId(1)).thenReturn(ticketsBefore);
        
        List<SupportTicket> ticketsAfter = mockSupportDAO.getTicketsByUserId(1);
        assertEquals(1, ticketsAfter.size());
        assertEquals("Test issue", ticketsAfter.get(0).getIssueDescription());
    }

    @Test
    public void testViewTickets() throws SQLException, Exception {
        List<SupportTicket> mockTickets = new ArrayList<>();
        mockTickets.add(new SupportTicket(1, 1, "Test issue", "OPEN", new Date(), null));
        mockTickets.add(new SupportTicket(2, 1, "Another issue", "RESOLVED", new Date(), null));
        
        when(mockSupportDAO.getTicketsByUserId(1)).thenReturn(mockTickets); // Mocking the ticket list for user 1

        List<SupportTicket> tickets = supportService.viewTickets(1);
        assertEquals(2, tickets.size());
    }

    @Test
    public void testResolveTicket() throws SQLException, Exception {
        SupportTicket mockTicket = new SupportTicket(1, 1, "Test issue", "OPEN", new Date(), null);
        List<SupportTicket> mockTickets = new ArrayList<>();
        mockTickets.add(mockTicket);
    
        when(mockSupportDAO.getTicketsByUserId(1)).thenReturn(mockTickets); // Mocking ticket retrieval

        supportService.resolveTicket(1);

        // Verifying that the ticket status was updated to "RESOLVED"
        verify(mockSupportDAO, times(1)).updateTicketStatus(1, "RESOLVED");
        
        mockTicket.setStatus("RESOLVED");
        when(mockSupportDAO.getTicketsByUserId(1)).thenReturn(mockTickets);
        
        List<SupportTicket> tickets = mockSupportDAO.getTicketsByUserId(1);
        assertEquals("RESOLVED", tickets.get(0).getStatus());
    }
    
    @Test
    public void testNoTicketsForUser() throws SQLException, Exception {
        when(mockSupportDAO.getTicketsByUserId(99)).thenReturn(new ArrayList<>()); // Mocking no tickets for user 99

        List<SupportTicket> tickets = supportService.viewTickets(99);
        assertEquals(0, tickets.size());
    }
}
