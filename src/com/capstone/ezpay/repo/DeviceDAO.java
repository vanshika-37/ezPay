package com.capstone.ezpay.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.ezpay.util.DbConnection;

/**
 * @author Vaishnave
 * @since 2024-08-19
 * 
 * Retrieves device details from the database for a given device ID.
 *
 * @param deviceId the ID of the device to retrieve
 * @return a ResultSet containing the device details (ID, DEVICE_TYPE, DEVICE_WIDTH, DEVICE_HEIGHT)
 * @throws SQLException if a database access error occurs
 * @throws ClassNotFoundException if the JDBC driver class is not found
 */

public class DeviceDAO {

	
	public ResultSet getDevice(int deviceId) throws SQLException, ClassNotFoundException {
		
		// Establish a connection to the database using the utility class
		Connection conn = DbConnection.getConnection();

		// Prepare the SQL query to select device details where the ID matches the given deviceId
		PreparedStatement preparedStatement = conn.prepareStatement(
				"SELECT ID, DEVICE_TYPE, DEVICE_WIDTH, DEVICE_HEIGHT FROM DEVICE WHERE ID = ?");
		preparedStatement.setInt(1, deviceId);

		// Execute the query and store the result in a ResultSet
		ResultSet resultSet = preparedStatement.executeQuery();

		// Return the ResultSet containing the device details
		return resultSet;
		
	}
}
