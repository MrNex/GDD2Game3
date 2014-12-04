package triggers;

import java.awt.Color;

import engine.Engine;
import engine.manager.ContentManager;
import objects.GameObject;
import objects.MovableGameObject;
import buffer.CollisionBuffer;
import state.object.PlayerOneState;
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
		if(triggeredBy instanceof MovableGameObject){
			
			
			/*
			 * Unfortunately for some reason although this limits the killing to
			 * only the player, the blocks stop falling and only stay in place
			//modified this to make it so the block will hopefully only kill the player
			//based on the sprite
			if(triggeredBy.getSprite() == ((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("SpriteSheetTest"))
			{
				MovableGameObject Obj = (MovableGameObject)triggeredBy;
				if(Obj != null){
					Engine.currentInstance.getCurrentState().removeObj(triggeredBy);
				}
			}
			*/
				MovableGameObject Obj = (MovableGameObject)triggeredBy;
				if(Obj != null){
					if(triggeredBy.getCurrentState() instanceof  PlayerOneState){
						Engine.currentInstance.getCurrentState().removeObj(triggeredBy);
					}
				}
		}
		attachedTo.removeTrigger(this);
	}

}
