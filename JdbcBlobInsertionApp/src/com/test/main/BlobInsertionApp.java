package com.test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.test.util.JdbcUtil;

public class BlobInsertionApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;

		String name = null;
		String imageLoc = null;

		try {

			connection = JdbcUtil.getJdbConnection();
			String sqlInsertQuery = "insert into persons(`name`,`image`) values(?,?)";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);

				if (pstmt != null) {
					sc = new Scanner(System.in);

					if (sc != null) {
						System.out.print("Enter the name :: ");
						name = sc.next();

						System.out.print("Enter image location :: ");
						imageLoc = sc.next();
					}

					pstmt.setString(1, name);
					pstmt.setBinaryStream(2, new FileInputStream(new File(imageLoc)));

					int rowAffected = pstmt.executeUpdate();
					System.out.println("No of rows inserted inserted is :: " + rowAffected);
				}
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sc.close();
		}

	}

}
