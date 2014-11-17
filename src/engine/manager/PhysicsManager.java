package engine.manager;

import engine.Engine;
import mathematics.Vec;
import buffer.CollisionBuffer;
import objects.GameObject;
import objects.MovableGameObject;

/**
 * Defines a class which resolves collisions between two objects
 * Taking into account the axis of collision, and the net forces of the objects
 * @author Nex
 *
 */
public class PhysicsManager extends Manager {

	//Attributes
	private Vec gravity;
	
	
	/**
	 * Constructs a physics manager
	 */
	public PhysicsManager() {
		super();
	}

	/**
	 * Initializes all member variables
	 */
	@Override
	public void init() {
		gravity = new Vec(0.0, 0.15);
	}

	/**
	 * Updates the physics manager
	 * Applies global forces to all movable object's in the current state
	 */
	@Override
	public void update() {
		for(GameObject obj : Engine.currentInstance.getCurrentState().getObjList()){
			if(obj instanceof MovableGameObject){
				applyGlobalForces((MovableGameObject)obj);
			}
		}
	}
	
	/**
	 * Applies all global forces to objects which move
	 * @param mObj Object to apply global forces to
	 */
	public void applyGlobalForces(MovableGameObject mObj){
		mObj.addForce(Vec.scalarMultiply(gravity, mObj.getMass()));
		Vec friction = new Vec(2);
		friction.setComponent(0, -mObj.getCalculatedVelocity().getComponent(0));
		friction.scalarMultiply(0.01);
		mObj.addForce(friction);

	}
	
	/**
	 * Resolves a collision between two game objects
	 * Determines the axis of collision
	 * Zero's movable object's velocity on the axis of collision
	 * Decrements velocity on axis orinthogonal to axis of collision by a frictional force
	 * Reverts movable object's position only on the axis of collision
	 * @param mObj Object which moved into the collision
	 * @param collidedWith Object which was collided with
	 * @param cBuff Information regarding collision
	 */
	public void resolveCollision(MovableGameObject mObj, GameObject collidedWith, CollisionBuffer cBuff){		
		//Determine collision axis
		int axis;
		//If collision was in X Axis
		if(cBuff.obj1CollidedSide.getComponent(0) == 0){
			axis = 0;
		}
		//Else if collision was in Y Axis
		else{
			axis = 1;
		}
		
		//Zero axis velocity
		mObj.getCalculatedVelocity().setComponent(axis, 0);
		//Revert movable object on axis
		mObj.revertAxis(axis);
		
		//If collidedWith is movable
		if(collidedWith instanceof MovableGameObject){
			//have their forces act on each other
			MovableGameObject mObj2 = (MovableGameObject)collidedWith;
			Vec rForce1 = Vec.scalarMultiply(mObj.getNetForce(), mObj.getMass() / (2 *mObj2.getMass()));
			Vec rForce2 = Vec.scalarMultiply(mObj2.getNetForce(), mObj2.getMass()/ (2 *mObj.getMass()));
			rForce1.setComponent(axis == 0 ? 1 : 0, 0);
			rForce2.setComponent(axis == 0 ? 1 : 0, 0);
			mObj2.addForce(rForce1);
			mObj.addForce(rForce2);
		}
		
	}

}
