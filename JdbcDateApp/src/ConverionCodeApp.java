import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConverionCodeApp {

	public static void main(String[] args) {
		
		//Read input from user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter date:: (dd-MM-yyyy)");
		String sDate = sc.next();
		
		//Convert date from string format to java.util.Date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date uDate = sdf.parse(sDate);
			
			//Convert java.util.Date to java.sql.Date
			long value = uDate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(value);
			
			//Print all 3 formats of date
			System.out.println("String format date:: "+sDate);
			System.out.println("Util Date:: "+uDate);
			System.out.println("Sql Date:: "+sDate);
			
			System.out.println();
			
			System.out.println("Enter date in format:: (yyyy-MM-dd)");
			String standardInput = sc.next();
			java.sql.Date sqlStandardInput = java.sql.Date.valueOf(standardInput);
			System.out.println("Standard Input "+standardInput);
			System.out.println("SqlStandard Input "+sqlStandardInput);
			
			
			sc.close();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
