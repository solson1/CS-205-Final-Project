/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

/**
 * The Wrapper interface specifies the methods required 
 * for the gameWapper object. The wrapper object contains
 * the AI player object, the Game object itself, as well as the 
 * database connection. The wrapper exists to make the GUI
 * portion of the game much simpler, by providing a single point
 * of interaction between the GUI and the gameLogic.
 * @author Ian
 */
public interface Wrapper {
	
	public GameState playerAction(Action action);
	public GameState startNewGame(GameType type, int numPlayers);
	//3/12/15 NOTE: FUTURE METHODS TO ADD TO INTERFACE: DATABASE METHODS, Save/Load functionality
	
}
