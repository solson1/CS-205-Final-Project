/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

/**
 * The CardName enumerated datatype represents all the possible cards
 * in the rat-a-tat-cat card game. These range from 2-9, with additional 
 * cards for the swap, peek and draw-2 cards. 
 * @author Ian
 */
public enum CardName {
	ZERO, ONE, TWO, THREE, FOUR, 
	FIVE, SIX, SEVEN, EIGHT, NINE, 
	DRAW_TWO, PEEK, SWAP;
	
	/**
	 * returns the numeric score associated with each card. Defaults to returning zero 
	 * for powercards, although currently this represents an error. 
	 */
	public static int getNumericScore(CardName name)
	{
		switch(name)
		{
		case ZERO:
			return 0;
		case ONE:
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		default:
			return 0;
		}
	}
}
