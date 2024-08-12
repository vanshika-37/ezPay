package com.ezp.Ticketing.Controller;

import com.ezp.Ticketing.Repo.SupportDAO;
import com.ezp.Ticketing.Service.SupportService;

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
