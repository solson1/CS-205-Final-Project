package utilityObjects;

public class AIDummy {

	private GameState state;
	private String name;
	private int numPlayers;
	
	public AIDummy(GameState state, String myName, int numPlayers)
	{
		this.state = state;
		this.name = myName;
		this.numPlayers = numPlayers;
		
	}
	
	public Action aiAction(GameState currentState)
	{
		return null;
	}
}
