
public class TestApp {

	public static void main(String[] args) {
		
		java.util.Date uDate = new java.util.Date();
		System.out.println("utilDate is: "+uDate);
		
		long value = uDate.getTime();
		System.out.println("Information about date in milliseconds: "+value);
		
		java.sql.Date sqlDate = new java.sql.Date(value);
		System.out.println("sql Date: "+sqlDate);

	}

}
