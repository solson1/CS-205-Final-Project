/**
 * Ian Foertsch
 * CS205 Semester Project
 * Ratatat cat game
 */

//package utilityObjects;

import java.util.AbstractQueue;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * The GameObject contains all of the game logic associated with the Ratatat game
 * @author Ian 
 *
 */
public class GameObject {

	//Class Parameters
	private final int NUM_CARDS_PER_HAND = 4;
	private final int NUM_BASE_CARDS = 4;
	private final int NUM_NINES = 9;
	private final int NUM_POWER_CARDS = 3;
	
	
	//Class fields
	private Stack<CardName> deck;
	private Stack<CardName> discard;
	private GameType type;
	private String[] playerNames;
	private HashMap<String, PlayerHand> playerHands;
	
	public GameObject(GameType inType, String... players)
	{
		this.type = inType;
		this.playerNames = players;
		this.playerHands = new HashMap<String, PlayerHand>(); 
		this.deck = this.generateShuffledDeck();
		this.discard = new Stack<CardName>();
		
		//Next, load cards from the deck to the player's hands
		for(String name: this.playerNames)
		{
			this.playerHands.put(name, this.generatePlayerHand());
		}
	}
	
	/**
	 * The action method is the interface by which all game actions
	 * are performed. the method accepts an action object, performs changes on 
	 * the internal state of the game, and returns a new GameState object
	 * reflecting those changes. 
	 * @param action
	 * @return
	 */
	public GameState action(Action action)
	{
		
		switch(action.getActionName())
		{
		case DRAW:
			this.draw(action);
			break;
		case SWAP:
			this.swap(action);
			break;
		case ENDROUND:
			break;
		}
		//generate a GameState Object to return.
		return this.generateCurrentGameState();
	}
	/**
	 * The swap method performs the swap operation specified by the action object on the game object
	 * @param action
	 */
	private void swap(Action action)
	{
		//get the source and target playerNames and pileNames
		String sourcePlayer = action.getSourcePlayerName();
		String targetPlayer = action.getTargetPlayerName();
		PileName sourcePile = action.getSourcePileName();
		PileName targetPile = action.getTargetPileName();
		
		//insert the card from the source into the target, and store the output
		CardName sourceCard = this.playerHands.get(sourcePlayer).getCardAtIndex(PileName.getIntegerValue(sourcePile));
		CardName cardFromTarget = this.playerHands.get(targetPlayer).insertCard(sourceCard, PileName.getIntegerValue(targetPile));
		
		//insert the "output" from the previous operation into the source
		this.playerHands.get(sourcePlayer).insertCard(cardFromTarget, PileName.getIntegerValue(sourcePile));
		//discard the output from the last operation
	}

	/**
	 * draw handles the draw action, accepting the action object containing all the infromation required to perform 
	 * the draw operation. 
	 * @param action
	 */
	private void draw(Action action)
	{
		//Get the source pile.
		PileName source = action.getSourcePileName();
		//switch on the source name. There are only two possible sources in a draw operation, the deck and discard piles
		//will need to add additional handling for powercards
		CardName newCard;
		switch(source)
		{
		case DECK:
			newCard = this.deck.pop();
			this.updatePlayerHand(action.getTargetPlayerName(), action.getTargetPileName(), newCard);
			break;
		case DISCARD:
			try
			{
				newCard = this.discard.pop();
				this.updatePlayerHand(action.getTargetPlayerName(), action.getTargetPileName(), newCard);	
			}
			catch(EmptyStackException e)
			{
				//Ignore, if top of discard is null, then we will have failed to perform an action.
			}
			break;
		default:
			//Do nothing, because we would never "draw" from anywhere other than the deck or the discard pile. 
			break;
		}
	}
	
	/**
	 * GenerateGameState generates a gamestate object reflecting the current state of gameplay
	 * and returns it.
	 * @return
	 */
	public GameState generateCurrentGameState()
	{
		HashMap<String, Integer> currentScores = new HashMap<String,Integer>();
		//compute the current scores for each player by calling the compute score method for each playerHandobject.
		for(String name: this.playerNames)
		{
			currentScores.put(name, new Integer(this.playerHands.get(name).getScore()));
		}
		
		HashMap<String, CardName[]> handsCopy = this.deepCopyHandsArray();
		//get the top of the discard pile, handling the case with the discard pile is empty.
		CardName topOfDiscard = CardName.SEVEN;
		
		{
			//topOfDiscard = this.discard.peek();
		}
		//catch(EmptyStackException e)
		{
			//Ignore, topOfDiscard will be null;
		}
		//Get the top of the deck
		CardName topOfDeck = this.deck.peek();
		//create deep copy functionality for the player hands array and the hashmap of player names to hands
		return new GameState(handsCopy, currentScores, topOfDiscard, topOfDeck); 
	}
	
	private HashMap<String, CardName[]> deepCopyHandsArray()
	{
		HashMap<String, CardName[]> handsCopy = new HashMap<String, CardName[]>();
		
		for(String name: this.playerNames)
		{
			CardName[] newHand = this.playerHands.get(name).getDeepCopyHand();
			handsCopy.put(name, newHand);
		}
		return handsCopy;
	}
	
	/**
	 * updatePlayerhand accepts a PileName value specifying the target pile to modify and 
	 * the input card. The method sets the "pile" value to the input card, and pushes 
	 * the prior value of that pile to the discard pile.
	 * @param targetPlayerName describes the target player upon which the action is being performed.
	 * @param pile describes which pile in the specified player's hand the card is being pushed to
	 * @param inputCard specifies the input card.
	 */
	private void updatePlayerHand(String targetPlayerName, PileName targetPileName, CardName inputCard)
	{
		//Get the pertinent player's hand
		PlayerHand hand = this.playerHands.get(targetPlayerName);

		insertAndDiscard(hand, inputCard, PileName.getIntegerValue(targetPileName));
	}
	
	/**
	 * insertAndDiscard inserts the inputCard CardName into the specified index of the hand CardName[] array, 
	 * and pushes the old value onto the discard stack. 
	 * @param hand
	 * @param index
	 * @param inputCard
	 */
	private void insertAndDiscard(PlayerHand hand, CardName inputCard, int index)
	{
		//Use the insertCardMethod to insert a card into the hand and store the old cardname
		//in the cardFromHand variable.
		CardName cardFromHand = hand.insertCard(inputCard, index);
		
		//Push the card from the hand onto the discardpile
		this.discard.push(cardFromHand);
	}
	
	/**
	 * loadPlayerHand gets cards from the top of the deck, and 
	 * assigns them to a new CardName[4] array. Power cards 
	 * "disappear" at this stage, if they are drawn. 
	 * @return
	 */
	private PlayerHand generatePlayerHand()
	{
		//generate a new array to be filled.
		CardName[] hand = new CardName[NUM_CARDS_PER_HAND];
		//Fill the hand with NUM_CARDS_PER_HAND cards
		
		for(int i = 0; i < NUM_CARDS_PER_HAND; i++)
		{	//make sure that the card is not a powerCard, and if not, add it to the hand at index "i". 
			while(deck.peek() == CardName.DRAW_TWO || deck.peek() == CardName.SWAP || deck.peek() == CardName.PEEK)
			{
				this.discard.push(deck.pop());
			}
			hand[i] = deck.pop();
		}
		return new PlayerHand(hand);
	}
	
	/**
	 * generateShuffledDeck returns a full, randomly shuffled AbstractQueue of CardNames
	 * @return
	 */
	private Stack<CardName>generateShuffledDeck()
	{
		//generate a list of cardnames
		LinkedList<CardName> orderedDeck = this.generateOrderedListOfCards();
		
		//execute the shuffle method.
		Collections.shuffle(orderedDeck);
		
		
		//First generate an AbstractQueue
		Stack<CardName> deck = new Stack<CardName>();
		
		//load the cardnames in sequence to the abstract queue.
		deck.addAll(orderedDeck);
		//return the newly instantiated queue.
		
		return deck;
	}
	
	private LinkedList<CardName> generateOrderedListOfCards()
	{
		//Create the ordered deck list
		LinkedList<CardName> orderedDeck = new LinkedList<CardName>();
		
		//Generate four each of cards 0-8
		for(int i = 0; i < NUM_BASE_CARDS; i++)
		{
			orderedDeck.add(CardName.ZERO);
			orderedDeck.add(CardName.ONE);
			orderedDeck.add(CardName.TWO);
			orderedDeck.add(CardName.THREE);
			orderedDeck.add(CardName.FOUR);
			orderedDeck.add(CardName.FIVE);
			orderedDeck.add(CardName.SIX);
			orderedDeck.add(CardName.SEVEN);
			orderedDeck.add(CardName.EIGHT);
		}
		//Add nine "NINE" cards
		for(int i = 0; i < NUM_NINES; i++)
		{
			orderedDeck.add(CardName.NINE);
		}
		//Add three of each powerCard
		for(int i = 0; i < NUM_POWER_CARDS; i++)
		{
			orderedDeck.add(CardName.DRAW_TWO);
			orderedDeck.add(CardName.PEEK);
			orderedDeck.add(CardName.SWAP);
		}
		
		return orderedDeck;
	}
	
	/**
	 * The private inner class playerHand represents a single player's hand of four cards. 
	 * The methods specify inserting and removing cards from the hand. 
	 * @author Ian
	 *
	 */
	private class PlayerHand
	{
		private CardName[] hand;
		
		private PlayerHand(CardName[] inHand)
		{
			this.hand = inHand;
		}
		
		private int getScore()
		{
			int total = 0;
			for(CardName card: this.hand)
			{
				total += CardName.getNumericScore(card);
			}
			
			return total;
		}
		
		/**
		 * InsertCard assigns inserts the "inputCard" value to the specified location in the hand, 
		 * and returns the CardName value that was originally contained at that index. 
		 * @param inputCard
		 * @param index
		 * @return
		 */
		private CardName insertCard(CardName inputCard, int index)
		{
			CardName outputCard = this.hand[index];
			this.hand[index] = inputCard;
			
			return outputCard;
		}
		
		/**
		 * getHand returns a copy of the cardname[] which describes the contents of the player's hand. 
		 * @return
		 */
		private CardName[] getDeepCopyHand()
		{
			CardName[] copyOfHand = new CardName[NUM_CARDS_PER_HAND];
			
			System.arraycopy(hand, 0, copyOfHand, 0, NUM_CARDS_PER_HAND);
			
			return copyOfHand;
		}
		
		private CardName getCardAtIndex(int index)
		{
			return this.hand[index];
		}
		
	}
	
}