package com.ezpay.service;

import com.google.cloud.dialogflow.v2.*;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.gax.rpc.ApiException;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Service class for interacting with Dialogflow API to handle natural language processing for chatbot queries.
 * Provides functionality to detect intent from user input using Dialogflow and handle credentials for authentication.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Service  // Marks this class as a service component, enabling it to be managed by Spring's dependency injection.
public class DialogflowService {

	private static final Logger log = LogManager.getLogger(DialogflowService.class);
    private SessionsClient sessionsClient; // Client for Dialogflow session to interact with the API.
    private SessionName sessionName;       // Represents the Dialogflow session name (combination of project ID and session ID).
    String status;                         // Holds the status message in case of initialization issues.

    /**
     * Constructor initializes the Dialogflow client by loading the Google API credentials from a JSON key file.
     * If credentials are invalid or missing, the client remains null and a status message is set.
     */
    public DialogflowService() {
        try {
            // Load credentials from the provided JSON key file (path must be correct for authentication).
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\Subhi\\Desktop\\NW\\Gitlab\\ezpay_backend\\EZPay\\src\\main\\resources\\ezpay-9ubu-ebd8de1f4887.json"));

            // Configure SessionsSettings using the loaded credentials.
            SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

            // Initialize the Dialogflow SessionsClient with the settings.
            this.sessionsClient = SessionsClient.create(sessionsSettings);
            System.out.println(sessionsClient);
        } catch (Exception e) {
            // Handle potential exceptions such as file not found or authentication issues.
            e.printStackTrace();
           log.error("You do not have valid Google API credentials!");
            status = "You do not have valid Google API credentials!";
        }
    }

    /**
     * Returns the current session name for Dialogflow.
     * 
     * @return The Dialogflow `SessionName` object.
     */
    public SessionName getSessionName() {
        return this.sessionName;
    }

    /**
     * Sets the session name by combining the project ID and session ID.
     * 
     * @param sessionId The session ID for Dialogflow, representing a unique conversation.
     */
    public void setSessionName(String sessionId) {
        if (this.sessionName == null) {
            // Creates a session name using the project ID "ezpay-9ubu" and the given session ID.
            this.sessionName = SessionName.of("ezpay-9ubu", sessionId);
        }
    }

    /**
     * Sends user input (text) to Dialogflow for intent detection and processes the response.
     * If an error occurs, it returns a dummy response.
     * 
     * @param text The user input to be processed by Dialogflow.
     * @param languageCode The language code for the text (e.g., "en" for English).
     * @return The fulfillment text (response) from Dialogflow or an error message in case of failure.
     * @throws ApiException If any Dialogflow API-related error occurs.
     */
    public String detectIntentTexts(String text, String languageCode) throws ApiException {
        QueryResult queryResult;
        try {
            // Create text input for Dialogflow with the provided user text and language.
            TextInput textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode).build();
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            // Build the request for detecting intent.
            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                .setSession(sessionName.toString())  // Set the session name.
                .setQueryInput(queryInput)           // Set the query input.
                .build();

            // Send the request to Dialogflow and get the response.
            DetectIntentResponse response = sessionsClient.detectIntent(request);
            queryResult = response.getQueryResult(); // Extract the result from the response.
        } catch (Exception e) {
            // Handle exceptions and return a dummy response if something goes wrong.
            e.printStackTrace();
            return status + " This is a dummy bot response";
        }

        // Return the fulfillment text (Dialogflow's response to the user input).
        return queryResult.getFulfillmentText();
    }
}
