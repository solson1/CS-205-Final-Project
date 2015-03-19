/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

/**
 * The Game interface specifies all of the necessary methods for the "Game object" which 
 * include a public constructor accepting the number of players and the gameType, 
 * as well as an action method
 * @author Ian
 *
 */
public interface Game {
	
	public GameState action(Action action);
	public GameState getCurrentState();
}
