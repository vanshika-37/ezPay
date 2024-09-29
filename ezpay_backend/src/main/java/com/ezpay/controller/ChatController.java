package com.ezpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.service.ChatService;
import com.ezpay.service.DialogflowService;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller handles chat-related API endpoints for the EzPay support system.
 * It manages the interactions between the user and the chatbot, using the Dialogflow service for bot responses.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@RestController
@RequestMapping("/api/support")  // Base path for all API endpoints within this controller
@CrossOrigin(
	    origins = {"http://localhost:3000"}, // Allowed origins for CORS
	    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, // Allowed HTTP methods
	    allowedHeaders = "*",  // Allow all headers
	    allowCredentials = "true"  // Allow cookies or session data
)
public class ChatController {
	
	@Autowired
    private ChatService chatService;  // Service for handling chat-related operations
	@Autowired
	private DialogflowService dialogflowService;  // Service for handling Dialogflow API interactions

    /**
     * This endpoint handles user messages sent to the chatbot for a specific support ticket.
     * It accepts a user message, sends it to the Dialogflow chatbot, and returns both the user and chatbot responses.
     * 
     * @param ticketId The ID of the support ticket to which the message belongs.
     * @param message The user's message.
     * @return A list containing the user message and the bot's response.
     * @throws Exception if an error occurs while processing the messages.
     */
    @PostMapping("/sendusermessage/{ticketId}")
    public List<ChatbotMessage> sendUserMessage(@PathVariable Long ticketId, @RequestBody ChatbotMessage message) throws Exception {
    	// Extract the user message from the ChatbotMessage object
    	String userRequest = message.getMessage();
    	
    	// Send the user's message to the chat service and save it in the database
    	ChatbotMessage userMessage = chatService.sendMessage(ticketId, userRequest, "User");
    	
    	// If a session is not already established for this ticket, set the session name in DialogflowService
    	if(dialogflowService.getSessionName() == null) {
    		dialogflowService.setSessionName(String.valueOf(ticketId));  // Use ticket ID as session name
    	}
    	
    	// Send the user's message to Dialogflow and get the bot's response
    	String botResponse = dialogflowService.detectIntentTexts(userRequest, "en");
    	
    	// Save the bot's response in the database
    	ChatbotMessage botMessage = chatService.sendMessage(ticketId, botResponse, "Chatbot");
    	
    	// Create a list to hold both the user and bot messages
    	List<ChatbotMessage> messages = new ArrayList<>();
        messages.add(userMessage);  // Add the user message
        messages.add(botMessage);  // Add the bot response

        return messages;  // Return the list of messages
    }

    /**
     * This endpoint retrieves the chat history for a specific support ticket.
     * 
     * @param ticketId The ID of the support ticket for which the chat history is requested.
     * @return A list of ChatbotMessage objects representing the chat history.
     * @throws Exception if an error occurs while retrieving the chat history.
     */
    @GetMapping("/getchat/{ticketId}")
    public List<ChatbotMessage> getChatHistory(@PathVariable Long ticketId) throws Exception {
        // Call the chat service to retrieve the chat history for the given ticket
        return chatService.getChatHistory(ticketId);
    }

}
