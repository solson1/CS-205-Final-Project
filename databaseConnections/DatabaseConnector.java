package databaseConnections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


public class DatabaseConnector {
	
	public static final String URL = "jdbc:mysql://localhost/ratatat";
	public static final String USER = "gamer";
	public static final String PASSWORD = "game";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	
	
	//NOTE RE: DATABASE_CREATION_QUERY: the saved game table is left without any tables describing the save game 
	//status due to the incomplete nature of the gameObject at present. This feature will be implemented when the gameObject is finished.
	public static final String TABLE_CREATION_PROFILES = "CREATE TABLE IF NOT EXISTS profiles (name varchar(20) not null, "
			+ "games_played int, rounds_won int, rounds_lost int, PRIMARY KEY (name));";
	
	public static final String TABLE_CREATION_SAVED_GAMES = "CREATE TABLE IF NOT EXISTS saved_games "
			+ "(player_name varchar(20) NOT NULL REFERENCES profiles, save_name varchar(20) NOT NULL, "
			+ "PRIMARY KEY (player_name, save_name));";
	
	public static final String GET_PROFILES = "SELECT name FROM profiles;";
	public static final String NAME_IDENTIFIER = "name";
	
	public static final String CREATE_USER_QUERY_FIRST = "INSERT INTO PROFILES (NAME, GAMES_PLAYED, ROUNDS_WON, ROUNDS_LOST)"
			+ " VALUES ('";
	public static final String CREATE_USER_QUERY_LAST = "',0,0,0);";
	
	public static final String SAVE_GAME_QUERY_FIRST = "INSERT INTO SAVED_GAMES (player_name, save_name) VALUES ('";
	public static final String SAVE_GAME_QUERY_MIDDLE = "', '";
	public static final String SAVE_GAME_QUERY_LAST = "');";
	
	public static final String GET_SAVED_GAMES_FIRST = "SELECT save_name FROM saved_games WHERE player_name = '";
	public static final String GET_SAVED_GAMES_LAST = "';";
	public static final String SAVE_NAME_IDENTIFIER = "save_name";
	
	public static final String UPDATE_PROFILE_INCREMENT_GAMESPLAYED_FIRST = "UPDATE profiles SET games_played = game_played + 1 "
																			+ " WHERE name = '";
	public static final String UPDATE_PROFILE_INCREMENT_GAMESPLAYED_LAST =  "';";
	
	/**
	 * initializeDatabase sets up a connection to MySQL, and creates a database for the game if none exists 
	 */
	public static void initializeDatabase()
	{
		executeNonReturningQuery(TABLE_CREATION_PROFILES);
		executeNonReturningQuery(TABLE_CREATION_SAVED_GAMES);
	}
	
	
	/**
	 * The get profiles method returns a linked list of string objects describing the available player profiles in the database.
	 * @return
	 */
	public static LinkedList<String> getProfiles()
	{
		String query = GET_PROFILES;
		return stringListReturningQuery(query, NAME_IDENTIFIER);
	}
	
	
	/**
	 * CreateProfile allows the user to update the database to create a new user row under the profiles table. All database fields will be set to 0.
	 * @param playerName
	 */
	public static void createProfile(String playerName)
	{
		Statement statement = null;
		ResultSet results = null;
		
		//Enclosed within try block
		try
		{
			Connection connection = createConnection(URL, USER, PASSWORD, DRIVER_CLASS);
			
			statement = connection.createStatement();
			//Create the query by concatenating the first portion of the create user query form, the 
			//player name and the last part of the create user query form.
			String query = CREATE_USER_QUERY_FIRST + playerName + CREATE_USER_QUERY_LAST;
			System.out.println(query);
			statement.execute(query);
			results = statement.getResultSet();
			//Do we need to handle the results Here? I don't think so
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			closeResources(statement, results);
			statement  = null;
			results = null;
		}
	}
	
	/**
	 * saveGame allows the user to access the saved_games table in the database, inserting or loading data.
	 * THIS METHOD IS INCOMPLETE: TO DO: ADD SAVE GAME FUNCTIONALITY TO INSERT INFORMATION REGARDING 
	 * STATE OF GAMEPLAY TO DATABASE
	 * @param playerName
	 * @param gameName
	 */
	public static void saveGame(String playerName, String gameName)
	{
		String query = SAVE_GAME_QUERY_FIRST + playerName + SAVE_GAME_QUERY_MIDDLE + gameName + SAVE_GAME_QUERY_LAST;
		
		executeNonReturningQuery(query);
	}
	
	/**
	 * getSavedGames returns a linkedList of the saved games for a particular player specified by the input 
	 * argument playerName.
	 * @param playerName
	 * @return
	 */
	public static LinkedList<String> getSavedGames(String playerName)
	{	
		String query = GET_SAVED_GAMES_FIRST + playerName + GET_SAVED_GAMES_LAST;
		
		return stringListReturningQuery(query, SAVE_NAME_IDENTIFIER);
	}
	
	/**
	 * StringListReturningQuery accepts a query and returns a linkedlist of strings containing the results of that query.
	 * this method can be used to get profile names or save game names. The columnID describes the column of the table containing
	 * the desired information.
	 * @param query
	 * @return
	 */
	private static LinkedList<String> stringListReturningQuery(String query, String columnId)
	{
		//declare variables in order to close them within the finally block;
				LinkedList<String> returnMe = new LinkedList<String>();
				Statement statement = null;
				ResultSet results = null;
				try
				{
					Connection connection = createConnection(URL, USER, PASSWORD, DRIVER_CLASS);
					
					statement = connection.createStatement();
					statement.execute(query);
					results = statement.getResultSet();
					while(results.next())
					{
						returnMe.add(results.getString(columnId));
					}
				}
				catch(Exception e) //handle all errors
				{
					System.out.println(e.getMessage());
				}
				finally //close all resources, including the result set and connection
				{
					closeResources(statement, results);
					statement = null;
					results = null;
				}
				return returnMe;
	}
	
	
	private static void executeNonReturningQuery(String query)
	{
		Statement statement = null;
		ResultSet results = null;
		//SuccessOrFail is a boolean describing if the queries have executed without errors.
		boolean successOrFail = false;
		//Enclosed within try block
		try
		{
			Connection connection = createConnection(URL, USER, PASSWORD, DRIVER_CLASS);
			statement = connection.createStatement();
			statement.execute(query);
			results = statement.getResultSet();
			//Do we need to handle the results Here? I don't think so
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			closeResources(statement, results);
			statement = null;
			results = null;
		}
	}
	
	private static Connection createConnection(String url, String user, String password, String driver_class)
	{
		Connection connection = null;
		try {
			//The Class.forName(driver_class) statement below is magic, Don't touch it or things will break
			Class.forName(driver_class);
			connection = DriverManager.getConnection(url, user, password);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	private static void closeResources(Statement statement, ResultSet results)
	{
		if(results != null) {
			try{
				results.close();
			}
			catch(SQLException e)
			{ //ignore
			}			
		}
		if(statement != null) {
			try {
				statement.close();
			}
			catch(SQLException e)
			{ //ignore
			}
		}
	}
}
