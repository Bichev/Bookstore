package com.tsystems.bookstore.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tsystems.bookstore.persistence.utils.JDBCConnector;

public class JDBCExample {
	
	public static void main (String... args){
		try {
			//Create new table 'Movie' in DB
			createMovieTable();
			
			//Insert new record to 'USR' table
			insertUserRecord();
			
			//Update 'bookreview' table
			updateReviewTable();
			
			//Delete from usr
			deleteRecordFromUSR();
			
			//Example of selection and ResultSet
			selectFromDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private static void createMovieTable () throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String dropMovieTableSQL = "DROP TABLE Movie";
		String createMovieTableSQL = "CREATE TABLE Movie(" +
									"id     NUMERIC(8) NOT NULL," +
									"title  VARCHAR(100)," +
									"isbn   VARCHAR(20) NOT NULL" +
									")";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			statement = dbConnection.createStatement();
			
			System.out.println(createMovieTableSQL + "\n");
			
			statement.execute(dropMovieTableSQL);
			statement.execute(createMovieTableSQL);
			
			System.out.println("\nTable 'Movie' was created!\n");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (!statement.isClosed()){
				statement.close();
				System.out.println("Statement was closed!");
			}
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}
	
	private static void insertUserRecord() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement pStatement = null;
		
		String insertUsrSQL = "Insert into USR " +
				"(ID,FIRSTNAME,LASTNAME,EMAIL,REGDATE) values " +
				"(?,?,?,?,?)";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			pStatement = dbConnection.prepareStatement(insertUsrSQL);
			
			System.out.println(insertUsrSQL);
			
			pStatement.setInt(1, 9);
			pStatement.setString(2, "Joshua");
			pStatement.setString(3, "Bloch");
			pStatement.setString(4, "bloch@gmail.com");
			pStatement.setTimestamp(5, getCurrentTimeStamp());
			
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (!pStatement.isClosed()){
				pStatement.close();
				System.out.println("Statement was closed!");
			}
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}
	
	private static void updateReviewTable() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement pStatement = null;
		
		String updateReviewSQL = "UPDATE bookreview " +
				"SET usr_id = ? " +
				"WHERE rating < ?";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			pStatement = dbConnection.prepareStatement(updateReviewSQL);
			
			System.out.println(updateReviewSQL);
			
			pStatement.setInt(1, 9);
			pStatement.setInt(2, 5);
			
			pStatement.executeUpdate();
			
			System.out.println("bookreview table was updated!\n");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (!pStatement.isClosed()){
				pStatement.close();
				System.out.println("Statement was closed!");
			}
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}
	
	private static void deleteRecordFromUSR() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement pStatement = null;
		
		String deleteUSR = "DELETE FROM usr WHERE id = ?";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			pStatement = dbConnection.prepareStatement(deleteUSR);
			
			System.out.println(deleteUSR);
			
			pStatement.setInt(1, 12);
			
			pStatement.executeUpdate();
			
			System.out.println("Record was deleted!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (!pStatement.isClosed()){
				pStatement.close();
				System.out.println("Statement was closed!");
			}
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}
	
	private static void selectFromDB () throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT title, rating, text " +
				"FROM book join bookreview on (book.id = bookreview.book_id)";
		
		try {
			dbConnection = JDBCConnector.getDBConnection();
			statement = dbConnection.createStatement();
			
			System.out.println(selectSQL);
			
			ResultSet rs = statement.executeQuery(selectSQL);
			
			while (rs.next()) {
				String bTitle = rs.getString("title");
				String bRating = rs.getString("rating");
				String rText = rs.getString("text");
				
				System.out.println("For book: " + bTitle + "\n" +
						"Rating and review was: " + bRating + " " + rText + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (!statement.isClosed()){
				statement.close();
				System.out.println("Statement was closed!");
			}
			if (!dbConnection.isClosed()) {
				dbConnection.close();
				System.out.println("Connection was closed!\n");
			}
		}
	}
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}

}
