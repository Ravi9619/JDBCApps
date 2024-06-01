package com.test.main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.test.util.JDBCUtil;

public class InsertApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`,`gender`)values(?,?,?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				// use precompiled query to set the values
				pstmt.setString(1, "richa");
				pstmt.setInt(2, 21);
				pstmt.setString(3, "USA");
				pstmt.setString(4, "F");

				System.out.println(sqlInsertQuery);
				
				// execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: " + rowCount);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, pstmt, null);
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}
