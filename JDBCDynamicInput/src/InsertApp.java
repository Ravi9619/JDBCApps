import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			// Load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loading driver...");

			// Establish Connection
			String url = "jdbc:mysql://localhost:3306/testdb";
			String userName = "root";
			String passWord = "Ravi85423@";

			connection = DriverManager.getConnection(url, userName, passWord);

			// Create Statement and send query
			statement = connection.createStatement();
			System.out.println("Statement Object created");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter name of student");
			String sname = sc.next();

			System.out.println("Enter student age");
			int sage = sc.nextInt();

			System.out.println("Enter student address");
			String saddress = sc.next();

			System.out.println("Enter student gender");
			String sgender = sc.next();

			// Process ResultSet and execute query
			String sqlInsertQuery = String.format(
					"insert into student(`sname`,`sage`,`saddress`,`gender`) values('%s',%d,'%s','%s')", sname, sage,
					saddress, sgender);
			
			int rowAffected = statement.executeUpdate(sqlInsertQuery);
			System.out.println("No of rows affected::" + rowAffected);

			// connection close
			statement.close();
			connection.close();
			System.out.println("Closed resources...");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
