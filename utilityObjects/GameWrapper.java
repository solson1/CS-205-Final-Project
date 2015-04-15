package utilityObjects;

import java.util.ArrayList;
import java.util.LinkedList;

import databaseConnections.DatabaseConnector;

public class GameWrapper {
	
	private LinkedList<AIDummy> aiPlayerList;
	private GameType type;
	private GameObject game;
	private String playerID;
	
	public GameWrapper()
	{
		//Initialize default values
		this.playerID = "DefaultPlayer";
		//Perform the database initialization step, encased in a try/catch block
		DatabaseConnector.initializeDatabase();
	}
	
	//--------------------------------------------------------------------
	//DATABASE METHODS----------------------------------------------------
	//--------------------------------------------------------------------
	/**
	 * the login method queries the database to see if a given profile name currently
	 * exists, creates an entry in the profiles table if it does not exist, and 
	 * sets the current session's player name to the playerName argument. 
	 * @param playerName
	 */
	public void login(String playerName)
	{
		LinkedList<String> existingProfiles = DatabaseConnector.getProfiles();
		
		boolean found = false;
		for(String profile:existingProfiles)
		{
			if(profile.equalsIgnoreCase(playerName))
				found = true;
		}
		if(found == false)
			DatabaseConnector.createProfile(playerName);
		this.playerID = playerName;
	}
	
	public LinkedList<String> getProfiles()
	{
		return DatabaseConnector.getProfiles();
	}
	
	public void createProfile(String playerName)
	{
		DatabaseConnector.createProfile(playerName);
		this.playerID = playerName;
	}
	
	public LinkedList<String> getSavedGames(String playerName)
	{
		return DatabaseConnector.getSavedGames(playerName);
	}
	
	public ArrayList<Integer> getScores(String playerName)
	{
		return DatabaseConnector.getScores(playerName);
	}
	
	/**
	 * LoadGame is currently a non-functional stub due to missing gameobject
	 * Method has no return.
	 * @param playerName
	 * @param gameName
	 */
	public void loadGame(String playerName, String gameName)
	{
		DatabaseConnector.loadGame(playerName, gameName);
	}
	
	/**
	 * SaveGame is currently a non-functional due to missing gameObject class.
	 * Currently, method inserts a new row containing the playername and the 
	 * save game name into the savedGames table, while inserting no actual game data.
	 * @param playerName
	 * @param gameName
	 */
	public void saveGame(String playerName, String gameName)
	{
		DatabaseConnector.saveGame(playerName, gameName);
	}
	
	public void updatePlayerProfile(String playerName, int gamesPlayed, int roundsWon, int roundsLost)
	{
		DatabaseConnector.updatePlayerProfile(playerName, gamesPlayed, roundsWon, roundsLost);
	}	
	//--------------------------------------------------------------------
	//END DATABASE METHODS----------------------------------------------------
	//--------------------------------------------------------------------
	
	
	
	/**
	 * StartNewGame accepts an integer arguement describing the number of players in the game, as well as a GameType value describing the type.
	 * The method initializes several of the object's fields, such as the list of AI players and the game object. 
	 * The method returns the current state of the game to the GUI. 
	 * @param numPlayers
	 * @param type
	 * @return
	 */
	public GameState startNewGame(GameType type, int numPlayers, String[] players)
	{	
		//first, generate and populate the list of AI players;
		this.aiPlayerList = this.generateListOfAI(numPlayers, type);
		//next, generate a new game object
		this.game = new GameObject(type, players);
		//finally, return that game
		return this.game.generateCurrentGameState();
	}
	
	/**
	 * PlayerAction accepts an action object, executes that action by calling the game's action method, and returns the resulting gamestate object.
	 */
	public GameState playerAction(Action action)
	{
		return this.game.action(action);
	}
	
	/**
	 * AIAction executes the AIAction method on the GameWrapper's list of AI objects in sequence, creates a list of GameState objects describing 
	 * the state of the game following the execution of each AI action, and returns that list.
	 * @return
	 */
	public LinkedList<GameState> AIAction()
	{
		//Create the list of resulting states
		LinkedList<GameState> states = new LinkedList<GameState>();
		//Get the current state in order to feed it to the AI's in sequence
		GameState current = this.game.generateCurrentGameState();
		for(AIDummy ai: this.aiPlayerList)
		{
			//Give the AI the current state and store the resulting action object in the "action" variable
			Action action = ai.aiAction(current);
			//execute the action by calling the game's action method, storing the resulting gamestate in "newState"
			GameState newState = this.game.action(action);
			
			//Add the new state to the states list.
			states.add(newState);
			//set the 'current' state to newState
			current = newState;
		}
		
		return states;
	}
	
	
	/**
	 * generateListofAi creates and returns a linked list of AI player objects The parameter arguement numPlayers describes the GLOBAL 
	 * number of players, not the number of AI players. The number of AI players to be produced and returned is = numPlayers - 1 
	 * with the - 1 representing the human player. This is included as an argument because the AI players need to know both how many players
	 * there are total, as well as the method needing to know how many to generate.
	 * @param numPlayers
	 * @return
	 */
	private LinkedList<AIDummy> generateListOfAI(int numPlayers, GameType type)
	{
		LinkedList<AIDummy> AIPlayers = new LinkedList<AIDummy>();
		for(int i = 0; i < numPlayers - 1; i++)
		{
			AIPlayers.add(new AIDummy(numPlayers, type));
		}
		
		return AIPlayers;
	}

}
