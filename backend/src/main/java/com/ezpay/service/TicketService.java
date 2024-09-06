package com.ezpay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpay.entity.Ticket;
import com.ezpay.repository.TicketRepository;

@Service
public class TicketService {
  
	@Autowired
	private TicketRepository ticketRepository;
	 
	
	public TicketService(TicketRepository ticketRepository) {
	
		this.ticketRepository = ticketRepository;
	}

	//Storing the ticket in the database
	public Ticket saveTicket(Ticket ticket) {
		if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
		return ticketRepository.save(ticket);
	}
	
	public Ticket getTicket(Long ticketId) {
         try {
        	 Ticket ticket =  ticketRepository.findById(ticketId).get();
     		return ticket;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	public List<Ticket> getAllTickets(Long userId){
		return ticketRepository.findAllByUserId(userId);
	}
	
	public Boolean deleteTicket(Long ticketId) {
		try {
			ticketRepository.deleteById(ticketId);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public Ticket resolveTicket(Long ticketId) {
		Ticket ticket = this.getTicket(ticketId);
		ticket.setDateResolved(new Date());
		ticket.setStatus("RESOLVED");
		return ticketRepository.save(ticket);
		
	}
	
	public Ticket unresolveTicket(Long ticketId) {
		Ticket ticket = this.getTicket(ticketId);
		ticket.setDateResolved(null);
		ticket.setStatus("PENDING");
		return ticketRepository.save(ticket);
		
	}
	
	
}
