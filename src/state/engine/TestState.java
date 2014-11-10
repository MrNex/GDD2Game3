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
		GameObject floor = new GameObject(0, 750, 2000, 100, new Vec(1.0, 0.0));
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
		
		
		//Create player
		MovableGameObject player1 = new MovableGameObject(0, 550, 100, 100, new Vec(1.0, 0.0), 1.0001);
		player1.setSprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("SpriteSheetTest"));
		//player1.setShape(new Rectangle2D.Double(), Color.blue);
		player1.setVisible(true);
		
		player1.getSprite().queueAnimation(0, true);
		
		player1.pushState(new PlayerOneState());
		
		addObj(player1);
		
		
		MovableGameObject fallingBlock = new MovableGameObject(0,-300,100,100,new Vec(1.0,0.0),1);
		fallingBlock.setShape(new Rectangle2D.Double(),Color.blue);
		fallingBlock.setVisible(true);
		fallingBlock.pushState(new ClickableBlockState());
		//fallingBlock.setTriggerable(true);
		//fallingBlock.addTrigger(new KillOnCollideTrigger());
		addObj(fallingBlock);
		
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(player1);
	}

}
