package com.ezpay.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ezpay.service.TicketService;

/**
 * This is the REST controller class that handles HTTP requests for the Ticket entity.
 * It provides endpoints for creating, retrieving, updating, and deleting tickets, as well as other miscellaneous requests.
 * 
 * @author Yash Gaikwad
 * @since: 04-09-2024
 */
@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "http://localhost:3000") 
public class TicketController {
    
	private static final Logger log = LogManager.getLogger(TicketController.class);
	
	
    @Autowired
    private TicketService ticketService; // Injects the TicketService to handle business logic for ticket operations
    
    /**
     * Endpoint to create a new ticket.
     * 
     * @param ticket The ticket object received from the request body
     * @return The saved ticket object
     */
    @PostMapping("/create")
    public Ticket createTicket(@RequestBody Ticket ticket) {
    	log.info("New ticket created successfully");
        return ticketService.saveTicket(ticket);
    }
    
    /**
     * Endpoint to get all tickets by user ID.
     * 
     * @param userId The ID of the user whose tickets are being requested
     * @return A list of tickets belonging to the specified user
     */
    @GetMapping("/gettick/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getAllTickets(userId);
    }
    
    /**
     * Endpoint to get a single ticket by its ticket ID.
     * 
     * @param ticketId The ID of the ticket being requested
     * @return The ticket with the specified ID
     */
    @GetMapping("/gettick/ticket/{ticketId}")
    public Ticket getTicket(@PathVariable Long ticketId) {
        return ticketService.getTicket(ticketId);
    }
    
    /**
     * Endpoint to delete a ticket by its ticket ID.
     * 
     * @param ticketId The ID of the ticket to be deleted
     * @return A boolean value indicating whether the deletion was successful
     */
    @DeleteMapping("/delete/{ticketId}")
    public boolean deleteTicket(@PathVariable Long ticketId) {
    	log.info("ticket deleted successfully");
        return ticketService.deleteTicket(ticketId);
    }
    
    /**
     * Endpoint to resolve a ticket by its ticket ID.
     * This sets the ticket's `dateResolved` field to the current date.
     * 
     * @param ticketId The ID of the ticket to be resolved
     * @return The updated ticket object
     */
    @PutMapping("/resolveticket/{ticketId}")
    public Ticket resolveTicket(@PathVariable Long ticketId) {
        return ticketService.resolveTicket(ticketId);
    }
    
    /**
     * Endpoint to unresolve a ticket by its ticket ID.
     * This removes the `dateResolved` field value.
     * 
     * @param ticketId The ID of the ticket to be unresolved
     * @return The updated ticket object
     */
    @PutMapping("/unresolveticket/{ticketId}")
    public Ticket unresolveTicket(@PathVariable Long ticketId) {
        return ticketService.unresolveTicket(ticketId);
    }
    
    // Other endpoints for balance, payment, and profile
    
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
