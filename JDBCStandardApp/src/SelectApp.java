import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.demo.util.JdbcUtil;

public class SelectApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			
			connection = JdbcUtil.getJdbConnection();
			
			if(connection != null)
				statement = connection.createStatement();
				System.out.println("Statement Object created");
			
			if(statement != null) 
				resultSet = statement.executeQuery("select sid,sname,sage, saddress, gender from student");
			
			if(resultSet != null) {
				System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
				while(resultSet.next()) {
					int sid = resultSet.getInt(1);
					String sname = resultSet.getString(2);
					int sage = resultSet.getInt(3);
					String saddress = resultSet.getString(4);
					String sgender = resultSet.getString(5);
					System.out.println(sid +"\t"+ sname + "\t"+ sage + "\t"+ saddress+ "\t"+ sgender);
				}
			}
		}catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, statement, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
