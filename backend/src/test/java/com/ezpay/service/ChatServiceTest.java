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

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.entity.Ticket;
import com.ezpay.repository.ChatbotMessageRepository;
import com.ezpay.repository.TicketRepository;

/**
 * Unit tests for the {@ChatService} class.
 * This test class uses Mockito to mock the {@TicketRepository} and {@ChatbotMessageRepository}.
 * 
 * @author Subhashree M
 * @since 9th September, 2024
 */
public class ChatServiceTest {

    @InjectMocks
    private ChatService chatService;

    @Mock
    private ChatbotMessageRepository chatbotMessageRepository;

    @Mock
    private TicketRepository ticketRepository;

    private Ticket ticket;
    private ChatbotMessage userMessage;

    @Before
    public void setUp() throws Exception {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Create a sample ticket
        ticket = new Ticket();
        ticket.setTicketId(1L);
        ticket.setUserId(101L);
        ticket.setIssueDescription("Test issue");
        ticket.setDateCreated(new Date());
        ticket.setStatus("PENDING");

        // Create a sample user message
        userMessage = new ChatbotMessage();
        userMessage.setTicket(ticket);
        userMessage.setSender(ChatbotMessage.Sender.User);
        userMessage.setMessage("Test user message");
        userMessage.setTimestamp(new Date());
    }

    @Test
    public void testSendMessage() throws Exception {
        // Mock repository findById to return the ticket
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Mock repository saveAndFlush to return the user message
        when(chatbotMessageRepository.saveAndFlush(userMessage)).thenReturn(userMessage);

        // Call the service method
        ChatbotMessage resultMessage = chatService.sendMessage(1L, "Test user message", "User");

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        verify(chatbotMessageRepository).saveAndFlush(argThat(message -> 
	        message.getMessage().equals("Test user message") &&
	        message.getSender().equals(ChatbotMessage.Sender.User)
        ));


        assertNotNull(resultMessage);
        assertEquals("Test user message", resultMessage.getMessage());
    }

    @Test
    public void testGetChatHistory() throws Exception {
        // Mock repository findByTicket to return the list of messages
        List<ChatbotMessage> messages = Arrays.asList(userMessage);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(chatbotMessageRepository.findByTicket(ticket)).thenReturn(messages);

        // Call the service method
        List<ChatbotMessage> chatHistory = chatService.getChatHistory(1L);

        // Verify and assert
        verify(ticketRepository, times(1)).findById(1L);
        verify(chatbotMessageRepository, times(1)).findByTicket(ticket);
        assertNotNull(chatHistory);
        assertEquals(1, chatHistory.size());
        assertEquals("Test user message", chatHistory.get(0).getMessage());
    }

    @Test(expected = Exception.class)
    public void testSendMessage_TicketNotFound() throws Exception {
        // Mock repository findById to throw an exception
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method, expecting an exception
        chatService.sendMessage(1L, "Test user message", "User");
    }
}
