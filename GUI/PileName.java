//package utilityObjects;

public enum PileName {
	ZERO, ONE, TWO, THREE, DECK, DISCARD;
	
	
	/**
	 * getIntegerValue returns the integer value of the Pile name for ZERO through THREE.
	 * Deck and Discard Values are equal to -1. 
	 * @param name
	 * @return
	 */
	public static int getIntegerValue(PileName name)
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
		default:
			return -1;
		}
	}
}
