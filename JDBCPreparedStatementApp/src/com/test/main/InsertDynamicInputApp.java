package com.test.main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.util.JDBCUtil;

public class InsertDynamicInputApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;

		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`,`gender`)values(?,?,?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {
				
				scanner = new Scanner(System.in);
				
				System.out.println("Enter student name");
				String sname = scanner.next();
				
				System.out.println("Enter student age");
				int sage = scanner.nextInt();
				
				System.out.println("Enter student address");
				String saddress = scanner.next();
				
				System.out.println("Enter student gender");
				String sgender = scanner.next();

				// use precompiled query to set the values
				pstmt.setString(1,sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				pstmt.setString(4, sgender);

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
