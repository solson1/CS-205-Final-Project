package utilityObjects;

public class AIDummy {
	
	private int numPlayers;
	private GameType type;
	
	public AIDummy(int numPlayers, GameType type)
	{
		this.numPlayers = numPlayers;
		this.type = type;
	}
	
	public Action aiAction(GameState currentState)
	{
		return null;
	}
}
