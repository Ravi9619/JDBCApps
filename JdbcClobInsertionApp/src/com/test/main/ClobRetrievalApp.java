package com.test.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import com.test.util.JdbcUtil;

public class ClobRetrievalApp {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		
		int id = 2;
		try {
			conn = JdbcUtil.getJdbConnection();
			String sqlSelectQuery = "select id,name,history from cities where id=?";
			
			if(conn != null) {
				pstmt = conn.prepareStatement(sqlSelectQuery);
				
				if(pstmt != null) {
					
					pstmt.setInt(1, id);
					resultSet = pstmt.executeQuery();
					
					}
				if(resultSet != null) {
					if(resultSet.next()) {
						System.out.println("ID\tNAME\tHISTORY");
						
						id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						Reader reader= resultSet.getCharacterStream(3);
						
						File file = new File("history_copied.txt");
						FileWriter writer = new FileWriter(file);
						
						//Replace byte code with apache io utils commons io jar 
						IOUtils.copy(reader, writer);
						
						writer.close();
						System.out.println(id + "\t" + name + "\t" + file.getAbsolutePath());
						
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
