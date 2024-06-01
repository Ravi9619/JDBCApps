package com.test.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import com.test.util.JdbcUtil;

public class ImageRetrievalApp {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		
		int id = 1;
		try {
			conn = JdbcUtil.getJdbConnection();
			String sqlSelectQuery = "select id,name,image from persons where id=?";
			
			if(conn != null) {
				pstmt = conn.prepareStatement(sqlSelectQuery);
				
				if(pstmt != null) {
					
					pstmt.setInt(1, id);
					resultSet = pstmt.executeQuery();
					
					}
				if(resultSet != null) {
					if(resultSet.next()) {
						System.out.println("ID\tNAME\tIMAGE");
						
						id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						InputStream is = resultSet.getBinaryStream(3);
						
						File file = new File("copied.jpg");
						FileOutputStream fos = new FileOutputStream(file);
						
//						int i = is.read();
//						while(i != -1) {
//							fos.write(i);
//							i = is.read();
//						}
						
//						byte[] b = new byte[1024];
//						while(is.read(b) > 0) {
//							fos.write(b);
//						}
						
						//Replace byte code with apache io utils commons io jar 
						IOUtils.copy(is, fos);
						
						fos.close();
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
