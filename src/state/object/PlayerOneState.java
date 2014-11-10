package state.object;

import java.awt.Graphics2D;

import buffer.CollisionBuffer;
import mathematics.Vec;
import engine.Engine;
import engine.manager.InputManager;
import objects.GameObject;
import objects.MovableGameObject;
import triggers.PlayerOneFeetSensoryTrigger;
import triggers.Trigger;

/**
 * This state defines the state of a gameObject
 * Which can be controlled with WASD
 * @author Nex
 *
 */
public class PlayerOneState extends ObjectState{

	private double acceleration;
	private double jumpAcceleration;
	private boolean onFloor;
	
	//Accessors / Modifiers
	/**
	 * Gets whether player1 is on the floor
	 * @return Whether player 1 is on the floor
	 */
	public boolean isOnFloor(){
		return onFloor;
	}
	
	/**
	 * Sets whether or not player 1 is on the floor
	 * @param isOnFloor Whether player 1 is on the floor
	 */
	public void setOnFloor(boolean isOnFloor){
		onFloor = isOnFloor;
	}
	
	/**
	 * Constructs playerOne's state
	 */
	public PlayerOneState() {
		super();
		
		//Set movementSpeed
		acceleration = 0.000001;
		//SEt jump power
		jumpAcceleration = 0.005;
		//Set as not on the floor
		onFloor = true;
		
	}

	/**
	 * Ensure the attached object is triggerable and add the optimization trigger
	 */
	@Override
	public void enter() {
		attachedTo.setTriggerable(true);
		attachedTo.addTrigger(new PlayerOneFeetSensoryTrigger(this));
		
	}

	/**
	 * Updates Player ones state
	 * Gets reference to input manager
	 * Based on input constructs a translation vector
	 * Moves attached gameObject by translationVector
	 */
	@Override
	public void update() {
		//Get reference to input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Engine.Managers.INPUTMANAGER);
		
		//Create a translation vector
		Vec translationVector = new Vec(2);
		
		//If up is pressed
		if(input.isKeyPressed('w')){
			//If the player is apparently on the floor
			if(onFloor){
				//Make sure he's on the floor
				if(((MovableGameObject)attachedTo).checkAllOnFloor()){
					//And if he is jump, and let him know he's not on the floor
					translationVector.setComponent(1, -jumpAcceleration);	
					onFloor = false;
					System.out.println("Jumping");
				}
				else
				{
					System.out.println("Tested false");
					onFloor = false;
				}
			}
		}
		
		
		//If left or right is pressed
		if(input.isKeyPressed('a')){
			translationVector.setComponent(0, -acceleration);
		}
		if(input.isKeyPressed('d')){
			translationVector.setComponent(0, acceleration);
		}
		
		//((MovableGameObject)attachedTo).move(translationVector);
		((MovableGameObject)attachedTo).addForce(translationVector);
		((MovableGameObject)attachedTo).forceMove();
		
	}

	/**
	 * No special drawing instructions are needed
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * No special exit instructions are needed
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
