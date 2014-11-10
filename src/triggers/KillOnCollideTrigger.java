package triggers;

import engine.Engine;
import objects.GameObject;
import objects.MovableGameObject;
import buffer.CollisionBuffer;

/**
 * Used to wipe the player out of existence
 * @author aml9227 (Alan Leeson)
 *
 */

public class KillOnCollideTrigger extends Trigger{

	public KillOnCollideTrigger(){
		
	}
	
	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		// TODO Auto-generated method stub
		MovableGameObject Obj = (MovableGameObject)triggeredBy;
		if(Obj != null){
			Engine.currentInstance.getCurrentState().removeObj(triggeredBy);
		}
	}
	
	

}
