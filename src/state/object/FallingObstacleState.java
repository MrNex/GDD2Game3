package state.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.engine.TestState;
import triggers.KillOnCollideTrigger;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.CameraManager;
import engine.manager.InputManager;
import engine.manager.ScreenManager;

/**
 * This state updates a new falling block if the mouse is pressed
 * @author afs7827 (Andrew Schott)
 *
 */

public class FallingObstacleState extends ObjectState{
	
	
	private boolean mouseCheck;
	private int spawnLimit; //only let player2 spawn this many blocks
	private int currentSpawn;
	
	public FallingObstacleState(){
		super();
		mouseCheck = false;
		spawnLimit = 90;
		currentSpawn = 0;
	}
	

	public void enter(){
	
	}

	@Override
	public void update() {
		
		//Get reference to input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Engine.Managers.INPUTMANAGER);
		
		//might be a bit too fast
		if(input.isMouseButtonPressed(1) && mouseCheck == false && currentSpawn < spawnLimit)
		{
			
			//make the block to drop, think this works, coordinates might be weird
			MovableGameObject fallingBlock = new MovableGameObject(
					input.getMouseWorldPosition().getComponent(0)-50,
					((CameraManager)Engine.currentInstance.getManager(Managers.CAMERAMANAGER)).getPosition().getComponent(1) -
						((ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER)).getPanel().getWidth() / 2 - 50,
					100,
					100,
					new Vec(1.0,0.0),
					20);
			fallingBlock.setShape(new Rectangle2D.Double(),Color.blue);
			fallingBlock.setVisible(true);
			fallingBlock.pushState(new ClickableBlockState());
			fallingBlock.setTriggerable(true);
			fallingBlock.addTrigger(new KillOnCollideTrigger());

			//Do it this way:
			Engine.currentInstance.getCurrentState().addObj(fallingBlock);
			
			currentSpawn++;			
			mouseCheck = true;
			
		}
		else if(!(input.isMouseButtonPressed(1)))
		{
			mouseCheck = false;
		}
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
