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
	private CardName topOfDeck;
	
	public GameState(HashMap<String, CardName[]> playerHands, int roundScore, int gameScore, CardName faceUpDiscard, CardName topOfDeck)
	{
		this.playerHands = playerHands;
		this.roundScore = roundScore;
		this.gameScore = gameScore;
		this.faceUpDiscard = faceUpDiscard;
		this.topOfDeck = topOfDeck;
	}

	/**
	 * @return the playerHands
	 */
	public HashMap<String, CardName[]> getPlayerHands() {
		return playerHands;
	}

	/**
	 * @return the roundScore
	 */
	public int getRoundScore() {
		return roundScore;
	}

	/**
	 * @return the gameScore
	 */
	public int getGameScore() {
		return gameScore;
	}

	/**
	 * @return the faceUpDiscard
	 */
	public CardName getFaceUpDiscard() {
		return faceUpDiscard;
	}

	/**
	 * @return the topOfDeck
	 */
	public CardName getTopOfDeck() {
		return topOfDeck;
	}

	

	
	
}
