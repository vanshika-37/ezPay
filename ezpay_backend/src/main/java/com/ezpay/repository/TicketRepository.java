package com.ezpay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ezpay.entity.Ticket;

/**
 * Repository interface for the Ticket entity. It extends CrudRepository to provide basic CRUD operations 
 * and also includes a custom method to find tickets by user ID.
 * 
 * The @Repository annotation indicates that this interface is a Spring repository.
 * 
 * @author Yash Gaikwad
 * @since: 04-09-2024
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    
    /**
     * Custom query method to find all tickets by the user ID.
     * Spring Data JPA automatically generates the query based on the method name.
     * 
     * @param userId The ID of the user.
     * @return A list of tickets that belong to the specified user.
     */
	public List<Ticket> findAllByUserId(Long userId);
}
