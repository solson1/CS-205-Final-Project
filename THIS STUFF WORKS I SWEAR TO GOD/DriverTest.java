//package databaseConnections;


import java.util.ArrayList;
import java.util.LinkedList;



public class DriverTest {

	
	public static void main(String[] args)
	{
		
		ArrayList<Integer> scores = DatabaseConnector.getScores("Ian");
		for(Integer num:scores)
		{
			System.out.println(num.intValue());
		}
		
		DatabaseConnector.updatePlayerProfile("Ian", 5,7,8);
		
		
		scores = DatabaseConnector.getScores("Ian");
		for(Integer num:scores)
		{
			System.out.println(num.intValue());
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