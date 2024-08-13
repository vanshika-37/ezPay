package com.ezPay.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SupportServiceTest.class, UserInterfaceServiceTest.class })
public class AllTests {

}
