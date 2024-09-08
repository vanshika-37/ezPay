package com.ezpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.service.ChatService;
import com.ezpay.service.DialogflowService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Subhashree M
 * @since: 04-09-2024
 */

@RestController
@RequestMapping("/api/support")
public class ChatController {
	
	@Autowired
    private ChatService chatService;
//	@Autowired
//	private DialogflowService dialogflowService;
	

    @PostMapping("/sendusermessage/{ticketId}")
    public ChatbotMessage sendUserMessage(@PathVariable Long ticketId, @RequestBody String message) throws Exception {
    	ChatbotMessage userMessage = chatService.sendMessage(ticketId, message);
    	
    	//String userMessage = message;
//        String botResponse = dialogflowService.detectIntentTexts(message, "en");
//		System.out.println(ResponseEntity.ok(Map.of("fulfillmentText", botResponse)));
    	return userMessage;
    }

    @GetMapping("/getchat/{ticketId}")
    public List<ChatbotMessage> getChatHistory(@PathVariable Long ticketId) throws Exception {
        return chatService.getChatHistory(ticketId);
    }

}
