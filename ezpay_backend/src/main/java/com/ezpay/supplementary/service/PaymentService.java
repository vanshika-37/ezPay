package com.ezpay.supplementary.service;

import org.springframework.stereotype.Service;

/*
* @author Subhashree M
* @since 4th September, 2024
*/


@Service
public class PaymentService {

    public String getPaymentStatus(String paymentID) {
        // Logic to retrieve payment status (use JPA to fetch from the database)
        return "Completed"; // Replace with actual logic
    }
}

