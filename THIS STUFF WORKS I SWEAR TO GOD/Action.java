/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
//package utilityObjects;

/**
 * The Action class is designed to communicate all the necessary information 
 * which represents a player or AI action to the game object. These objects are created 
 * within the GUI from the player's actions, or in the AI object by the AI actions. 
 * @author Ian
 *
 */
public class Action {
	private ActionName actionName;
	
	private PileName sourcePileName;
	private PileName targetPileName;
	private String sourcePlayerName;
	private String targetPlayerName;
	
	
	private CardName cardName;
	
	public Action(ActionName action, PileName sourcePile, PileName targetPile, String sourcePlayer, String targetPlayer, CardName card)
	{
		this.actionName = action;
		this.sourcePileName = sourcePile;
		this.targetPileName = targetPile;
		this.sourcePlayerName = sourcePlayer;
		this.targetPlayerName = targetPlayer;
		this.cardName = card;
	}

	/**
	 * @return the actionName
	 */
	public ActionName getActionName() {
		return actionName;
	}

	/**
	 * @return the sourcePileName
	 */
	public PileName getSourcePileName() {
		return sourcePileName;
	}

	/**
	 * @return the targetPileName
	 */
	public PileName getTargetPileName() {
		return targetPileName;
	}

	/**
	 * @return the sourcePlayerName
	 */
	public String getSourcePlayerName() {
		return sourcePlayerName;
	}

	/**
	 * @return the targetPlayerName
	 */
	public String getTargetPlayerName() {
		return targetPlayerName;
	}

	/**
	 * @return the cardName
	 */
	public CardName getCardName() {
		return cardName;
	}

	
	

}
