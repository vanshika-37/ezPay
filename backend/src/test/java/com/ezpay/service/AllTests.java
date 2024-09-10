package com.ezpay.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ChatServiceTest.class, DialogflowServiceTest.class, TicketServiceTest.class })
public class AllTests {

}
