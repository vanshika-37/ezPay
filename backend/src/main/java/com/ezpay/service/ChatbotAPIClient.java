package com.ezpay.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

/**
 * @author Subhashree M
 * @since: 04-09-2024
 */

@Service
public class ChatbotAPIClient {
	private static final String CHATBOT_API_URL = "https://chatbot-api-url"; // Replace with actual URL

    public String getChatbotResponse(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Build request body
        String requestBody = "{\"message\": \"" + userMessage + "\"}";
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Make request to chatbot API
        ResponseEntity<String> response = restTemplate.postForEntity(CHATBOT_API_URL, entity, String.class);

        // Return the chatbot's response
        return response.getBody();
    }
}
