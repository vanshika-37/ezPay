package com.ezPay.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ezPay.util.DbConnection;

public class DeviceDAO {

	
	public ResultSet getDevice(int deviceId) throws SQLException, ClassNotFoundException {
		Connection conn = DbConnection.GetConnection();

		PreparedStatement preparedStatement = conn.prepareStatement(
				"SELECT ID, DEVICE_TYPE, DEVICE_WIDTH, DEVICE_HEIGHT FROM DEVICE WHERE ID = ?");
		preparedStatement.setInt(1, deviceId);

		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet;
		
	}
}
