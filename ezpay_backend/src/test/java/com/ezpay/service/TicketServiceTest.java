package com.ezpay.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ezpay.entity.Ticket;
import com.ezpay.repository.TicketRepository;

/**
 * Unit tests for the {@link TicketService} class.
 * This test class uses Mockito to mock the {@link TicketRepository} and test various service methods.
 * 
 * @author Vanshika Sood, Vaishnave JP
 * @since 5th September, 2024
 */
public class TicketServiceTest {

    /**
     * Injects mock dependencies into {@link TicketService}.
     */
    @InjectMocks
    private TicketService ticketService;

    /**
     * Mocked {@link TicketRepository} to avoid real database operations.
     */
    @Mock
    private TicketRepository ticketRepository;

    private Ticket ticket;
    private Ticket ticket2;

    /**
     * Initializes mock objects and sets up sample ticket data before each test.
     * 
     * @throws Exception if there is an issue during setup
     */
    @Before
    public void setUp() throws Exception {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Create a sample ticket
        ticket = new Ticket();
        ticket.setTicketId(1L);
        ticket.setUserId(101L);
        ticket.setIssueDescription("Issue description");
        ticket.setDateCreated(new Date());
        ticket.setStatus("PENDING");

        // Create another sample ticket
        ticket2 = new Ticket();
        ticket2.setTicketId(2L);
        ticket2.setUserId(101L);
        ticket2.setIssueDescription("Login Issue");
        ticket2.setDateCreated(new Date());
        ticket2.setStatus("PENDING");
    }

    /**
     * Tests the {@link TicketService#saveTicket(Ticket)} method.
     * Ensures that the ticket is successfully saved and that the repository's save method is called.
     */
    @Test
    public void testSaveTicket() {
        // Mock the repository save method to return the ticket
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Call the service method
        Ticket savedTicket = ticketService.saveTicket(ticket);

        // Verify that save was called and assert the result
        verify(ticketRepository, times(1)).save(ticket);
        assertNotNull(savedTicket);
        assertEquals(ticket.getTicketId(), savedTicket.getTicketId());
    }

    /**
     * Tests the {@link TicketService#saveTicket(Ticket)} method with a null input.
     * Expects an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSaveTicket_NullTicket() {
        // Call saveTicket with null, expecting an exception
        ticketService.saveTicket(null);
    }

    /**
     * Tests the {@link TicketService#getTicket(Long)} method.
     * Verifies that a ticket is fetched correctly from the repository.
     * 
     * @param ticketId the ID of the ticket to retrieve
     */
    @Test
    public void testGetTicket() {
        // Mock the repository findById method to return the ticket
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Call the service method
        Ticket foundTicket = ticketService.getTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        assertNotNull(foundTicket);
        assertEquals(ticket.getTicketId(), foundTicket.getTicketId());
    }

    /**
     * Tests the {@link TicketService#getTicket(Long)} method when the ticket is not found.
     * Verifies that the result is null when the ticket is not present in the repository.
     * 
     * @param ticketId the ID of the ticket to retrieve
     */
    @Test
    public void testGetTicket_NotFound() {
        // Mock the repository findById method to return an empty optional
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Ticket foundTicket = ticketService.getTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        assertNull(foundTicket);
    }

    /**
     * Tests the {@link TicketService#getAllTickets(Long)} method.
     * Ensures that all tickets for a user are fetched from the repository.
     * 
     * @param userId the ID of the user whose tickets are being fetched
     * @return a list of tickets belonging to the user
     */
    @Test
    public void testGetAllTickets() {
        // Mock the repository to return the list of tickets for the user
        List<Ticket> tickets = Arrays.asList(ticket, ticket2);
        when(ticketRepository.findAllByUserId(101L)).thenReturn(tickets);

        // Call the service method
        List<Ticket> userTickets = ticketService.getAllTickets(101L);

        // Verify and assert
        verify(ticketRepository, times(1)).findAllByUserId(101L);
        assertNotNull(userTickets);
        assertEquals(2, userTickets.size());

        // Check the details of the first and second tickets
        Ticket firstTicket = userTickets.get(0);
        assertEquals(Long.valueOf(1L), firstTicket.getTicketId());
        assertEquals("Issue description", firstTicket.getIssueDescription());
        assertEquals("PENDING", firstTicket.getStatus());

        Ticket secondTicket = userTickets.get(1);
        assertEquals(Long.valueOf(2L), secondTicket.getTicketId());
        assertEquals("Login Issue", secondTicket.getIssueDescription());
        assertEquals("PENDING", secondTicket.getStatus());
    }

    /**
     * Tests the {@link TicketService#deleteTicket(Long)} method.
     * Verifies that the ticket is deleted from the repository and the result is true.
     * 
     * @param ticketId the ID of the ticket to be deleted
     * @return true if the deletion is successful, false otherwise
     */
    @Test
    public void testDeleteTicket() {
        // Call the service method
        Boolean result = ticketService.deleteTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).deleteById(1L);
        assertTrue(result);
    }

    /**
     * Tests the {@link TicketService#deleteTicket(Long)} method when an exception occurs during deletion.
     * Verifies that the result is false in case of a failure.
     * 
     * @param ticketId the ID of the ticket to be deleted
     * @return false if the deletion fails
     */
    @Test
    public void testDeleteTicket_Failure() {
        // Simulate an exception during deletion
        doThrow(new RuntimeException()).when(ticketRepository).deleteById(1L);

        // Call the service method
        Boolean result = ticketService.deleteTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).deleteById(1L);
        assertFalse(result);
    }

    /**
     * Tests the {@link TicketService#resolveTicket(Long)} method.
     * Verifies that a ticket is marked as resolved, the status is updated to "RESOLVED",
     * and the resolution date is set.
     * 
     * @param ticketId the ID of the ticket to resolve
     * @return the resolved ticket with updated status and resolution date
     */
    @Test
    public void testResolveTicket() {
        // Mock the repository findById and save methods
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Call the service method
        Ticket resolvedTicket = ticketService.resolveTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        verify(ticketRepository, times(1)).save(ticket);
        assertEquals("RESOLVED", resolvedTicket.getStatus());
        assertNotNull(resolvedTicket.getDateResolved());
    }

    /**
     * Tests the {@link TicketService#unresolveTicket(Long)} method.
     * Verifies that a resolved ticket is marked as open again, the status is updated to "OPEN",
     * and the resolution date is cleared (set to null).
     * 
     * @param ticketId the ID of the ticket to unresolve
     * @return the unresolved ticket with updated status and cleared resolution date
     */
    @Test
    public void testUnresolveTicket() {
        // Set the initial state of the ticket to resolved
        ticket.setStatus("RESOLVED");
        ticket.setDateResolved(new Date());

        // Mock the repository findById and save methods
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Call the service method
        Ticket unresolvedTicket = ticketService.unresolveTicket(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        verify(ticketRepository, times(1)).save(ticket);
        assertEquals("PENDING", unresolvedTicket.getStatus());
        assertNull(unresolvedTicket.getDateResolved());
    }

}
