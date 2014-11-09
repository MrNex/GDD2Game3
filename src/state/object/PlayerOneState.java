package state.object;

import java.awt.Graphics2D;

import mathematics.Vec;
import engine.Engine;
import engine.manager.InputManager;
import objects.MovableGameObject;

/**
 * This state defines the state of a gameObject
 * Which can be controlled with WASD
 * @author Nex
 *
 */
public class PlayerOneState extends ObjectState{

	private double acceleration;
	private double jumpAcceleration;
	
	/**
	 * Constructs playerOne's state
	 */
	public PlayerOneState() {
		super();
		
		//Set movementSpeed
		acceleration = 0.000001;
		//SEt jump power
		jumpAcceleration = 0.005;
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
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
			//If the attached object isn't going anywhere vertically
			//TODO: What if player holds W while under a low ceiling?
			//This edge case breaks the current physics system.
			if(((MovableGameObject)attachedTo).getVelocity().getComponent(1) == 0){
				translationVector.setComponent(1, -jumpAcceleration);
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
