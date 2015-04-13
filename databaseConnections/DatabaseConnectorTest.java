package databaseConnections;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class DatabaseConnectorTest extends TestCase{

	private String userName = "TEST_USER_NAME";

	@Before
	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testInitialization()
	{
		try
		{
			DatabaseConnector.initializeDatabase();
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testGetProfiles()
	{
		try
		{
			LinkedList<String> profiles = DatabaseConnector.getProfiles();
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCreateProfile()
	{
		try
		{
			DatabaseConnector.createProfile(userName);
			LinkedList<String> users = DatabaseConnector.getProfiles();
			
			assertTrue("Expected database to contain " + userName ,users.contains(userName));
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * testGetScores tests the ability of the database connector to make changes to scores data contained within 
	 * the profiles table of the database. It tests whether updated values differ from the original values by some 
	 * parameter "differential"
	 */
	@Test 
	public void testGetScores()
	{		
		try
		{
			//declare differential parameter and get the starting values for the test user.
			int differential = 1;
			ArrayList<Integer> scores = DatabaseConnector.getScores(userName);
			//Execute the changes
			DatabaseConnector.updatePlayerProfile(userName, differential, differential, differential);
			//Get the updated scores
			ArrayList<Integer> updatedScores = DatabaseConnector.getScores(userName);
			//cycle through the list and compare the updated values to the starting values, asserting that 
			//the difference between the two is equal to the differential.
			for(int index = 0; index < scores.size(); index++)
			{
				assertEquals((updatedScores.get(index) - scores.get(index)), differential, .00001);
			}	
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * TestSaveGame covers the saveGame and getSavedGames database methods, by creating a new saved game
	 * and asserting that the getSavedGames method contains a saved game with that name.
	 */
	@Test
	public void testSaveGame()
	{
		String saveName = "TEST_SAVE";
		DatabaseConnector.saveGame(userName, saveName);
		
		LinkedList<String> savedGames = DatabaseConnector.getSavedGames(userName);
		
		boolean found = false;
		for(String name: savedGames)
		{
			if(name.equalsIgnoreCase(saveName))
			{
				found = true;
			}
		}
		
		assertTrue(found);
	}
}
