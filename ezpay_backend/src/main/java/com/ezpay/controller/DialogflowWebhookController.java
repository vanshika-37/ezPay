package com.ezpay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezpay.service.EmailService;
import com.ezpay.supplementary.service.PaymentService;
import com.ezpay.supplementary.service.TransactionService;

/**
 * This controller handles webhook requests from Dialogflow.
 * It processes the incoming requests and triggers different actions
 * like checking payment status or sending an email.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */
@RestController
@RequestMapping("/api/dialogflow/webhook")
@CrossOrigin(
    origins = "https://dialogflow.cloud.google.com", // Allows requests from Dialogflow only
    methods = {RequestMethod.POST} // Only POST requests are allowed
)
public class DialogflowWebhookController {

    // Automatically injects the PaymentService for handling payments
    @Autowired
    private PaymentService paymentService;

    // Automatically injects the TransactionService for handling transactions
    @Autowired
    private TransactionService transactionService;
    
    // Automatically injects the EmailService for sending emails
    @Autowired
    private EmailService emailService; 

    /**
     * This method listens for POST requests from Dialogflow and handles them.
     * It reads the intent from the request and performs different actions
     * based on the intent.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> handleWebhook(@RequestBody Map<String, Object> request) {
        
        // Extract the intent name from the incoming request
        String intentName = ((Map<String, String>)((Map<String, Object>)request.get("queryResult")).get("intent")).get("displayName");
        
        // Extract parameters associated with the intent
        Map<String, Object> parameters = (Map<String, Object>)((Map<String, Object>)request.get("queryResult")).get("parameters");
        
        // Prepare a response map
        Map<String, String> response = new HashMap<>();

        // Handle different intents
        switch (intentName) {
            case "CheckPaymentStatus":
                // If the intent is to check payment status, get the payment ID and status
                String paymentID = parameters.get("paymentID").toString();
                String paymentStatus = paymentService.getPaymentStatus(paymentID);
                
                // Set the response message
                response.put("fulfillmentText", "The status of payment with ID " + paymentID + " is: " + paymentStatus);
                break;

            case "RequestTransactionHistory":
                // If the intent is to request transaction history, get the user ID and history
                String userID = parameters.get("UserID").toString();
                String transactionHistory = transactionService.getTransactionHistory(userID);
                
                // Set the response message
                response.put("fulfillmentText", "Transaction history for user ID " + userID + ": " + transactionHistory);
                break;

            case "EmailRequest-Yes":
                // If the intent is to send an email, send the email using EmailService
                String email = parameters.get("email").toString();
                emailService.sendEmail(email, "RE: Ezpay email support", "Hi customer\n \t This mail has been triggered as you have requested for support. Our customer support team will contact you shortly. \n\n Thank you for your patience!\n\n This is an automated response. Please do not reply.\n Regards,\n Ezpay team.");
                break;

            default:
                // If the intent is not recognized, send a default response
                response.put("fulfillmentText", "Sorry, I didn't understand that.");
                break;
        }

        // Return the response as a ResponseEntity
        return ResponseEntity.ok(response);

    }
}
