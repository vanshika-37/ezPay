package com.ezpay.service;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.cloud.dialogflow.v2.*;
import com.google.api.gax.rpc.ApiException;

/**
 * Unit tests for the {@DialogflowService} and {@ChatService} classes.
 * This test class uses Mockito to mock external dependencies and test various service methods.
 * 
 * @author Subhashree M
 * @since 9th September, 2024
 */
public class DialogflowServiceTest {

    @InjectMocks
    private DialogflowService dialogflowService;

    @Mock
    private SessionsClient sessionsClient;

    @Mock
    private SessionName sessionName;

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
        dialogflowService = new DialogflowService();
        dialogflowService.setSessionName("test-session");
    }

    @Test
    public void testDetectIntentTexts() throws ApiException {
        // Mock a query result
        QueryResult mockQueryResult = mock(QueryResult.class);
        //when(mockQueryResult.getFulfillmentText()).thenReturn("Greetings! How can I assist?");

        // Mock the DetectIntentResponse
        DetectIntentResponse mockResponse = mock(DetectIntentResponse.class);
        when(mockResponse.getQueryResult()).thenReturn(mockQueryResult);

        // Mock the SessionsClient detectIntent method
        when(sessionsClient.detectIntent(any(DetectIntentRequest.class))).thenReturn(mockResponse);

     // Set up a list of possible valid responses
        List<String> validResponses = Arrays.asList(
            "Hi! How are you doing?",
            "Hello! How can I help you?",
            "Good day! What can I do for you today?",
            "Greetings! How can I assist?"
        );

        // Loop through each possible response and verify
        for (String expectedResponse : validResponses) {
            when(mockQueryResult.getFulfillmentText()).thenReturn(expectedResponse);

            // Test the detectIntentTexts method
            String responseText = dialogflowService.detectIntentTexts("Hello", "en");

            // Verify that the response is one of the valid responses
            assertNotNull(responseText);
            assertTrue(validResponses.contains(responseText));
        }
    }
}
