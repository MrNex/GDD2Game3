package objects;

import mathematics.Vec;

/**
 * Movable game object defines any gameObjects which need to move
 * and have resolved collisions. While all gameobjects can technically move,
 * if there is a collision there's no simple resolution, and if we do nothing 
 * the gameobject will get "stuck" inside of the other object.
 * 
 * Movable game objects solve this problem by tracking a previous position.
 * In case of a collision MovableGameObjects will be reverted back
 * to their previousPosition by the collision manager.
 * 
 * Movable game objects also implement an activeCheckpoint. If movableGameobjects
 * collide with a deathTrigger they are set back to their activeCheckpoint.
 * @author Nex
 *
 */
public class MovableGameObject extends GameObject {

	//Static attributes
	static private double maxSpeed = 0.005;
	
	//Attributes
	protected Vec previousPosition;
	protected Vec netForce;
	protected Vec velocity;
	protected double mass;
	
	//Accessors / Modifiers
	/**
	 * Gets this object's velocity vector
	 * @return the vector containing the velocity of this object
	 */
	public Vec getVelocity(){
		return velocity;
	}
	
	/**
	 * Gets the net force of this object
	 * @return The vector containing the net force of this object
	 */
	public Vec getNetForce(){
		return netForce;
	}
	
	/**
	 * Gets the mass of this object
	 * @return The mass of this object
	 */
	public double getMass(){
		return mass;
	}


	/**
	 * Constructs a MovableGameObject
	 * @param xx X Position of movable game object
	 * @param yy Y position of movable game object
	 * @param w Width of movable game object
	 * @param h Height of movable game object
	 * @param fwd Forward vector of movable game object
	 * @param m Mass of movable game object
	 */
	public MovableGameObject(double xx, double yy, double w, double h, Vec fwd, double m) {
		super(xx, yy, w, h, fwd);
		previousPosition = new Vec(2);
		
		netForce = new Vec(2);
		velocity = new Vec(2);
		mass = m;
	}
	
	/**
	 * Adds a force to the movable game object's net force
	 * @param forceInc Force to increment net force by
	 */
	public void addForce(Vec forceInc){
		netForce.add(forceInc);
	}
	
	/**
	 * Converts the netForce to acceleration
	 * Adds the acceleration to velocity
	 * Limits velocity
	 * Moves object by velocity
	 * Zero's net force
	 */
	public void forceMove(){
		velocity.add(Vec.scalarMultiply(netForce, 1.0/mass));
		if(velocity.getComponent(0) > MovableGameObject.maxSpeed) velocity.setComponent(0, MovableGameObject.maxSpeed);
		if(velocity.getComponent(0) < -MovableGameObject.maxSpeed) velocity.setComponent(0, -MovableGameObject.maxSpeed);

		//if(velocity.getComponent(1) > MovableGameObject.maxSpeed) velocity.setComponent(1, MovableGameObject.maxSpeed);
		//if(velocity.getComponent(1) < -MovableGameObject.maxSpeed) velocity.setComponent(1, -MovableGameObject.maxSpeed);

		move(velocity);
		netForce.scalarMultiply(0);
	}

	/**
	 * Updates previousPosition and increments position by the movementVector
	 * Also makes call to updateShape
	 * @param movementVec The vector to increment position by
	 */
	public void move(Vec movementVec){
		previousPosition.copy(position);
		position.add(movementVec);
		updateShape();
	}

	/**
	 * Reverts the position back to the previous position
	 * And makes call to updateShape
	 */
	public void revert(){
		position.copy(previousPosition);
		updateShape();
	}
	
	/**
	 * Reverts the position back to the previousPosition on a given axis
	 * Updates the object's shape
	 * @param axis The axis to revert
	 */
	public void revertAxis(int axis){
		position.setComponent(axis, previousPosition.getComponent(axis));
		
		updateShape();
	}
	
	/**
	 * Sets the previousosition to the currentPosition
	 */
	public void refresh(){
		previousPosition.copy(position);
	}

}
