package com.test.main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.util.JDBCUtil;

public class UpdateApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;

		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlUpdateQuery = "update student set saddress=? where sid=?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);

			if (pstmt != null) {
				
				scanner = new Scanner(System.in);
				
				System.out.println("Enter student sid ");
				int sid = scanner.nextInt();
				
				System.out.println("Enter student address");
				String saddress = scanner.next();

				// use precompiled query to set the values
				pstmt.setString(1,saddress);
				pstmt.setInt(2, sid);

				System.out.println(sqlUpdateQuery);
				
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
