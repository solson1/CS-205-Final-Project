package utilityObjects;

public class GameObjectDummy implements Game {
	private int numPlayer;
	private GameType type;
	
	
	public GameObjectDummy(int numPlayer, GameType type)
	{
		this.numPlayer = numPlayer;
		this.type = type;
	}
	
	public GameState action(Action action)
	{
		return null;
	}
	
	public GameState getCurrentState()
	{
		return null;
	}

}
