package com.ezPay.ticketing.controller;

import com.ezPay.ticketing.repo.SupportDAO;
import com.ezPay.ticketing.service.SupportService;

public class Main {
    public static void main(String[] args) {
        SupportDAO supportDAO = new SupportDAO();
        SupportService supportService = new SupportService(supportDAO);
        SupportController supportController = new SupportController(supportService);

        supportController.createTicket(1, "Issue with OTP verification.");
        supportController.viewTickets(1);
        supportController.resolveTicket(4);
    }
}
