package com.tsystems.bookstore.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tsystems.bookstore.persistence.utils.JDBCConnector;

public class JDBCTransaction {
	
	public static void main(String... args) {
		try {
			modifyTuniDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
private static void modifyTuniDB() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement pStatementInsert = null;
		PreparedStatement pStatementUpdate = null;
		
		String insertBookSQL = "INSERT INTO book " +
				"(ID,TITLE,ISBN) values " +
				"(?,?,?)";
		
		String updateBookSQL = "UPDATE book " +
				"SET title = ? " +
				"WHERE ID = ?";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			pStatementInsert = dbConnection.prepareStatement(insertBookSQL);
			pStatementUpdate = dbConnection.prepareStatement(updateBookSQL);
			
			// start a transaction block
			dbConnection.setAutoCommit(false);
			
			pStatementInsert.setInt(1, 9);
			pStatementInsert.setString(2, "Effective Java (2nd Edition)");
			pStatementInsert.setString(3, "978-0321356680");
			pStatementInsert.addBatch();
			
			pStatementInsert.setInt(1, 10);
			pStatementInsert.setString(2, "Thinking in Java (3th Edition)");
			pStatementInsert.setString(3, "978-0131872486");
			pStatementInsert.addBatch();
			
			pStatementInsert.setInt(1, 11);
			pStatementInsert.setString(2, "Design Patterns: Elements of Reusable Object-Oriented Software");
			pStatementInsert.setString(3, "978-0201633610");
			pStatementInsert.addBatch();
			
			pStatementInsert.executeBatch();
			
			pStatementUpdate.setString(1, "Thinking in C++");
			pStatementUpdate.setInt(2, 10);
			pStatementUpdate.addBatch();
			pStatementUpdate.executeBatch();
//			pStatementUpdate.executeQuery();
			
			//end a transaction block
			dbConnection.commit();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
			dbConnection.rollback();
		} finally {
			if (!pStatementInsert.isClosed()){
				pStatementInsert.close();
				System.out.println("Statement Insert was closed!");
			}
			
			if (!pStatementUpdate.isClosed()){
				pStatementUpdate.close();
				System.out.println("Statement Update was closed!");
			}
			
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}

}
