package objects;

import java.util.ArrayList;

import buffer.CollisionBuffer;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.CollisionManager;
import engine.manager.TimeManager;
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
	static private double maxSpeed = 1.5;

	//Attributes
	protected Vec previousPosition;
	protected Vec netForce;
	protected Vec collisionImpulse;
	protected Vec calculatedVelocity;
	protected Vec actualVelocity;
	protected Vec netImpulse;
	protected double mass;

	//Accessors / Modifiers
	/**
	 * Gets this object's velocity vector independent of time passed since last frame
	 * @return the vector containing the velocity of this object
	 */
	public Vec getCalculatedVelocity(){
		return calculatedVelocity;
	}

	public Vec getActualVelocity(){
		return actualVelocity;
	}

	/**
	 * Gets the net force of this object
	 * @return The vector containing the net force of this object
	 */
	public Vec getNetForce(){
		return netForce;
	}

	public Vec getNetImpulse(){
		return netImpulse;
	}

	/**
	 * Gets the impulse due to collison applied on this object previous update
	 * @return the collison impulse
	 */
	public Vec getCollisionImpulse(){
		return collisionImpulse;
	}

	/**
	 * Sets the impulse due to collision to be applied next update
	 * @param impulse new collision impulse
	 */
	public void setCollisionImpulse(Vec impulse){
		collisionImpulse = impulse;
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
		netImpulse = new Vec(2);

		calculatedVelocity = new Vec(2);
		actualVelocity = new Vec(2);

		collisionImpulse = new Vec(2);

		mass = m;
	}

	/**
	 * Adds a force to the movable game object's net force
	 * @param forceInc Force to increment net force by
	 * @param isImpulse should this force be scaled by dt?
	 */
	public void addForce(Vec forceInc){
		//double dt = ((double)((TimeManager)Engine.currentInstance.getManager(Managers.TIMEMANAGER)).getAvgNanoSecondsPassed());
		//netForce.add(Vec.scalarMultiply(forceInc, dt/1000000.0));
		netForce.add(forceInc);
	}

	public void addImpulse(Vec impulse){
		netImpulse.add(impulse);
	}

	/**
	 * Converts the netForce to acceleration
	 * Adds the acceleration to velocity
	 * Limits velocity
	 * Moves object by velocity
	 * Zero's net force
	 */
	public void forceMove(){
		//Get acceleration
		Vec accel = Vec.scalarMultiply(netForce, 1.0/mass);

		double dt = ((double)((TimeManager)Engine.currentInstance.getManager(Managers.TIMEMANAGER)).getAvgNanoSecondsPassed());

		dt /= 1000000.0;

		//Vec actualAccel = Vec.scalarMultiply(accel, dt);

		Vec actualAccel = Vec.scalarMultiply(accel, dt);


		actualAccel.add(Vec.scalarMultiply(netImpulse, 1.0/mass));

		//actualVelocity.scalarMultiply(dt);
		//System.out.println(velocity.toString());



		//move(actualVelocity);
		previousPosition.copy(position);

		position.add(Vec.scalarMultiply(Vec.add(calculatedVelocity, Vec.scalarMultiply(actualAccel, 0.5)), dt));
		calculatedVelocity.add(actualAccel);
		calculatedVelocity.add(Vec.scalarMultiply(netImpulse, 1.0/mass));

		//If the X velocity is really small but non zero, make it zero
		if(Math.abs(calculatedVelocity.getComponent(0)) < 0.001){
			calculatedVelocity.setComponent(0, 0);
		}

		//If the magnitude of the velocity in the X direction is greater than the max speed
		if(Math.abs(calculatedVelocity.getComponent(0)) > MovableGameObject.maxSpeed){
			//System.out.println("Maxing");
			if(calculatedVelocity.getComponent(0) < 0){
				calculatedVelocity.setComponent(0, -MovableGameObject.maxSpeed);
			}
			else{
				calculatedVelocity.setComponent(0, MovableGameObject.maxSpeed);

			}
		}
		//netForce.scalarMultiply(0);
		//netImpulse.scalarMultiply(0);
	}

	/**
	 * Moves the movable game object based on the net force in a specific axis
	 * Converts net force in axis to acceleration
	 * Adds acceleration to velocity in axis
	 * Limits velocity if axis is 0 (X axis)
	 * Moves object by velocity in axis
	 * Note: Net force is not zeroed after this method
	 * @param axis Axis to move on
	 */
	public void specifiedForceMove(int axis){	
		double accel = (netForce.getComponent(axis)  / mass);

		double dt = ((double)((TimeManager)Engine.currentInstance.getManager(Managers.TIMEMANAGER)).getNanoSecondsSinceLastUpdate());

		dt /= 1000000.0;

		//double actualAccel = accel * dt;



		//Increment velocity in the axis by the netforce in axis divided by the mass of the object
		calculatedVelocity.incrementComponent(axis, accel * dt);
		calculatedVelocity.incrementComponent(axis, netImpulse.getComponent(axis) / mass);

		actualVelocity.copy(calculatedVelocity);


		//actualVelocity.incrementComponent(axis, actualAccel);
		//velocity.scalarMultiply(dt);
		//If moving in the X axis
		/*
		if(axis == 0){
			//Limit the velocity
			if(velocity.getComponent(axis) > MovableGameObject.maxSpeed) velocity.setComponent(axis, MovableGameObject.maxSpeed);
			if(velocity.getComponent(axis) < -MovableGameObject.maxSpeed) velocity.setComponent(axis, -MovableGameObject.maxSpeed);
		}*/





		//Move object in axis
		Vec axisVelocity = new Vec(2);
		axisVelocity.setComponent(axis, actualVelocity.getComponent(axis));
		move(axisVelocity);
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
		//Get difference in positions
		Vec dx = Vec.subtract(previousPosition, position);
		
		//Divide this by the time last update loop
		double dt = ((double)((TimeManager)Engine.currentInstance.getManager(Managers.TIMEMANAGER)).getNanoSecondsSinceLastUpdate());
		//Convert to ms
		dt /= 1000000.0;
		
		//Divide dx by dt to get v
		Vec v = Vec.scalarMultiply(dx, 1.0/dt);
		
		//Divide v by t to get a
		Vec a = Vec.scalarMultiply(v, 1/dt);
		
		//Multiply by mass to get force needed to push block back to previous position
		a.scalarMultiply(mass);
		a.scalarMultiply(mass);
		this.addForce(a);
		
		position.copy(previousPosition);
		
		
		updateShape();
	}

	/**
	 * Reverts the position back to the previousPosition on a given axis
	 * Updates the object's shape
	 * @param axis The axis to revert
	 */
	public void revertAxis(int axis){
		//Get difference in positions on axis
		double dx = previousPosition.getComponent(axis) - position.getComponent(axis);
		
		//Divide by time last update loop
		double dt = ((double)((TimeManager)Engine.currentInstance.getManager(Managers.TIMEMANAGER)).getNanoSecondsSinceLastUpdate());
		//Convert to ms
		dt /= 1000000.0;
		
		//Divide change in position by change in time for velocity
		double v = dx/dt;
		//Divide velocity by change in time to get acceleration
		double a = v/dt;
		//Multiply acceleration by mass to get force needed to push object back to previous position
		a *= mass * mass;
		
		//Create resultant force
		Vec rForce = new Vec(2);
		rForce.setComponent(axis, a);
		
		this.addForce(rForce);
		
		position.setComponent(axis, previousPosition.getComponent(axis));
		updateShape();
	}

	/**
	 * Sets the previousosition to the currentPosition
	 */
	public void refresh(){
		previousPosition.copy(position);
	}

	/**
	 * Queries the collision manager for any collisions,
	 * And checks if any of the returned collisions is occurring on the floor
	 * @return Boolean indicating whether or not this movable game object is on the floor
	 */
	public boolean checkAllOnFloor(){
		boolean returnBool = false;

		//Save current velocity and position
		Vec savedVelocity = new Vec(2);
		savedVelocity.copy(calculatedVelocity);

		//Move player in Y Axis
		specifiedForceMove(1);

		//Get list of collision buffers from collision manager
		ArrayList<CollisionBuffer> cBuffs = ((CollisionManager)Engine.currentInstance.getManager(Engine.Managers.COLLISIONMANAGER)).getCollisionsOnObject(this);

		//Loop through registered collisions to check if any are with a floor
		for(CollisionBuffer cBuff : cBuffs){
			//Make sure the collision occurred on the Y Axis
			//If not, skip checking this object
			if(cBuff.obj2CollidedSide.getComponent(1) != 0){
				continue;				
			}

			//Once found a collision on Y axis, check if the abs(colliding object's Y position - (this objects Y position + this object's height)) 
			//is no more than the Y velocity of this object
			//If this is true, the object is on the floor
			double difference = Math.abs(cBuff.obj2.position.getComponent(1) - (position.getComponent(1) + height));
			if(difference <= 10){
				returnBool = true;
			}

		}

		//Revert object to saved velocity and position
		calculatedVelocity.copy(savedVelocity);
		position.copy(previousPosition);

		//If no collisions with the floor are found, return false
		return returnBool;

	}

	public boolean checkOnFloor(CollisionBuffer cBuff){

		boolean isOnFloor = false;

		if(cBuff.obj1 == this){
			if(cBuff.obj2.isSolid()){
				//Make sure the collision occurred on the Y Axis
				//If not, skip checking this object
				if(cBuff.obj2CollidedSide.getComponent(1) != 0) return false;

				//Once found a collision on Y axis, check if the abs(colliding object's Y position - (this objects Y position + this object's height)) 
				//is no more than the Y velocity of this object
				//If this is true, the object is on the floor
				double difference = Math.abs(cBuff.obj2.position.getComponent(1) - (position.getComponent(1) + height));
				if(difference <=  MovableGameObject.maxSpeed){
					isOnFloor = true;
				}
			}

		}
		else{
			if(cBuff.obj1.isSolid()){
				//Make sure the collision occurred on the Y Axis
				//If not, skip checking this object
				if(cBuff.obj1CollidedSide.getComponent(1) != 0) return false;

				//Once found a collision on Y axis, check if the abs(colliding object's Y position - (this objects Y position + this object's height)) 
				//is no more than the Y velocity of this object
				//If this is true, the object is on the floor
				double difference = Math.abs(cBuff.obj1.position.getComponent(1) - (position.getComponent(1) + height));
				if(difference <= 10){
					isOnFloor = true;
				}
			}

		}


		return isOnFloor;
	}

}
