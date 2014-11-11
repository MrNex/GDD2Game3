package state.engine;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import engine.Engine;
import engine.manager.CameraManager;
import engine.manager.ContentManager;
import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.object.ClickableBlockState;
import state.object.PlayerOneState;
import triggers.AddForceTrigger;
import triggers.KillOnCollideTrigger;

/**
 * An extension of EngineState which will the development test state
 * For the game
 * @author Nex
 *
 */
public class TestState extends EngineState {

	/**
	 * Constructs a new TestState
	 */
	public TestState() {
		super();
	}
	
	/**
	 * Initialize all member variables
	 */
	@Override
	public void init(){
		super.init();
		
		//Force camera to put 0, 0 at top left corner for now
		
		/*
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).snapTo(
				new Vec(600, 375));
		*/
		
		//Create a floor
		GameObject floor = new GameObject(0, 750, 1800, 100, new Vec(1.0, 0.0));
		floor.setShape(new Rectangle2D.Double(), Color.black);
		floor.setVisible(true);
		
		addObj(floor);
		
		//Create steps
		GameObject step1 = new GameObject(500, 650, 100, 100, new Vec(1.0, 0.0));
		step1.setShape(new Rectangle2D.Double(), Color.black);
		step1.setVisible(true);
		
		addObj(step1);
		
		GameObject step2 = new GameObject(700, 550, 100, 200, new Vec(1.0, 0.0));
		step2.setShape(new Rectangle2D.Double(), Color.black);
		step2.setVisible(true);
		
		addObj(step2);
		
		GameObject step3 = new GameObject(800, 450, 100, 100, new Vec(1.0, 0.0));
		step3.setShape(new Rectangle2D.Double(), Color.black);
		step3.setVisible(true);
		
		addObj(step3);
		
		GameObject step4 = new GameObject(600, 350, 100, 100, new Vec(1.0, 0.0));
		step4.setShape(new Rectangle2D.Double(), Color.black);
		step4.setVisible(true);
		
		addObj(step4);
		
		//Block that slows the player down
		GameObject pushBlock = new GameObject(1400,550,200,200,new Vec(1.0,0.0));
		pushBlock.setShape(new Rectangle2D.Double(),Color.white);
		pushBlock.setVisible(true);
		pushBlock.setSolid(false);
		pushBlock.setTriggerable(true);
		pushBlock.addTrigger(new AddForceTrigger(new Vec(-0.0000008,0.0)));
		
		addObj(pushBlock);
		
		GameObject hangBlock = new GameObject(2200,450,400,100,new Vec(1.0,0.0));
		hangBlock.setShape(new Rectangle2D.Double(),Color.white);
		hangBlock.setVisible(true);
		hangBlock.setSolid(false);
		hangBlock.setTriggerable(true);
		hangBlock.addTrigger(new AddForceTrigger(new Vec(0.0,0.00000008)));
		
		addObj(hangBlock);
		
		//Block that bounces the player upwards
		GameObject jumpBlock = new GameObject(1800,750,100,100,new Vec(1.0,0.0));
		jumpBlock.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock.setVisible(true);
		jumpBlock.setTriggerable(true);
		jumpBlock.addTrigger(new AddForceTrigger(new Vec(0.0,-0.008)));
		
		addObj(jumpBlock);
		
		GameObject jumpBlock2 = new GameObject(2300,750,100,100,new Vec(1.0,0.0));
		jumpBlock2.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock2.setVisible(true);
		jumpBlock2.setTriggerable(true);
		jumpBlock2.addTrigger(new AddForceTrigger(new Vec(0.0,-0.008)));
		
		addObj(jumpBlock2);
		
		GameObject floor2 = new GameObject(1900, 750, 400, 100, new Vec(1.0, 0.0));
		floor2.setShape(new Rectangle2D.Double(), Color.black);
		floor2.setVisible(true);
		
		addObj(floor2);
		
		GameObject floor3 = new GameObject(2400, 750, 400, 100, new Vec(1.0, 0.0));
		floor3.setShape(new Rectangle2D.Double(), Color.black);
		floor3.setVisible(true);
		
		addObj(floor3);
		
		//Create player
		MovableGameObject player1 = new MovableGameObject(0, 550, 100, 100, new Vec(1.0, 0.0), 1.0001);
		player1.setSprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("SpriteSheetTest"));
		//player1.setShape(new Rectangle2D.Double(), Color.blue);
		player1.setVisible(true);
		
		player1.getSprite().queueAnimation(0, true);
		
		player1.pushState(new PlayerOneState());
		
		addObj(player1);
		
		
		MovableGameObject fallingBlock = new MovableGameObject(100,-300,100,100,new Vec(1.0,0.0),5);
		fallingBlock.setShape(new Rectangle2D.Double(),Color.blue);
		fallingBlock.setVisible(true);
		fallingBlock.pushState(new ClickableBlockState());
		fallingBlock.setTriggerable(true);
		fallingBlock.addTrigger(new KillOnCollideTrigger());
		addObj(fallingBlock);

		
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(player1);
	}

}
