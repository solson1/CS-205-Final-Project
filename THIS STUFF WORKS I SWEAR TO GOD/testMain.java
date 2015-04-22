//AiObject test enviornment. 
//			
//										Test log
//
//1. Null pointer exception, Solution: testing hashmaps were empty, so values were null,
//which caused fuckery

//2. Another null pointer exception. Problem was me not creating the aiHand array from the playerhands hashmap properly,
//specifically I was using the wrong key.
//AiObj compiles at this point.
				
import java.util.*;

public class testMain
{
	public static void main(String[] args)
	{
		CardName[] player1 = new CardName[4];
		CardName[] player2 = new CardName[4];
		CardName[] player3 = new CardName[4];
		
		player1[0] = CardName.ONE;
		player1[1] = CardName.TWO;
		player1[2] = CardName.THREE;
		player1[3] = CardName.FOUR;
		
		player2[0] = CardName.FIVE;
		player2[1] = CardName.TWO;
		player2[2] = CardName.SIX;
		player2[3] = CardName.FOUR;
		
		player3[0] = CardName.ONE;
		player3[1] = CardName.SEVEN;
		player3[2] = CardName.THREE;
		player3[3] = CardName.EIGHT
				;
		/*HashMap<String, String > h = new HashMap<String, String>(){{
        put("a","b");
    }};*/
		
		 final HashMap<String, CardName[]> playerHands = new HashMap<String, CardName[]>();
		 
		 playerHands.put("player1", player1);
		 playerHands.put("player2", player2);
		 playerHands.put("player3", player3);
		 
		 CardName faceUpDiscard = CardName.THREE;
		 CardName topOfDeck = CardName.FIVE;
		
		
		//we try and create the aiObj. Brace yourselves.
		
		
		GameState state = new GameState(playerHands, new HashMap<String, Integer>(), faceUpDiscard, topOfDeck);
		
		System.out.println("GameState tests");
		
		
		System.out.print( "Top of deck: " + state.getTopOfDeck().toString() + "\n\n"); //works
		System.out.print("Face up discard: " + state.getFaceUpDiscard().toString() + "\n\n"); //works
		System.out.print("Player 1's first card: " + state.getPlayerHands().get("player1")[0] + "\n\n"); //works
	
		
		
		//////////////////////////////////////////////////////////////////////
		
		//testing aimemory method. it works
		//player1 = aiMemory(player1, 0.3);
		//System.out.print(player1[0].toString() + "\n\n\n\n\n");
		
		////////////////////////////////////////////////////////////////////////
		//Testing aiobj
		
		AiObj ai = new AiObj(state);
		ai.start("player1");
		Action tits = ai.getAction();
		
		System.out.println("something somethging i dont fuciubg know bitch: " + tits.getActionName().toString());
		
	}
	
	
	
	
}	
	/*public static CardName[] aiMemory(CardName[] aiHand, double forgetRate)
	{
		Random rand = new Random();
		
		for(int i = 0; i < 4; i++)
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
	
	
	
	
} */