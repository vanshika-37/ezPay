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
		// Mock the repository findAllByUserId method
		List<Ticket> tickets = Arrays.asList(ticket);
		when(ticketRepository.findAllByUserId(101L)).thenReturn(tickets);

		// Call the service method
		List<Ticket> userTickets = ticketService.getAllTickets(101L);

		// Verify and assert
		verify(ticketRepository, times(1)).findAllByUserId(101L);
		assertNotNull(userTickets);
		assertEquals(1, userTickets.size());
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
