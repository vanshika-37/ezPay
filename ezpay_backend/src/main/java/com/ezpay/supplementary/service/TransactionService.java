package com.ezpay.supplementary.service;

import org.springframework.stereotype.Service;

/*
* @author Subhashree M
* @since 4th September, 2024
*/


@Service
public class TransactionService {

    public String getTransactionHistory(String userID) {
        // Logic to retrieve transaction history (use JPA to fetch from the database)
        return "Transaction history..."; // Replace with actual logic
    }
}
