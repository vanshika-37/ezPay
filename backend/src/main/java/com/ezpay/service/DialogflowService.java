package com.ezpay.service;

import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.gax.rpc.ApiException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Service
public class DialogflowService {

    private SessionsClient sessionsClient;
    private SessionName sessionName;
    String status;
    public DialogflowService() {
        try {
			// Load credentials from the JSON key file
			GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\Subhi\\Desktop\\NW\\Project\\ezPay\\backend\\src\\main\\resources\\ezpay-9ubu-ebd8de1f4887.json"));

			// Create SessionsSettings with credentials
			SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
			        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
			        .build();

			// Initialize the Dialogflow client
			this.sessionsClient = SessionsClient.create(sessionsSettings);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("You do not have valid Google API credentials!");
			status = "You do not have valid Google API credentials!";
			//if(this.sessionsClient == null) this.sessionsClient = null;
		}
    }
    
    public SessionName getSessionName() {
    	return this.sessionName;
    }
    
    public void setSessionName(String sessionId) {
    	// Create a session name with the provided project ID and session ID
    	if(this.sessionName == null)
    		this.sessionName = SessionName.of("ezpay-9ubu", sessionId);
    }

    public String detectIntentTexts(String text, String languageCode) throws ApiException {
        

        QueryResult queryResult;
		try {
			// Create text input
			TextInput textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode).build();
			QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

			// Build the request
			DetectIntentRequest request = DetectIntentRequest.newBuilder()
			        .setSession(sessionName.toString())
			        .setQueryInput(queryInput)
			        .build();

			// Send request and get response
			DetectIntentResponse response = sessionsClient.detectIntent(request);
			queryResult = response.getQueryResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return status + " This is a dummy bot response";
		}

        // Return the response text
        return queryResult.getFulfillmentText();
    }
}
