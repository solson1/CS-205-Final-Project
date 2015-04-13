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
		
		System.out.println("woops");
	}
	
	/**
	 * testSimpleSwap performs a simple swap operation within one player's hand, and queries the current gamestate 
	 * to ensure that the operation has been performed and is reflected in the current state. 
	 */
	@Test
	public void testSimpleSwap()
	{
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
		
		Action action = new Action(ActionName.DRAW, PileName.ONE, PileName.ONE, "Steve", "Steve", CardName.NINE);
		
		GameState state = game.action(action);
		
		CardName[] steveHand = state.getPlayerHands().get("Steve");
		
		assertTrue("expected Player's hand to contain NINE", steveHand[0] == CardName.NINE );
		
	}
	
	/**
	 * Test the assignment to the discard pile of the current contents of the target "pile" within a player's hand.
	 */
	public void testAutomaticAssignmentToDiscardPile()
	{
		//Generate a new Game
		GameObject game = new GameObject(GameType.ROUNDS, "Steve", "Bill", "Fred");
		//Get the current state of the game, and obtain the contents of "steve"'s hand at position one, 
		GameState originalState = game.generateCurrentGameState();
		
		//Obtain a copy of steve's hand, and get the first (zeroth) card in the hand.
		CardName[] steveHand = originalState.getPlayerHands().get("Steve");	
		CardName compareThisLater = steveHand[0];
		
		//perform some swap operation on steve's hand
		Action action = new Action(ActionName.DRAW, PileName.ONE, PileName.ONE, "Steve", "Steve", CardName.TWO);
		//and obtain the current state,
		GameState state = game.action(action);
	
		//Get the current top of the discard pile
		CardName topOfDiscard = state.getFaceUpDiscard();
		
		//and assert that that value == the compareThisLaterValue
		assertTrue("Unexpected Value found on top of discard pile following simple one "
				+ "player swap operation", compareThisLater == topOfDiscard); 
	}
	
	
	
}
