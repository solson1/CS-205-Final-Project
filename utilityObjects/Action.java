/**
 * Author: Ian Foertsch
 * Date: 3/12/15
 * Project: Rat A Tat Card Game
 */
package utilityObjects;

/**
 * The Action class is designed to communicate all the necessary information 
 * which represents a player or AI action to the game object. These objects are created 
 * within the GUI from the player's actions, or in the AI object by the AI actions. 
 * @author Ian
 *
 */
public class Action {
	private ActionName actionName;
	private String sourcePileName;
	private String targetPlayerName;
	private CardName cardName;
	
	public Action(ActionName action, String sourcePile, String targetPlayer, CardName card)
	{
		this.actionName = action;
		this.sourcePileName = sourcePile;
		this.targetPlayerName = targetPlayer;
		this.cardName = card;
	}

	public ActionName getActionName() {
		return actionName;
	}

	public String getSourcePileName() {
		return sourcePileName;
	}

	public String getTargetPlayerName() {
		return targetPlayerName;
	}

	public CardName getCardName() {
		return cardName;
	}
	
	

}
