//package utilityObjects;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;
import junit.framework.TestCase;

public class GameObjectTest extends TestCase{
	
	@Test
	public void testConstruction()
	{
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
	}
	
	@Test
	public void testDrawFromDeckAction()
	{
		//Generate a new Game
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
		//Get the current state of the game, and obtain the contents of "Steve"'s hand at position one, 
		GameState originalState = game.generateCurrentGameState();
		
		//get the current top of the deck.
		CardName topOfDeck = originalState.getTopOfDeck();
		
	
		CardName[] originalSteveHand = originalState.getPlayerHands().get("Steve");
		
		//create an action object representing that "Steve" wants to draw a card and place it in position 
		//"Four" in his hand
		Action action = new Action(ActionName.DRAW, PileName.DECK, PileName.THREE, "Steve", "Steve",null);
		
		//perform the action and store the resulting gamestate
		GameState updatedGameState = game.action(action);
		
		//get the card at position THREE in steve's hand, and assert that it == the original top of the deck.
		CardName[] steveHand = updatedGameState.getPlayerHands().get("Steve");
		CardName cardAtPositionTHREE = steveHand[3];
		
		assertTrue("Unexpected card found in player's hand while attempting draw operation from deck.", cardAtPositionTHREE == topOfDeck);		
	}
	
	@Test
	public void testDrawFromDiscardAction()
	{
		//generate a new game object
		//execute the draw method from the deck, in order to ensure there is something on the discard pile
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
		Action action = new Action(ActionName.DRAW, PileName.DECK, PileName.THREE, "Steve", "Steve",null);
		
		//perform the action and store the resulting gamestate
		GameState originalGameState = game.action(action);
		
		CardName topOfDiscard = originalGameState.getFaceUpDiscard();
			
		//Now execute another action drawing from the discard pile and putting that card in position three in 
		//Bill's hand
		Action nextAction = new Action(ActionName.DRAW, PileName.DISCARD, PileName.THREE, "Bill", "Bill",null);
		GameState updatedGameState = game.action(nextAction);
		
		//get the card at position three in bills hand and assert that it equals the top of discard variable.
		CardName[] billhand = updatedGameState.getPlayerHands().get("Bill");
		CardName cardToTest = billhand[3];
		
		assertTrue("Unexpected card found in player's hand while attempting draw operation from deck.", cardToTest ==topOfDiscard);
	}
	
	/**
	 * TestSwapOperation performs a simple swap between players hands, and tests to ensure that the cards
	 * in the players hands within the game state match the expected values. 
	 */
	@Test
	public void testSwapOperation()
	{	
		String sourcePlayer = "Steve";
		PileName sourcePile = PileName.ONE;
		String targetPlayer = "Bill";
		PileName targetPile = PileName.ONE;
		
		//generate a game object
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
		//generate an action object specifying a swap action target Steve's hand at position one, 
		//and bill's hand at position one. 
		Action swapAction = new Action(ActionName.SWAP, sourcePile, targetPile, sourcePlayer, targetPlayer, null);
		//get the current gamestate and get the id's of the source and target cards of our action.
		GameState originalGameState = game.generateCurrentGameState();
		
		CardName sourceCard = originalGameState.getPlayerHands().get(sourcePlayer)[PileName.getIntegerValue(sourcePile)];
		CardName targetCard = originalGameState.getPlayerHands().get(targetPlayer)[PileName.getIntegerValue(targetPile)];
		
		//Perform the action
		GameState alteredGameState = game.action(swapAction);
		//recover the new cards from the current gamestates
		CardName sourcePlayersNewCard = alteredGameState.getPlayerHands().get(sourcePlayer)[PileName.getIntegerValue(sourcePile)];
		CardName targetPlayersNewCard = alteredGameState.getPlayerHands().get(targetPlayer)[PileName.getIntegerValue(targetPile)];
		
		assertTrue("Swap operation did not perform as expected", sourceCard == targetPlayersNewCard && targetCard == sourcePlayersNewCard);
		
	}
	
	@Test
	public void testScoreGeneration()
	{
		String[] players = new String[] {"Steve", "Bill", "Fred"};
		GameObject game = new GameObject(GameType.ROUNDS, players);
		GameState state = game.generateCurrentGameState();
				
		HashMap<String, Integer> scores = state.getRoundScores();
		
		for(String name: players)
		{
			int total = 0;
			CardName[] hand = state.getPlayerHands().get(name);
			for(CardName card: hand)
			{
				total += CardName.getNumericScore(card);
			}
			
			assertTrue("PlayerHand object computed unexpected score value for hand.", total == scores.get(name));
		}
		
	}
	
	
	
}
