package com.ezpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.entity.Ticket;
import com.ezpay.repository.ChatbotMessageRepository;
import com.ezpay.repository.TicketRepository;

import java.util.Date;
import java.util.List;

/**
 * Service class for handling chat-related operations between users and the chatbot.
 * Provides functionality to send messages, save them to the database, and retrieve chat history.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Service  // Marks this class as a service, allowing it to be auto-detected by Spring for dependency injection.
public class ChatService {

    @Autowired  // Automatically injects the `ChatbotMessageRepository` dependency for message-related operations.
    private ChatbotMessageRepository chatbotMessageRepository;

    @Autowired  // Automatically injects the `TicketRepository` dependency for ticket-related operations.
    private TicketRepository ticketRepository;

    /**
     * Sends a message from a user or chatbot related to a specific support ticket.
     * The message is saved to the database, with details of the sender (user or chatbot) and the timestamp.
     * 
     * @param ticketId The ID of the support ticket.
     * @param message The content of the message being sent.
     * @param sender The sender of the message, either "User" or "Chatbot".
     * @return The saved `ChatbotMessage` representing the user or chatbot message.
     * @throws Exception If the ticket ID does not exist in the database.
     */
    public ChatbotMessage sendMessage(Long ticketId, String message, String sender) throws Exception {
        // Retrieve the support ticket from the database using the provided ticket ID.
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        // Create a new `ChatbotMessage` entity to store the user or chatbot message.
        ChatbotMessage userMessage = new ChatbotMessage();
        userMessage.setTicket(ticket);
        // Set sender type: 1 for "User" and 0 for "Chatbot".
        if(sender.equals("User")) userMessage.setSender(1);
        else if(sender.equals("Chatbot")) userMessage.setSender(0);
        
        // Set the message content and the timestamp.
        userMessage.setMessage(message);
        userMessage.setTimestamp(new Date());

        // Save the message to the database and immediately flush changes.
        chatbotMessageRepository.saveAndFlush(userMessage);

        // Return the saved user message.
        return userMessage;
    }

    /**
     * Retrieves the chat history for a given support ticket.
     * 
     * @param ticketId The ID of the support ticket.
     * @return A list of `ChatbotMessage` entities representing the chat history for the ticket.
     * @throws Exception If the ticket ID does not exist in the database.
     */
    public List<ChatbotMessage> getChatHistory(Long ticketId) throws Exception {
        // Retrieve the support ticket from the database using the provided ticket ID.
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        // Return the list of chatbot messages related to the ticket.
        return chatbotMessageRepository.findByTicket(ticket);
    }
}
