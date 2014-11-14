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
import engine.manager.InputManager;

/**
 * This state updates a new falling block if the mouse is pressed
 * @author afs7827 (Andrew Schott)
 *
 */

public class FallingObstacleState extends ObjectState{
	
	//needed for adding the objects to the teststate
	private TestState theState;
	private boolean mouseCheck;
	private int spawnLimit; //only let player2 spawn this many blocks
	private int currentSpawn;
	
	public FallingObstacleState(){
		super();
		theState = null; // :)
		mouseCheck = false;
		spawnLimit = 9;
		currentSpawn = 0;
	}
	
	//this constructor can be used for a faster state hookup than using the setter below
	public FallingObstacleState(TestState ourState)
	{
		theState = ourState;
		mouseCheck = false;
		spawnLimit = 9;
		currentSpawn = 0;
	}
	
	//we need to be able to add falling block to the state
	//this will be used to add that connection
	public void setState(TestState stateToAddTo)
	{
		theState = stateToAddTo;
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
			//System.out.println("CLICKED!");
			
			//make the block to drop, think this works, coordinates might be weird
			MovableGameObject fallingBlock = new MovableGameObject(input.getMouseWorldPosition().getComponent(0)-50,0,100,100,new Vec(1.0,0.0),5);
			fallingBlock.setShape(new Rectangle2D.Double(),Color.blue);
			fallingBlock.setVisible(true);
			fallingBlock.pushState(new ClickableBlockState());
			fallingBlock.setTriggerable(true);
			fallingBlock.addTrigger(new KillOnCollideTrigger());
			fallingBlock.addForce(new Vec(0.0,0.05));
			theState.addObj(fallingBlock);
			
			currentSpawn++;
			//System.out.println(currentSpawn);
			
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
