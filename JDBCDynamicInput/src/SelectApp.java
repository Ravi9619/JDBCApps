import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) throws SQLException {

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
			String sqlQuery = "select * from student";
			statement = connection.createStatement();

			// Process ResultSet and execute query
			resultSet = statement.executeQuery(sqlQuery);

			System.out.println("SID\tSAGE\tSNAME\tSADDRESS");

			while (resultSet.next()) {
				Integer sid = resultSet.getInt(1);
				String sname = resultSet.getString(2);
				Integer sage = resultSet.getInt(3);
				String saddr = resultSet.getString(4);
				System.out.println(sid + "\t" + sname + "\t" + sage + "\t" + saddr);
			}

			// connection close
			resultSet.close();
			statement.close();
			connection.close();
			System.out.println("Closed resources...");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
