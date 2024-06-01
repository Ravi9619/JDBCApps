package com.test.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.test.util.JdbcUtil;

public class DateRetrievalApp {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		
		int id = 1;
		try {
			conn = JdbcUtil.getJdbConnection();
			String sqlSelectQuery = "select * from users where id=?";
			
			if(conn != null) {
				pstmt = conn.prepareStatement(sqlSelectQuery);
				
				if(pstmt != null) {
					
					pstmt.setInt(1, id);
					resultSet = pstmt.executeQuery();
					
					}
				if(resultSet != null) {
					if(resultSet.next()) {
						System.out.println("SID\tSNAME\tSODB\t\tSDOM");
						
						id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						java.sql.Date dob = resultSet.getDate(3);
						java.sql.Date dom = resultSet.getDate(4);
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String strDob = sdf.format(dob);
						String strDom = sdf.format(dom);
						System.out.println(id + "\t" + name + "\t" + strDob + "\t" + strDom);
						
				}else {
					System.out.println("Resource not available with id: "+id);
				}
			}
		} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
