/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

import java.util.LinkedList;

/**
 * The Database interface specifies all of the methods required for the 
 * Ratatat Cat game's database functionality. This involves saving and loading 
 * games and a persistent tracking of player stats. 
 * @author Ian
 *
 */
public interface Database {
	
	//3-12-15 Create a FULLGAME object which contains all 
	//necessary game information, create the following method:
	//public FullGame(String gameName);
	//public void saveGame(String gameName, FullGame game);
	public void login(String playerName);
	public void createProfile(String playerName);
	public LinkedList<String> getProfiles();
	public LinkedList<String> getSavedGames();
	public void updatePlayerProfile(String playerName, boolean roundWon, boolean gameWon);
}
