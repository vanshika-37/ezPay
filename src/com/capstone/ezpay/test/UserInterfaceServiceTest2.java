/**
 * 
 */
package com.capstone.ezpay.test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capstone.ezpay.model.UserInterface;
import com.capstone.ezpay.repo.DeviceDAO;
import com.capstone.ezpay.service.UserInterfaceService;

/**
 * @author YashGaikwad 
 * 
 *
 */
public class UserInterfaceServiceTest2 {

	
	
	@Before
	public void setUp() throws Exception {
		
	}
		       
	

	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
    public void testFetchDeviceFromDB() throws SQLException, ClassNotFoundException {
		DeviceDAO deviceDAO = mock(DeviceDAO.class);
		
		ResultSet resultSet1 = mock(ResultSet.class);
		ResultSet resultSet2 = mock(ResultSet.class);
		
        when(resultSet1.next()).thenReturn(true); 
        when(resultSet1.getString("device_type")).thenReturn("smartphone");
        when(resultSet1.getDouble("device_width")).thenReturn(6.5);
        when(resultSet1.getDouble("device_height")).thenReturn(5.0);
        when(resultSet1.getInt("id")).thenReturn(1);
        when(deviceDAO.getDevice(1)).thenReturn(resultSet1);
        
        when(resultSet2.next()).thenReturn(true); 
        when(resultSet2.getString("device_type")).thenReturn("tablet");
        when(resultSet2.getDouble("device_width")).thenReturn(7.0);
        when(resultSet2.getDouble("device_height")).thenReturn(8.0);
        when(resultSet2.getInt("id")).thenReturn(2);
        when(deviceDAO.getDevice(2)).thenReturn(resultSet2);
        UserInterfaceService userInterfaceService = new UserInterfaceService(1,deviceDAO);
		
        assertEquals(userInterfaceService.getUI(),new UserInterface(1,"smartphone",6.5,5.0));
        userInterfaceService = new UserInterfaceService(2,deviceDAO);
        assertEquals(userInterfaceService.getUI(),new UserInterface(2,"tablet",7.0,8.0));
        
	}
		
	}
 