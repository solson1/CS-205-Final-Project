package databaseConnections;


import java.util.LinkedList;



public class DriverTest {

	
	public static void main(String[] args)
	{
		DatabaseConnector.createProfile("ian");
		DatabaseConnector.createProfile("dilbert");
		LinkedList<String> profiles = DatabaseConnector.getProfiles();
		for(String name: profiles)
		{
			System.out.println(name);
		}
	}
	
	public static void testInitProfCreate()
	{
		
		DatabaseConnector.initializeDatabase();
		
		String playerName = "Fred";
		
		DatabaseConnector.createProfile(playerName);
		
		playerName = "Martha";
		
		DatabaseConnector.createProfile(playerName);
		
		LinkedList<String> players = DatabaseConnector.getProfiles();
		for(String name:players)
		{
			System.out.println(name);
		}
	}
	
}