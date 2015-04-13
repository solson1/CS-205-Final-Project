package utilityObjects;

import java.util.AbstractQueue;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
	private HashMap<String, CardName[]> playerHands;
	
	public GameObject(GameType inType, String... players)
	{
		this.type = inType;
		this.playerNames = players;
		this.playerHands = new HashMap<String, CardName[]>(); 
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
		//Cases to Handle: 
		//1.) We're performing an operation on a player's 'own' hand.
		//		ie, drawing from a pile and swapping within his own hand.
		if(action.getSourcePlayerName().equalsIgnoreCase(action.getTargetPlayerName()))
		{
			this.updatePlayerHand(action.getSourcePlayerName(), action.getTargetPileName(), action.getCardName());
		}
	
		//generate a GameState Object to return.
		return this.generateCurrentGameState();
	}
	
	/**
	 * GenerateGameState generates a gamestate object reflecting the current state of gameplay
	 * and returns it.
	 * @return
	 */
	public GameState generateCurrentGameState()
	{
		//Default to returning zeros for the current scores
		int defaultScore = 0;
		
		HashMap<String, CardName[]> handsCopy = this.deepCopyPlayerHands();
		
		CardName topOfDiscard = null;
		
		try
		{
			topOfDiscard = this.discard.pop();
		}
		catch(EmptyStackException e)
		{
			//Ignore, topOfDiscard will be null;
		}
		
		//create deep copy functionality for the player hands array and the hashmap of player names to hands
		return new GameState(handsCopy, defaultScore, defaultScore, topOfDiscard); 
	}
	
	private HashMap<String, CardName[]> deepCopyPlayerHands()
	{
		HashMap<String, CardName[]> handsCopy = new HashMap<String, CardName[]>();
		
		for(String player: this.playerNames)
		{
			//Create a new Hand array
			CardName[] newHand = new CardName[NUM_CARDS_PER_HAND];
			//Copy the hand array
			System.arraycopy(this.playerHands.get(player), 0, newHand, 0, NUM_CARDS_PER_HAND);
			//Add that hand array to the new hashmap.
			handsCopy.put(player, newHand);
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
	private void updatePlayerHand(String targetPlayerName, PileName pile, CardName inputCard)
	{
		//Get the pertinent player's hand
		CardName[] hand = this.playerHands.get(targetPlayerName);
		//Switch on the specified pileName, executing the updateDiscardAndHand method for the 
		//appropriate indexd
		switch(pile)
		{
		case ONE:
			updateDiscardAndHand(hand, 0, inputCard);
			break;
		case TWO:
			updateDiscardAndHand(hand, 1, inputCard);
			break;
		case THREE:
			updateDiscardAndHand(hand, 2, inputCard);
			break;
		case FOUR:
			updateDiscardAndHand(hand, 3, inputCard);
			break;
		case DECK:
			//Do nothing
			break;
		case DISCARD:
			//Do Nothing
			break;
		default:
			break;
		}
	}
	
	/**
	 * updateDiscardAndHand inserts the inputCard CardName into the specified index of the hand CardName[] array, 
	 * and pushes the old value onto the discard stack. 
	 * @param hand
	 * @param index
	 * @param inputCard
	 */
	private void updateDiscardAndHand(CardName[] hand, int index, CardName inputCard)
	{
		this.discard.push(hand[index]);
		
		hand[index] = inputCard;
	}
	
	/**
	 * loadPlayerHand gets cards from the top of the deck, and 
	 * assigns them to a new CardName[4] array. Power cards 
	 * "disappear" at this stage, if they are drawn. 
	 * @return
	 */
	private CardName[] generatePlayerHand()
	{
		//generate a new array to be filled.
		CardName[] hand = new CardName[NUM_CARDS_PER_HAND];
		//Fill the hand with NUM_CARDS_PER_HAND cards
		
		for(int i = 0; i < NUM_CARDS_PER_HAND; i++)
		{	//make sure that the card is not a powerCard, and if not, add it to the hand at position "i". 
			while(deck.peek() == CardName.DRAW_TWO || deck.peek() == CardName.SWAP || deck.peek() == CardName.PEEK)
			{
				deck.pop();
			}
			hand[i] = deck.pop();
		}
		return hand;
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
	
}