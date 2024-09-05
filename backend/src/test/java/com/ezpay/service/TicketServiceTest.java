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
import com.ezpay.service.TicketService;

public class TicketServiceTest {

	@InjectMocks
	private TicketService ticketService;

	@Mock
	private TicketRepository ticketRepository;

	private Ticket ticket;
	private Ticket ticket2;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Sample Ticket Object
		ticket = new Ticket();
		ticket.setTicketId(1L);
		ticket.setUserId(101L);
		ticket.setIssueDescription("Issue description");
		ticket.setDateCreated(new Date());
		ticket.setStatus("PENDING");
		
		ticket2 = new Ticket();
		ticket2.setTicketId(2L);
		ticket2.setUserId(101L);
		ticket2.setIssueDescription("Login Issue");
		ticket2.setDateCreated(new Date());
		ticket2.setStatus("PENDING");
		
	}

	@Test
	public void testSaveTicket() {
		// Mock the repository save method
		when(ticketRepository.save(ticket)).thenReturn(ticket);

		// Call the service method
		Ticket savedTicket = ticketService.saveTicket(ticket);

		// Verify and assert
		verify(ticketRepository, times(1)).save(ticket);
		assertNotNull(savedTicket);
		assertEquals(ticket.getTicketId(), savedTicket.getTicketId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveTicket_NullTicket() {
	    // Call the service method with null ticket
	    ticketService.saveTicket(null);
	}

	@Test
	public void testGetTicket() {
		// Mock the repository findById method
		when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

		// Call the service method
		Ticket foundTicket = ticketService.getTicket(1L);

		// Verify and assert
		verify(ticketRepository, times(1)).findById(1L);
		assertNotNull(foundTicket);
		assertEquals(ticket.getTicketId(), foundTicket.getTicketId());
	}

	@Test
	public void testGetTicket_NotFound() {
		// Mock the repository findById method to return empty
		when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

		// Call the service method
		Ticket foundTicket = ticketService.getTicket(1L);

		// Verify and assert
		verify(ticketRepository, times(1)).findById(1L);
		assertNull(foundTicket);
	}

	@Test
	public void testGetAllTickets() {
	    // Mock the repository to return only tickets with userId 101L
	    List<Ticket> tickets = Arrays.asList(ticket, ticket2);
	    when(ticketRepository.findAllByUserId(101L)).thenReturn(tickets);

	    // Call the service method
	    List<Ticket> userTickets = ticketService.getAllTickets(101L);

	    // Verify that the repository method was called once
	    verify(ticketRepository, times(1)).findAllByUserId(101L);

	    // Assertions for the list not being null and having the correct size
	    assertNotNull(userTickets);
	    assertEquals(2, userTickets.size());

	    // Check if the tickets returned have the correct userId
	    for (Ticket t : userTickets) {
	        assertEquals(Long.valueOf(101L), t.getUserId());
	    }

	    // Assert that the first ticket in the list is ticket and contains expected data
	    Ticket firstTicket = userTickets.get(0);
	    assertEquals(Long.valueOf(1L), firstTicket.getTicketId());
	    assertEquals("Issue description", firstTicket.getIssueDescription());
	    assertEquals("PENDING", firstTicket.getStatus());

	    // Assert that the second ticket in the list is ticket2 and contains expected data
	    Ticket secondTicket = userTickets.get(1);
	    assertEquals(Long.valueOf(2L), secondTicket.getTicketId());
	    assertEquals("Login Issue", secondTicket.getIssueDescription());
	    assertEquals("PENDING", secondTicket.getStatus());
	}


	@Test
	public void testDeleteTicket() {
		// Call the service method
		Boolean result = ticketService.deleteTicket(1L);

		// Verify
		verify(ticketRepository, times(1)).deleteById(1L);
		assertTrue(result);
	}

	@Test
	public void testDeleteTicket_Failure() {
		// Simulate exception
		doThrow(new RuntimeException()).when(ticketRepository).deleteById(1L);

		// Call the service method
		Boolean result = ticketService.deleteTicket(1L);

		// Verify
		verify(ticketRepository, times(1)).deleteById(1L);
		assertFalse(result);
	}

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

}
