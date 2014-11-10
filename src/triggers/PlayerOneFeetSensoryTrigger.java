package triggers;

import objects.GameObject;
import objects.MovableGameObject;
import state.object.PlayerOneState;
import buffer.CollisionBuffer;

/**
 * Defines a trigger which lets player one know if he is on the floor.
 * Used in an optimization of telling whether or not the player can jump at this moment
 * @author Nex
 *
 */
public class PlayerOneFeetSensoryTrigger extends Trigger{

	
	//Attributes
	private PlayerOneState playerState;
	
	public PlayerOneFeetSensoryTrigger(PlayerOneState pState) {
		super();
		
		playerState = pState;
	}

	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		//If this object is said to be not on the floor, check if it's on the floor now.
		if(!playerState.isOnFloor()){
			if(((MovableGameObject)attachedTo).checkOnFloor(cBuff)){
				//And if it is, tell the player he's on the floor.
				playerState.setOnFloor(true);
			}
		}
	}

}
