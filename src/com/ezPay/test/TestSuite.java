package com.ezPay.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ SupportServiceTest.class, UserInterfaceServiceTest.class })
public class TestSuite {

}
