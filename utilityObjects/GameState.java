/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * The GameState object contains all the information required to draw the GUI representation of the 
 * game, as well as all the information available to a player on a given turn, as well as 
 * the AI. These objects are created in the Game object, and exported to the GUI or to the AI. 
 * @author Ian
 *
 */
public class GameState {
	private HashMap<String, CardName[]> playerHands;
	private int roundScore;
	private int gameScore;
	private CardName faceUpDiscard;
	
	public GameState(HashMap<String, CardName[]> playerHands, int roundScore, int gameScore, CardName faceUpDiscard)
	{
		this.playerHands = playerHands;
		this.roundScore = roundScore;
		this.gameScore = gameScore;
		this.faceUpDiscard = faceUpDiscard;
	}

	public HashMap<String, CardName[]> getPlayerHands() {
		return playerHands;
	}

	public int getRoundScore() {
		return roundScore;
	}

	public int getGameScore() {
		return gameScore;
	}

	public CardName getFaceUpDiscard() {
		return faceUpDiscard;
	}
	
	
	
}
