package com.ezpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezpay.entity.Ticket;
import com.ezpay.repository.TicketRepository;
import com.ezpay.service.TicketService;

@RestController
@RequestMapping("/api/support")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/create")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.saveTicket(ticket);
	}
	
	@GetMapping("/gettick/user/{userId}")
	public  List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
		return ticketService.getAllTickets(userId);
	}
	
	@GetMapping("/gettick/ticket/{ticketId}")
	public Ticket getTicket(@PathVariable Long ticketId) {
		return ticketService.getTicket(ticketId);
	}
	
	@DeleteMapping("/delete/{ticketId}")
	public boolean deleteTicket(@PathVariable Long ticketId) {
		return ticketService.deleteTicket(ticketId);
	}
	
	@PutMapping("/resolveticket/{ticketId}")
	public Ticket resolveTicket(@PathVariable Long ticketId) {
		return ticketService.resolveTicket(ticketId);
	}
	@GetMapping("/balance")
	public String getBalance() {
		return "Balance";
	}
	@GetMapping("/payment")
	public String getPayment() {
		return "Payment";
	}
	@GetMapping("/")
	public String getHome() {
		return "EzPay";
	}
	@GetMapping("/home")
    public String getHomeAlternate() {
        return "EzPay";
    }
	@GetMapping("/profile")
	public String getProfile() {
		return "Profile";
	}

	
	
	
	

}
