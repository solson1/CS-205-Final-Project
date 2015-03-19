package utilityObjects;

import java.util.LinkedList;

public class GameWrapper implements Wrapper {
	
	private LinkedList<AIDummy> aiPlayerList;
	private GameType type;
	private Game game;
	
	public GameWrapper()
	{
		//Database connections go here
	}
	
	/**
	 * StartNewGame accepts an integer arguement describing the number of players in the game, as well as a GameType value describing the type.
	 * The method initializes several of the object's fields, such as the list of AI players and the game object. 
	 * The method returns the current state of the game to the GUI. 
	 * @param numPlayers
	 * @param type
	 * @return
	 */
	public GameState startNewGame(GameType type, int numPlayers)
	{	
		//first, generate and populate the list of AI players;
		this.aiPlayerList = this.generateListOfAI(numPlayers, type);
		//next, generate a new game object
		this.game = new GameObjectDummy(numPlayers, type);
		//finally, return that game
		return this.game.getCurrentState();
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
		GameState current = this.game.getCurrentState();
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
