package utilityObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

public class GameWrapperTest {
	
	private String playerName = "WrapperTestingPlayer";
	private GameType type = GameType.ROUNDS;
	private String[] playerList = new String[] { playerName, "otherPlayer", "thirdPlayer"};
	
	@Test
	public void testConstruction()
	{
		try
		{
			GameWrapper wrapper = new GameWrapper();
		}
		
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testLogin()
	{
		try
		{
			GameWrapper wrapper = new GameWrapper();	
			wrapper.login(playerName);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGameCreation()
	{
		try
		{
			GameWrapper wrapper = new GameWrapper();	
			wrapper.login(playerName);
			
			GameState state = wrapper.startNewGame(type, playerList);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
}
