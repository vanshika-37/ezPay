package com.ezpay.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezpay.entity.ChatbotMessage;
import com.ezpay.service.ChatService;
import com.ezpay.service.DialogflowService;
import com.fasterxml.jackson.core.JsonParser;
import com.google.api.client.json.Json;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.json.stream.JsonParserFactory;

/**
 * @author Subhashree M
 * @since 4th September, 2024
 */

@RestController
@RequestMapping("/api/support")
@CrossOrigin(
	    origins = {"http://localhost:3000", "https://dialogflow.cloud.google.com"}, 
	    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, 
	    allowedHeaders = "*",
	    allowCredentials = "true"  // Enable this if you are sending cookies or session data
	)
public class ChatController {
	
	@Autowired
    private ChatService chatService;
	@Autowired
	private DialogflowService dialogflowService;
	

    @PostMapping("/sendusermessage/{ticketId}")
    public List<ChatbotMessage> sendUserMessage(@PathVariable Long ticketId, @RequestBody ChatbotMessage message) throws Exception {
    	//System.out.println(Object.type(message));
    	//JsonParser jsonParser = new JsonParser();
    	String userRequest = message.getMessage();//jsonParser.parse(message).getAsJsonObject()).get("message").getAsString();
    	//JsonParserFactory factory = Json.createParserFactory();  
    	//JsonParser parser1 = factory.createParser(...);
    	//System.out.println(message);
    	//System.out.println(userRequest);
//    	JSONParser parser = new JSONParser();
//    	JSONObject json = (JSONObject) parser.parse(stringToParse)
    			
    	ChatbotMessage userMessage = chatService.sendMessage(ticketId, userRequest, "User");
    	
    	if(dialogflowService.getSessionName() == null) {
    		dialogflowService.setSessionName(String.valueOf(ticketId));
    	}
    	
    	String botResponse = dialogflowService.detectIntentTexts(userRequest, "en");
    	
    	ChatbotMessage botMessage = chatService.sendMessage(ticketId, botResponse, "Chatbot");
		//System.out.println(botResponse);
    	
    	List<ChatbotMessage> messages = new ArrayList<>();
        messages.add(userMessage);
        messages.add(botMessage);

        return messages;
    }

    @GetMapping("/getchat/{ticketId}")
    public List<ChatbotMessage> getChatHistory(@PathVariable Long ticketId) throws Exception {
        return chatService.getChatHistory(ticketId);
    }

}
