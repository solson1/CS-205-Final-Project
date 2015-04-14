package utilityObjects;

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
	
	
	
}
