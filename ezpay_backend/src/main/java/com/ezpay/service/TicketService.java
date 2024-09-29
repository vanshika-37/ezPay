package com.ezpay.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpay.entity.Ticket;
import com.ezpay.repository.TicketRepository;

/**
 * Service class that provides business logic for managing tickets.
 * It communicates with the TicketRepository to perform database operations.
 * 
 * @author Yash Gaikwad
 * @since: 04-09-2024
 */
@Service
public class TicketService {
  
    // Injecting the TicketRepository for database interactions
	private static final Logger log = LogManager.getLogger(TicketService.class);
	@Autowired
	private TicketRepository ticketRepository;

	/**
	 * Constructor injection of TicketRepository.
	 * 
	 * @param ticketRepository The repository instance to interact with the database.
	 */
	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	/**
	 * Saves the ticket to the database.
	 * 
	 * @param ticket The ticket object to be saved.
	 * @return The saved Ticket object.
	 */
	public Ticket saveTicket(Ticket ticket) {
		if (ticket == null) {
			log.error("Ticket was found to be null.");
            throw new IllegalArgumentException("Ticket cannot be null");
        }
		return ticketRepository.save(ticket);
	}

	/**
	 * Retrieves a ticket by its ID.
	 * 
	 * @param ticketId The ID of the ticket to retrieve.
	 * @return The Ticket object if found, or null if not.
	 */
	public Ticket getTicket(Long ticketId) {
		try {
        	 Ticket ticket =  ticketRepository.findById(ticketId).get();
     		return ticket;
		} catch (Exception e) {
			log.info("Could not found ticket");
			return null;
		}
	}

	/**
	 * Retrieves all tickets for a specific user.
	 * 
	 * @param userId The ID of the user whose tickets are to be retrieved.
	 * @return A list of tickets associated with the given user ID.
	 */
	public List<Ticket> getAllTickets(Long userId){
		return ticketRepository.findAllByUserId(userId);
	}

	/**
	 * Deletes a ticket by its ID.
	 * 
	 * @param ticketId The ID of the ticket to be deleted.
	 * @return true if the ticket was successfully deleted, false otherwise.
	 */
	public Boolean deleteTicket(Long ticketId) {
		try {
			ticketRepository.deleteById(ticketId);
			return true;
		} catch (Exception e) {
			log.info("Could not delete ticket");
			return false;
		}
	}

	/**
	 * Marks a ticket as resolved by updating its resolved date and status.
	 * 
	 * @param ticketId The ID of the ticket to resolve.
	 * @return The updated Ticket object.
	 */
	public Ticket resolveTicket(Long ticketId) {
		Ticket ticket = this.getTicket(ticketId);
		ticket.setDateResolved(new Date());
		ticket.setStatus("RESOLVED");
		return ticketRepository.save(ticket);
	}

	/**
	 * Marks a ticket as unresolved by setting its resolved date to null and updating its status.
	 * 
	 * @param ticketId The ID of the ticket to unresolve.
	 * @return The updated Ticket object.
	 */
	public Ticket unresolveTicket(Long ticketId) {
		Ticket ticket = this.getTicket(ticketId);
		ticket.setDateResolved(null);
		ticket.setStatus("PENDING");
		return ticketRepository.save(ticket);
	}
}
