package com.capstone.ezpay.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capstone.ezpay.model.SupportTicket;
import com.capstone.ezpay.util.DbConnection;

/**
 * DAO class for managing the storage and retrieval of support tickets. Provides
 * methods to create, retrieve, and update support tickets in the database.
 * 
 * @author vanshika
 * @since 2024-08-19
 */
public class SupportDAO {

	/**
	 * Creates a new support ticket and saves it to the database.
	 * 
	 * @param ticket The SupportTicket object containing ticket details.
	 * @throws SQLException If a database access error occurs.
	 * @throws Exception    If any other error occurs.
	 */
	public void createSupportTicket(SupportTicket ticket) throws SQLException, Exception {
		// Establish database connection
		Connection connection = DbConnection.getConnection();

		// Prepare SQL INSERT statement for inserting a new support ticket
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO SUPPORT_TICKET (TICKET_ID, USER_ID, ISSUE_DESCRIPTION, STATUS, CREATED_DATE, RESOLVED_DATE) "
						+ "VALUES (support_ticket_seq.NEXTVAL, ?, ?, ?, ?, ?)");

		// Set parameters for the prepared statement
		preparedStatement.setInt(1, ticket.getUserId());
		preparedStatement.setString(2, ticket.getIssueDescription());
		preparedStatement.setString(3, ticket.getStatus());
		preparedStatement.setDate(4, new java.sql.Date(ticket.getCreatedDate().getTime()));
		preparedStatement.setDate(5, (Date) (ticket.getResolvedDate()));

		// Execute the update and check if the insertion was successful
		int result = preparedStatement.executeUpdate();

		// Commit the transaction if the insertion was successful
		if (result > 0) {
			connection.commit();
		}

		// Close the connection
		preparedStatement.close();
		connection.close();
	}

	/**
	 * Retrieves all support tickets for a specific user from the database.
	 * 
	 * @param userId The ID of the user whose tickets are to be retrieved.
	 * @return A list of SupportTicket objects for the specified user.
	 * @throws SQLException If a database access error occurs.
	 * @throws Exception    If any other error occurs.
	 */
	public List<SupportTicket> getTicketsByUserId(int userId) throws SQLException, Exception {
		// Establish database connection
		Connection connection = DbConnection.getConnection();

		// List to store retrieved support tickets
		List<SupportTicket> supportTicketList = new ArrayList<>();

		// Prepare SQL SELECT statement to retrieve tickets by user ID
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SUPPORT_TICKET WHERE USER_ID = ?");
		preparedStatement.setInt(1, userId);

		// Execute the query and process the result set
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			// Create a SupportTicket object for each record and add it to the list
			SupportTicket supportTicket = new SupportTicket(resultSet.getInt("TICKET_ID"), resultSet.getInt("USER_ID"),
					resultSet.getString("ISSUE_DESCRIPTION"), resultSet.getString("STATUS"),
					resultSet.getDate("CREATED_DATE"), resultSet.getDate("RESOLVED_DATE"));
			supportTicketList.add(supportTicket);
		}

		// Close the resources
		resultSet.close();
		preparedStatement.close();
		connection.close();

		// Return the list of support tickets
		return supportTicketList;
	}

	/**
	 * Updates the status of a ticket and sets the resolved date if the status is
	 * 'RESOLVED'.
	 * 
	 * @param ticketId The ID of the ticket to be updated.
	 * @param status   The new status of the ticket.
	 * @throws SQLException If a database access error occurs.
	 * @throws Exception    If any other error occurs.
	 */
	public void updateTicketStatus(int ticketId, String status) throws SQLException, Exception {
		// Establish database connection
		Connection connection = DbConnection.getConnection();

		// Prepare SQL UPDATE statement to update the ticket status
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE SUPPORT_TICKET SET STATUS = ? WHERE TICKET_ID = ?");
		preparedStatement.setString(1, status);
		preparedStatement.setInt(2, ticketId);

		// Execute the update and check if it was successful
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			connection.commit();
		}

		// If the status is 'RESOLVED', update the resolved date
		if ("RESOLVED".equalsIgnoreCase(status)) {
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("UPDATE SUPPORT_TICKET SET RESOLVED_DATE = ? WHERE TICKET_ID = ?");
			preparedStatement2.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			preparedStatement2.setInt(2, ticketId);

			int result2 = preparedStatement2.executeUpdate();
			if (result2 > 0) {
				connection.commit();
			}

			preparedStatement2.close();
		}

		// Close the resources
		preparedStatement.close();
		connection.close();
	}
}
