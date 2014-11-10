package state.object;

import java.awt.Graphics2D;

import objects.GameObject;
import objects.MovableGameObject;

/**
 * This state defines an object the warden controls
 * @author aml9227 (Alan Leeson)
 *
 */

public class ClickableBlockState extends ObjectState{
	
	public ClickableBlockState(){
		super();
	}
	
	public void enter(){
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		((MovableGameObject)attachedTo).forceMove();
	}

	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	

}
