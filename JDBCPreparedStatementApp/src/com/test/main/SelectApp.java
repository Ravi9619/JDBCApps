package com.test.main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.util.JDBCUtil;

public class SelectApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlSelectQuery = "select * from student where sid=?";
			
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				
				scanner = new Scanner(System.in);
				
				System.out.println("Enter student id ");
				int sid = scanner.nextInt();
				
				//precompiled query to set values
				pstmt.setInt(1, sid);
				
				// execute the query
				resultSet = pstmt.executeQuery();
				if(resultSet.next()) {
					System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
					
					System.out.printf("%d%11s%8d%11s%15s",resultSet.getInt(1),resultSet.getString(2),
							resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
					System.out.println();
				}else {
					System.out.println("Resource not available ");
				}
				
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
