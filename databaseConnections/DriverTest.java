package databaseConnections;


import java.util.LinkedList;



public class DriverTest {

	public static final String URL = "jdbc:mysql://localhost/ratatat";
	public static final String USER = "Ian";
	public static final String PASSWORD = "Gizmos01";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	
	public static void main(String[] args)
	{
		LinkedList<String> results = new LinkedList<String>();
		
		results = DatabaseConnector.getProfiles();
		for(String name: results)
		{
			System.out.println(name);
		}
		
	}
	
}