import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteApp {

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

			// Process ResultSet and execute query
			String sqlDeleteQuery = "delete from student where sid=2";
			int rowAffected = statement.executeUpdate(sqlDeleteQuery);
			System.out.println("No of rows affected::"+rowAffected);

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
