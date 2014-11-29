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
	private boolean movingRight = false;
	private boolean movingLeft = false;



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
		acceleration = 0.15;
		//SEt jump power
		jumpAcceleration = 5.0;
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
		Vec forceVector = new Vec(2);
		Vec impulseVector = new Vec(2);
		
		//If up is pressed
		if(input.isKeyPressed('w')){
			//If the player is apparently on the floor
			if(onFloor){

				//Make sure he's on the floor
				if(((MovableGameObject)attachedTo).checkAllOnFloor()){
					//And if he is jump, and let him know he's not on the floor
					impulseVector.setComponent(1, -jumpAcceleration);	
				}
				else
				{
					onFloor = false;
				}
			}
		}

		//If left or right is pressed
		boolean animKillFlag = true;	//Look for any input
		if(input.isKeyPressed('a')){
			animKillFlag = false;	//Input found

			if(!attachedTo.getSprite().isPlaying(3) && onFloor){
				attachedTo.getSprite().playAnimation(3, true);
			}
			else if (!onFloor) {
				attachedTo.getSprite().playAnimation(5, true);
			}
			forceVector.setComponent(0, -acceleration);
		}
		if(input.isKeyPressed('d')){
			animKillFlag = false;	//Input found

			if(!attachedTo.getSprite().isPlaying(1) && onFloor){
				attachedTo.getSprite().playAnimation(1, true);
			}
			else if (!onFloor) {
				attachedTo.getSprite().playAnimation(4, true);
			}

			forceVector.setComponent(0, acceleration);
		}

		//If no input was found
		if(animKillFlag){


			//If the animation was walking right or left and is done playing the walking animation
			if(attachedTo.getSprite().getCurrentRow() == 3){
				attachedTo.getSprite().playAnimation(2,false);
			}
			else if(attachedTo.getSprite().getCurrentRow() == 1){
				attachedTo.getSprite().playAnimation(0, false);
			}
			
			//If the sprite was jumping, but is now on the floor and not moving
			else if(attachedTo.getSprite().getCurrentRow() == 4 && onFloor) {
				attachedTo.getSprite().playAnimation(0, false);
			}
			else if(attachedTo.getSprite().getCurrentRow() == 5 && onFloor) {
				attachedTo.getSprite().playAnimation(2, false);
			}
			
		}

		//((MovableGameObject)attachedTo).move(translationVector);
		((MovableGameObject)attachedTo).addForce(forceVector);
		((MovableGameObject)attachedTo).addImpulse(impulseVector);

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

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}
