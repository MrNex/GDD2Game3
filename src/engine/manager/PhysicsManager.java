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
		gravity = new Vec(0.0, 0.0000001);
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
		//System.out.println("Resolving");
		
		//Determine collision axis
		int axis;
		//If collision was in X Axis
		if(cBuff.obj1CollidedSide.getComponent(0) == 0){
			axis = 0;
		}
		//Else if collision was in Y Axis
		else{
			axis = 1;
			mObj.getVelocity().scalarMultiply(1.0/mObj.getMass());
		}
		
		//Zero axis velocity
		mObj.getVelocity().setComponent(axis, 0);
		//Revert movable object on axis
		mObj.revertAxis(axis);
		
	}

}
