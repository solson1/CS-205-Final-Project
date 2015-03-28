package databaseConnections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


public class DatabaseConnector {
	
	public static final String URL = "jdbc:mysql://localhost/ratatat";
	public static final String USER = "Ian";
	public static final String PASSWORD = "Gizmos01";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	public static LinkedList<String> getProfiles()
	{
		//declare variables in order to close them within the finally block;
		LinkedList<String> returnMe = new LinkedList<String>();
		Statement statement = null;
		ResultSet results = null;
		try
		{
			Connection connection = createConnection(URL, USER, PASSWORD, DRIVER_CLASS);
			
			statement = connection.createStatement();
			String query = "Select name from profiles where roundsWon = 0";
			
			statement.execute(query);
			results = statement.getResultSet();
			while(results.next())
			{
				returnMe.add(results.getString("name"));
			}
		}
		catch(Exception e) //handle all errors
		{
			System.out.println(e.getMessage());
		}
		finally //close all resources, including the result set and connection
		{
			if(results != null) {
				try{
					results.close();
				}
				catch(SQLException e)
				{ //ignore
				}
				results = null;
				
			}
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e)
				{ //ignore
				}
				statement = null;
			}
		}
		return returnMe;
	}
	
	private static Connection createConnection(String url, String user, String password, String driver_class)
	{
		Connection connection = null;
		try {
			//The Class.forName(driver_class) statement below is magic, Don't touch it r things will break
			Class.forName(driver_class);
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Success!");
		}
		catch (Exception e)
		{
			System.out.println("Fail ! ");
			System.out.println(e.getMessage());
		}
		return connection;
		
	}
	
	

}
