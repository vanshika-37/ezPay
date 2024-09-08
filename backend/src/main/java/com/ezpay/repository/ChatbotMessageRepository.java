package com.ezpay.repository;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Subhashree M
 * @since: 04-09-2024
 */

@Repository
public interface ChatbotMessageRepository extends JpaRepository<ChatbotMessage, Long> {
    List<ChatbotMessage> findByTicket(Ticket ticket);
}
