//AiObject
//Version 1.0, April 21 2015
//
//This is the AiObj object. It represents the ai that will play against the player in the rat a tat cat game.
//Within the greater scheme of this implementation of the rat a tat game, it will recieve a gamestate object,
//and upon execution it will return an action object, within which all the actions that the ai object 
//has decided are in its best interest are stored.
//With the exception of the Peek pwer card, the aiObj doesnt actually do anything, it just decides what would be i
//n its best interest to do, and then create an action object that tells the wrapper that it would like to do a certain
//action.

import java.util.*;


public class AiObj {
	
	private HashMap<String, CardName[]> playerHands;
	private HashMap<String, Integer> roundScores;
	private CardName faceUpDiscard;
	private CardName topOfDeck;
	
	public double forgetRate;
	
	
	Action returnAction;
	//We use this to set up the ai's representation of what it thinks
	//other players have in their hand.
	private boolean firstTurn = true;
	
	//representation of players own hand. We fill the array in the object constructor.
	private CardName[] aiHand = new CardName[4]; 
	
	
	//The constructor method. When initiated, aiObj takes a gamestate as input, which contains all of the relavent 
	//information needed for the ai to play the game, such as what cards other people have, etc.

	AiObj(GameState game)
	{
		System.out.println("RUNNING THE CONSTRUCTOR");
		
		
		playerHands = game.getPlayerHands();
		roundScores = game.getRoundScores();
		faceUpDiscard = game.getFaceUpDiscard();
		topOfDeck = game.getTopOfDeck();
		
		firstTurn = false;
		
		aiHand = playerHands.get("player3"); //im not sure what you call the ai's hand in the playerHands hash, gonna guess its self.
		
		//System.out.println("calling playstageone from constructor");
		//playStageOne(playerHands, faceUpDiscard, topOfDeck, aiHand);
		
		//the loop that accepts new gamestate objects. I have no idea what I'm doing.
		//while()
	}
	
	//mini constructor, updates things when we get a new constructor.
	public void update(GameState state)
	{
		this.playerHands = playerHands;
		this.roundScores = roundScores;
		this.faceUpDiscard = faceUpDiscard;
		this.topOfDeck = topOfDeck;
	}
	
	public void start(String key)
	{
		playStageOne(this.playerHands, this.faceUpDiscard, this.topOfDeck, this.playerHands.get(key));
	}
	
	
	public Action getAction()
	{
		System.out.println("calling get return action\n");
		return this.returnAction;
		
	}
	
	//this method is a simulation of ai's memory. Its where it tries to keep track of the cards that it has
	//The basic idea is that we set some loss chance, and if the rand that we create returns a value that meets the random chance,
	//we change what the ai thinks it has. 
	//NOTE: I do not know/am too lazy to implement the ai forgetting what other people have
	public CardName[] aiMemory(CardName[] aiHand, double forgetRate)
	{
		System.out.println("RUNNING THE AIMEMORY");
		
		
		Random rand = new Random();
		
		for(int i = 0; i < 3; i++)
		{	
			double chance = rand.nextDouble();
			if(chance < forgetRate)
			{
				if(CardName.getNumericScore(aiHand[i]) > 5)
				{
					aiHand[i] = CardName.SEVEN;
				}
				if(CardName.getNumericScore(aiHand[i]) < 5)
				{
					aiHand[i] = CardName.THREE;
				}
			}
		}
		return aiHand;
	}
	
	public void setReturnAction(Action returnAction)
	{
		this.returnAction = returnAction;
	}
	
	
	//playStageOne is what first happens when we go to play a turn. 
	//It, along with a few supporting methods, figures out whether we should
	//draw from the discard pile or from the draw deck.
	//This is the meat and potatoes of the AiObj class, its where everything begins and ends.
	//Think of it as a main method.
	//After everything is said and done, it passes all relevant information to the returnAction method,
	//which creates and returns an action object to the wrapper.
	//stage one of the decision tree.
	public Action playStageOne(HashMap<String, CardName[]> playerHands, CardName faceUpDiscard, CardName topOfDeck, CardName[] aiHands)
	{
		System.out.println("RUNNING STAGEONE");
		
		if(drawOrDiscard(faceUpDiscard, topOfDeck) == 1)
		{
			return drawFromDiscard( faceUpDiscard, aiMemory( aiHands, 0.3)); //this is where we first simulate forgetting
		}
		if(drawOrDiscard(faceUpDiscard, topOfDeck) == 2)
		{
			return drawFromDeck(topOfDeck, aiMemory(aiHands, 0.3), playerHands); //here too
		}
		System.out.println("Aww shizz we dun goofed");
		return null;
		
		//testing 
		
	}
	
	//This method figures out whether it is in the AI's best interest to draw a card from the draw deck or
	//the discard pile. Obviously it is an essential method, as well as the most important piece of simulated 
	//reasoning within the ai object.
	//we call the which is better method to compare the values of the faceupdiscard and topofdeck cards, 
	//figuring out which is in our best interest.
	public int drawOrDiscard(CardName faceUpDiscard, CardName topOfDeck)
	{
		
		System.out.println("RUNNING THE DRAWORDISCARD METHOD");
		CardName better = whichIsBetter(topOfDeck, faceUpDiscard);
		if (CardName.getNumericScore(better) == CardName.getNumericScore(faceUpDiscard))
			return 1;
		else
			return 2;

	}
	
	//the whichisbetter takes two cards and decides which is better, in terms of total score.
	//we make heavy use of the CardName.getnumericScore function, which returns the associated value
	//of the card.
	public CardName whichIsBetter(CardName card1, CardName card2)
	{
		System.out.println("RUNNING WHICHISBETTER METHOD");
		{
			int x = CardName.getNumericScore(card1); //lol does this work?
			int y = CardName.getNumericScore(card2);
		
			//remember lower is always better.
			if (x < y)
				return card1;
			if (x > y)
				return card2;
			if ( x==y )
				return card1; //totally arbitrary, since it doesnt really matter
	
			return card1; //arbitrary default. This line will never execute, but it doesnt really matter if it does.
		}
	}
	
	//draw from discard function, calls the create action method, which creates an action object and returns it
	//Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
	public Action drawFromDiscard(CardName faceUpDisCard, CardName[] aiHands)
	{
		System.out.println("RUNNING THE CONSTRUCTOR");
		if(faceUpDiscard == CardName.SWAP)
		{
			swap(playerHands, aiHands);
		}
		if(faceUpDiscard == CardName.PEEK)
		{
			peek(playerHands, aiHands);
		}
		if(faceUpDiscard == CardName.DRAW_TWO)
		{
			drawTwo();
		}
		else
		{ //not a power card
			//this monster of an if statement figures out if topofdiscard is better than our worst card. If so, we replace it.
			if(CardName.getNumericScore(aiHands[getWorstCard(aiHands)]) > CardName.getNumericScore(faceUpDiscard)) 
			System.out.println("checking if face up discard is worth picking \n");
																																			
			{
				//we replace the card we drew with our worst card
				//create an action object that tells the wrapper that tells the gameobj to do this.
				//Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
				
				return createReturnAction(ActionName.DRAW, PileName.DISCARD, fromIntToPileName(getWorstCard(aiHands)), "self", "self", null);
				
			}
		}
		return null;
		
		
	}
	
	public Action drawFromDeck(CardName topOfDeck, CardName[] aiHands, HashMap<String, CardName[]> playerHands)
	{
		
		System.out.println("RUNNING THE DRAW FROM DECK METHOD");
		
		if(topOfDeck == (CardName.SWAP))
		{
			swap(playerHands, aiHands);
		}
		if(topOfDeck == (CardName.PEEK))
		{
			peek(playerHands, aiHands);
		}
		if(topOfDeck == (CardName.DRAW_TWO))
		{
			drawTwo();
		}
		else
		{ //not a power card
			
			if(CardName.getNumericScore(aiHands[getWorstCard(aiHands)]) > CardName.getNumericScore(faceUpDiscard)) 
				
			{
				//we replace the card we drew with our worst card
				//create an action object that tells the wrapper that tells the gameobj to do this.
				//Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
				return createReturnAction(ActionName.DRAW, PileName.DECK, fromIntToPileName(getWorstCard(aiHands)), "self", "self", null);
			}
		}
		return null;
	}
	
	//getWorstcard gets the worst card in some hand. We only use it in this class 
	//to get the lowest card in our own hand. A very usefull method, we use it to figure out which of our 
	//cards to replace with a better one when we have the chance to do so. Makes use of the CardName getNumericScore() 
	//method
	public int getWorstCard(CardName[] aiHand)
	{
		System.out.println("RUNNING THE GETWORSTCARD");
		int worstCardPos = 0;
		for(int i = 0; i < 3; i++)
		{
			if(CardName.getNumericScore(aiHand[i]) > CardName.getNumericScore(aiHand[worstCardPos]))
			{
				worstCardPos = i;
			}
		}
		return worstCardPos;
	}
	
	//implements swap power card. The getBestPlayerCard returns a string with the key and position of the best card
	//in the hash map. I return both in a string because java cant do multiple return types because
	//it is a wretched language.
	public Action swap(HashMap<String, CardName[]> playerHands, CardName[] aiHands)
	{
		System.out.println("RUNNING THE SWAPMETHOD");
		
		CardName worstAi =  aiHands[getWorstCard(aiHands)];
		
		String best = getBestPlayerCard(playerHands);
		String index = best.substring(best.length() - 1);
		int indexInt = Integer.parseInt(index);
		String key = best.substring(0, best.length()-1);
		
		//Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
		return createReturnAction(ActionName.SWAP, fromIntToPileName(indexInt), fromIntToPileName(getWorstCard(aiHands)), key, "self", playerHands.get(key)[indexInt]);
	}
	//WE NEED TO TALK ABOUT SWITCHING CARDS FOR THINGS LIKE SWAP, RIGHT NOW IT CANT WORK.
	
	
	public boolean isItAPowerCard(CardName card)
	{
		if((card == CardName.SWAP) || card == CardName.PEEK || card == CardName.DRAW_TWO )
		{
			return false;
			
		}else
		{
			return true;
		}
		
	}
	
	//the implementation of the ai's thought process when it goes to "run" a swap card
	//this functionality cannot really be implemented properly due to the constraints of the wrapper class,
	//the ai has no way of seeing what the second draw card is. So I'm just doing it randomly.
	public void drawTwo()
	{
		System.out.println("RUNNING THE DRAWTWOMETHOD");
		
		createReturnAction(ActionName.DRAW_TWO, null, null,null,null,null);
	}
	
	public void peek(HashMap<String, CardName[]> playerHands, CardName[] aihand)
	{
		Random rand = new Random();
		int i = rand.nextInt((3 - 0) + 1);
		this.aiHand[i] = playerHands.get("player3")[i]; 
		
	}
	
	//creates an action object and sends it back to the wrapper
	//NEEDS WORK
	//Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
	public Action createReturnAction(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
	{
		System.out.println("RUNNING THE CREATERETURNACTION");
		
		Action returnAction = new Action(action, sourcePile, targetPile, sourcePlayer, targetPlayer, card);
		
		//ignore this, just for testing.
		System.out.println("TIIIIIIIITS \n");
		System.out.println("Action name returned: " + returnAction.getActionName().toString() + "\n");
		System.out.println("Source Pile: " + returnAction.getSourcePileName().toString() + "\n");
		System.out.println("Target Pile: " + returnAction.getTargetPileName().toString() + "\n");
		System.out.println("Source player: " + returnAction.getSourcePlayerName() + "\n");
		System.out.println("Target player: " + returnAction.getTargetPlayerName() + "\n");
		
		setReturnAction(returnAction); //LOL WTF IS THIS SHIT PLEASE NO ONE LOOK AT THIS CODE LAWLLAWLAWLALWLAWLAS:DLMAS:FLKLgkjwelgkjaw'elgfJWELK GJAWLERGJK AL'ERJ 
		return returnAction;
	}
	
	//this method looks at all other cards it thinks other players have and figures out which
	//of those cards is best.
	public String getBestPlayerCard(HashMap<String, CardName[]> playerHands)
	{
		System.out.println("RUNNING THE GETBESTPLAYERCARDMETHOD");
		
		int best = 10;
		int bestVal = 99;
		String bestKey = "";
		
		for (Map.Entry<String, CardName[]> entry : playerHands.entrySet())
		{
			for (int i = 0; i < 4; i++)
			{
				if(CardName.getNumericScore(entry.getValue()[i]) < bestVal) 
				{
					best = i;
					bestKey = entry.getKey();
				}
			}
		}
		return bestKey + Integer.toString(best); 
	}
	
	//changes a int position of a card to a pilename enum type
	public PileName fromIntToPileName(int pos)
	{
		System.out.println("RUNNING THE FROMINTOPILE");
		
		if(pos == 1)
			return PileName.ZERO;
		if(pos == 2)
			return PileName.ONE;
		if(pos == 3)
			return PileName.TWO;
		if(pos == 4)
			return PileName.THREE;
		
		return PileName.ZERO; //just so it doesnt complain. This will never be exectuted.
	}
	
	public void tits(HashMap<String, CardName[]> playerHands)
	{
		CardName x = playerHands.get("player1")[0];
	}
	
	//playerHands.get("Steven")[2] = card

}//aiObj
