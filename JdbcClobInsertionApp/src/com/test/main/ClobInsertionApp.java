package com.test.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.util.JdbcUtil;

public class ClobInsertionApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;

		String name = null;
		String pdfLoc = null;

		try {

			connection = JdbcUtil.getJdbConnection();
			String sqlInsertQuery = "insert into cities(`name`,`history`) values(?,?)";

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);

				if (pstmt != null) {
					sc = new Scanner(System.in);

					if (sc != null) {
						System.out.print("Enter city name :: ");
						name = sc.next();

						System.out.print("Enter pdf location :: ");
						pdfLoc = sc.next();
					}

					pstmt.setString(1, name);
					pstmt.setCharacterStream(2, new FileReader(new File(pdfLoc)));

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
