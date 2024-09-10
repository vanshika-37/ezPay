package com.ezpay.service;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        when(mockQueryResult.getFulfillmentText()).thenReturn("Greetings! How can I assist?");

        // Mock the DetectIntentResponse
        DetectIntentResponse mockResponse = mock(DetectIntentResponse.class);
        when(mockResponse.getQueryResult()).thenReturn(mockQueryResult);

        // Mock the SessionsClient detectIntent method
        when(sessionsClient.detectIntent(any(DetectIntentRequest.class))).thenReturn(mockResponse);

        // Test the detectIntentTexts method
        String responseText = dialogflowService.detectIntentTexts("Hello", "en");

        // Verify and assert
        assertNotNull(responseText);
        assertEquals("Greetings! How can I assist?", responseText);
    }
}
