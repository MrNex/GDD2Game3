package triggers;

import java.awt.Color;

import objects.GameObject;
import objects.MovableGameObject;
import state.object.DoorState;
import buffer.CollisionBuffer;

/**
 * Makes two object interact with each other by 
 * triggering the pressurePlate to the change the solid state of
 * the resultantBlock
 * @author aml9227 (Alan Leeson)
 *
 */

public class PressurePlateTrigger extends Trigger{

	GameObject pressurePlate;
	GameObject resultantBlock;
	/**
	 * 
	 * @param pressurePlate is the gameObject that triggers the resultantBlock
	 * @param resultantBlock is the block that get affected. (door/obstruction)
	 */
	public PressurePlateTrigger(GameObject pressurePlate, GameObject resultantBlock){
		this.pressurePlate = pressurePlate;
		this.resultantBlock = resultantBlock;
	}
	
	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		// TODO Auto-generated method stub
		if(triggeredBy instanceof MovableGameObject){
			if(resultantBlock.getCurrentState() instanceof DoorState){
				DoorState door = (DoorState)resultantBlock.getCurrentState();
				door.isTriggered(true);
			}
		}
	}

}
