package com.ezpay.repository;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing `ChatbotMessage` entities in the database.
 * Extends `JpaRepository` to provide CRUD operations and additional methods for custom queries.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Repository  // Marks this interface as a Spring Data repository.
public interface ChatbotMessageRepository extends JpaRepository<ChatbotMessage, Long> {
    
    /**
     * Custom query method to find all `ChatbotMessage` entities associated with a specific support `Ticket`.
     * 
     * @param ticket The `Ticket` entity to search messages for.
     * @return A list of `ChatbotMessage` entities linked to the provided `Ticket`.
     */
    List<ChatbotMessage> findByTicket(Ticket ticket);
}
