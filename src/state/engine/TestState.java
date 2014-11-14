package state.engine;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.CameraManager;
import engine.manager.InputManager;
import engine.manager.ContentManager;
import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.object.ClickableBlockState;
import state.object.PlayerOneState;
import state.object.FallingObstacleState;
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
		
		createTestLevel();
	}
	@Override
	public void update(){
		super.update();
		
		if(((InputManager)Engine.currentInstance.getManager(Managers.INPUTMANAGER)).isKeyPressed('r')){
			wipeState();
			createTestLevel();
		}
	}
	
	private void createTestLevel(){
		GameObject startBumper = new GameObject(-100, 450, 100, 400, new Vec(1.0, 0.0));
		startBumper.setShape(new Rectangle2D.Double(), Color.black);
		startBumper.setVisible(true);
		
		addObj(startBumper);
		
		//Create a floor
		GameObject floor = new GameObject(0, 750, 2200, 100, new Vec(1.0, 0.0));
		floor.setShape(new Rectangle2D.Double(), Color.black);
		floor.setVisible(true);
		
		addObj(floor);
		
		GameObject box = new GameObject(2100, 850, 100, 300, new Vec(1.0, 0.0));
		box.setShape(new Rectangle2D.Double(), Color.black);
		box.setVisible(true);
		
		addObj(box);
		
		GameObject jumpBlock = new GameObject(2200,1050,300,100,new Vec(1.0,0.0));
		jumpBlock.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock.setVisible(true);
		jumpBlock.setTriggerable(true);
		jumpBlock.addTrigger(new AddForceTrigger(new Vec(0.0,-0.008)));
		
		addObj(jumpBlock);
		
		GameObject box3 = new GameObject(2500, 850, 100, 300, new Vec(1.0, 0.0));
		box3.setShape(new Rectangle2D.Double(), Color.black);
		box3.setVisible(true);
		
		addObj(box3);
		
		GameObject floor2 = new GameObject(2500, 750, 300, 100, new Vec(1.0, 0.0));
		floor2.setShape(new Rectangle2D.Double(), Color.black);
		floor2.setVisible(true);
		
		addObj(floor2);
		
		GameObject floor3 = new GameObject(2800, 850, 100, 100, new Vec(1.0, 0.0));
		floor3.setShape(new Rectangle2D.Double(), Color.black);
		floor3.setVisible(true);
		
		addObj(floor3);
		
		GameObject floor4 = new GameObject(2900, 950, 100, 100, new Vec(1.0, 0.0));
		floor4.setShape(new Rectangle2D.Double(), Color.black);
		floor4.setVisible(true);
		
		addObj(floor4);
		
		GameObject floor5 = new GameObject(3000, 1050, 100, 100, new Vec(1.0, 0.0));
		floor5.setShape(new Rectangle2D.Double(), Color.black);
		floor5.setVisible(true);
		
		addObj(floor5);
		
		GameObject floor6 = new GameObject(3100, 1150, 500, 100, new Vec(1.0, 0.0));
		floor6.setShape(new Rectangle2D.Double(), Color.black);
		floor6.setVisible(true);
		
		addObj(floor6);
		
		GameObject pushBlock = new GameObject(3200,750,300,400,new Vec(1.0,0.0));
		pushBlock.setShape(new Rectangle2D.Double(),Color.white);
		pushBlock.setVisible(true);
		pushBlock.setSolid(false);
		pushBlock.setTriggerable(true);
		pushBlock.addTrigger(new AddForceTrigger(new Vec(-0.0000008,0.0)));
		
		addObj(pushBlock);
		
		GameObject floor7 = new GameObject(3600, 1050, 100, 100, new Vec(1.0, 0.0));
		floor7.setShape(new Rectangle2D.Double(), Color.black);
		floor7.setVisible(true);
		
		addObj(floor7);
		
		GameObject floor8 = new GameObject(3700, 950, 100, 100, new Vec(1.0, 0.0));
		floor8.setShape(new Rectangle2D.Double(), Color.black);
		floor8.setVisible(true);
		
		addObj(floor8);
		
		GameObject floor9 = new GameObject(3800, 850, 400, 100, new Vec(1.0, 0.0));
		floor9.setShape(new Rectangle2D.Double(), Color.black);
		floor9.setVisible(true);
		
		addObj(floor9);
		
		GameObject jumpBlock2 = new GameObject(4200,850,100,100,new Vec(1.0,0.0));
		jumpBlock2.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock2.setVisible(true);
		jumpBlock2.setTriggerable(true);
		jumpBlock2.addTrigger(new AddForceTrigger(new Vec(0.0,-0.008)));
		
		addObj(jumpBlock2);
		
		//Create steps
		GameObject step1 = new GameObject(1000, 650, 800, 100, new Vec(1.0, 0.0));
		step1.setShape(new Rectangle2D.Double(), Color.black);
		step1.setVisible(true);
		
		addObj(step1);
		
		GameObject step2 = new GameObject(4300, 650, 100, 300, new Vec(1.0, 0.0));
		step2.setShape(new Rectangle2D.Double(), Color.black);
		step2.setVisible(true);
		
		addObj(step2);
		
		GameObject step3 = new GameObject(4400, 850, 100, 100, new Vec(1.0, 0.0));
		step3.setShape(new Rectangle2D.Double(), Color.black);
		step3.setVisible(true);
		
		addObj(step3);
		
		GameObject jumpBlock3 = new GameObject(4400,750,100,100,new Vec(1.0,0.0));
		jumpBlock3.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock3.setVisible(true);
		jumpBlock3.setTriggerable(true);
		jumpBlock3.addTrigger(new AddForceTrigger(new Vec(0.0,-0.008)));
		
		addObj(jumpBlock3);
		
		GameObject step4 = new GameObject(4500, 550, 100, 400, new Vec(1.0, 0.0));
		step4.setShape(new Rectangle2D.Double(), Color.black);
		step4.setVisible(true);
		
		addObj(step4);
		
		GameObject hangBlock = new GameObject(4300,450,300,100,new Vec(1.0,0.0));
		hangBlock.setShape(new Rectangle2D.Double(),Color.white);
		hangBlock.setVisible(true);
		hangBlock.setSolid(false);
		hangBlock.setTriggerable(true);
		hangBlock.addTrigger(new AddForceTrigger(new Vec(0.0,0.00000008)));
		
		addObj(hangBlock);
		
		GameObject floor10 = new GameObject(4500, 550, 600, 100, new Vec(1.0, 0.0));
		floor10.setShape(new Rectangle2D.Double(), Color.black);
		floor10.setVisible(true);
		
		addObj(floor10);
		
		GameObject step5 = new GameObject(5000, 250, 100, 300, new Vec(1.0, 0.0));
		step5.setShape(new Rectangle2D.Double(), Color.black);
		step5.setVisible(true);
		
		addObj(step5);
		
	/*	GameObject step2 = new GameObject(700, 550, 100, 200, new Vec(1.0, 0.0));
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
	*/
		//Block that slows the player down
	/*	GameObject pushBlock = new GameObject(1400,550,200,200,new Vec(1.0,0.0));
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
	*/	
		//Create player
		MovableGameObject player1 = new MovableGameObject(0, 550, 40, 66, new Vec(1.0, 0.0), 1.0001);
		player1.setSprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("prisonerOrange"));
		//player1.setShape(new Rectangle2D.Double(), Color.blue);
		player1.setVisible(true);
		
		player1.getSprite().queueAnimation(0, true);
		
		player1.pushState(new PlayerOneState());
		
		addObj(player1);
		
		
		//Make the second player...invisible ;)
		GameObject player2 = new GameObject(0,0,10,10, new Vec(1.0, 0.0));
		player2.setVisible(true);		//Make the second player invisible? No. He's visible with an alpha of 0. Two distinctly important different things.
		FallingObstacleState fallingObstacleState = new FallingObstacleState(); //allows the player to drop obstacles
		player2.pushState(fallingObstacleState);
		player2.setTriggerable(true);
		addObj(player2);
		
		
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
