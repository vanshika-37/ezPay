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
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Service
public class ChatService {

    @Autowired
    private ChatbotMessageRepository chatbotMessageRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public ChatbotMessage sendMessage(Long ticketId, String message, String sender) throws Exception {
        // Get the support ticket
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        // Save user message
        ChatbotMessage userMessage = new ChatbotMessage();
        userMessage.setTicket(ticket);
        if(sender.equals("User")) userMessage.setSender(ChatbotMessage.Sender.User);
        else if(sender.equals("Chatbot")) userMessage.setSender(ChatbotMessage.Sender.Chatbot);
        userMessage.setMessage(message);
        userMessage.setTimestamp(new Date());
        chatbotMessageRepository.saveAndFlush(userMessage);
        //System.out.println(userMessage.getMessageId());
        //System.out.println("Message Sent\n______________________________---------------");

        // Send the message to the chatbot API and get a response
        //String chatbotResponse = chatbotAPIClient.getChatbotResponse(message);

        // Save chatbot response
//        ChatbotMessage botMessage = new ChatbotMessage();
//        botMessage.setTicket(ticket);
//        botMessage.setSender(ChatbotMessage.Sender.Chatbot);
//        botMessage.setMessage(chatbotResponse);
//        botMessage.setTimestamp(new Date());
//        //chatbotMessageRepository.saveAndFlush(botMessage);

        return userMessage;
    }

    public List<ChatbotMessage> getChatHistory(Long ticketId) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        return chatbotMessageRepository.findByTicket(ticket);
    }
}