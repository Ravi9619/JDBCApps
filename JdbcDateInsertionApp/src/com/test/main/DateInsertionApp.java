package com.test.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.test.util.JdbcUtil;

public class DateInsertionApp {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;
		
		java.sql.Date sqlDob = null;
		java.sql.Date sqlDom = null;
		
		String name = null;
		String dob = null;
		String dom = null;
		
		try {
			
			connection = JdbcUtil.getJdbConnection();
			String sqlInsertQuery = "insert into users(`name`,`dob`,`dom`) values(?,?,?)";
			
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
				
				if(pstmt != null) {
					sc = new Scanner(System.in);
					
					if(sc != null) {
						System.out.print("Enter the username :: ");
						name = sc.next();

						System.out.print("Enter the dob(MM-dd-yyyy) :: ");
						dob = sc.next();

						System.out.print("Enter the dom(yyyy-MM-dd) :: ");
						dom = sc.next();
					}
					
					if(dob != null) {
						//Conversion of string to sql date
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						java.util.Date uDate = sdf.parse(dob);
						
						long value = uDate.getTime();
						sqlDob = new java.sql.Date(value);
					}
					
					if(dom != null) {
						sqlDom = java.sql.Date.valueOf(dom);
					}
					
					pstmt.setString(1, name);
					pstmt.setDate(2, sqlDom);
					pstmt.setDate(3, sqlDom);
					
					int rowAffected = pstmt.executeUpdate();
					System.out.println("No of rows inserted inserted is :: " + rowAffected);
				}
				}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException pe) {
			// TODO Auto-generated catch block
			pe.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sc.close();
		}

	}

}
