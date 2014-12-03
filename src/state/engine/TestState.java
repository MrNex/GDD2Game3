package state.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.CameraManager;
import engine.manager.InputManager;
import engine.manager.ContentManager;
import engine.manager.ScreenManager;
import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import sprites.Sprite;
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

	private Color walkBlock;
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
		walkBlock = new Color(64,64,64);
		
		GameObject startBumper = new GameObject(-100, 550, 100, 100, new Vec(1.0, 0.0));
		startBumper.setShape(new Rectangle2D.Double(), walkBlock);
		startBumper.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		startBumper.setVisible(true);
		GameObject startBumperTop = new GameObject(-100, 450, 100, 100, new Vec(1.0, 0.0));
		startBumperTop.setShape(new Rectangle2D.Double(), walkBlock);
		startBumperTop.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		startBumperTop.setVisible(true);
		GameObject startBumperBottom = new GameObject(-100, 650, 100, 100, new Vec(1.0, 0.0));
		startBumperBottom.setShape(new Rectangle2D.Double(), walkBlock);
		startBumperBottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		startBumperBottom.setVisible(true);
		
		addObj(startBumper);
		addObj(startBumperTop);
		addObj(startBumperBottom);
		
		//Create a floor
		GameObject floor = new GameObject(0, 750, 2200, 100, new Vec(1.0, 0.0));
		floor.setShape(new Rectangle2D.Double(), walkBlock);
		floor.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		floor.setVisible(true);
		GameObject floorLeft = new GameObject(-100, 750, 100, 100, new Vec(1.0, 0.0));
		floorLeft.setShape(new Rectangle2D.Double(), walkBlock);
		floorLeft.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		floorLeft.setVisible(true);
		GameObject floorRight = new GameObject(2200, 750, 100, 100, new Vec(1.0, 0.0));
		floorRight.setShape(new Rectangle2D.Double(), walkBlock);
		floorRight.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		floorRight.setVisible(true);
		
		addObj(floor);
		addObj(floorLeft);
		addObj(floorRight);
		
		GameObject box = new GameObject(2200, 950, 100, 100, new Vec(1.0, 0.0));
		box.setShape(new Rectangle2D.Double(), walkBlock);
		box.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		box.setVisible(true);
		GameObject boxTop = new GameObject(2200, 850, 100, 100, new Vec(1.0, 0.0));
		boxTop.setShape(new Rectangle2D.Double(), walkBlock);
		boxTop.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		boxTop.setVisible(true);
		GameObject boxBottom = new GameObject(2200, 1050, 100, 100, new Vec(1.0, 0.0));
		boxBottom.setShape(new Rectangle2D.Double(), walkBlock);
		boxBottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		boxBottom.setVisible(true);
		
		addObj(box);
		addObj(boxTop);
		addObj(boxBottom);
		
		GameObject box2 = new GameObject(2300, 1150, 200, 100, new Vec(1.0, 0.0));
		box2.setShape(new Rectangle2D.Double(), walkBlock);
		box2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		box2.setVisible(true);
		GameObject box2Left = new GameObject(2200, 1150, 100, 100, new Vec(1.0, 0.0));
		box2Left.setShape(new Rectangle2D.Double(), walkBlock);
		box2Left.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		box2Left.setVisible(true);
		GameObject box2Right = new GameObject(2500, 1150, 100, 100, new Vec(1.0, 0.0));
		box2Right.setShape(new Rectangle2D.Double(), walkBlock);
		box2Right.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		box2Right.setVisible(true);
		
		addObj(box2);
		addObj(box2Left);
		addObj(box2Right);
		
		GameObject box3 = new GameObject(2500, 850, 100, 200, new Vec(1.0, 0.0));
		box3.setShape(new Rectangle2D.Double(), walkBlock);
		box3.setVisible(true);
		
		addObj(box3);
		
		GameObject jumpBlock = new GameObject(2300,1050,200,100,new Vec(1.0,0.0));
		jumpBlock.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock.setVisible(true);
		jumpBlock.setTriggerable(true);
		jumpBlock.addTrigger(new AddForceTrigger(new Vec(0.0,-800)));
		
		addObj(jumpBlock);
		
		GameObject floor2 = new GameObject(2500, 750, 300, 100, new Vec(1.0, 0.0));
		floor2.setShape(new Rectangle2D.Double(), walkBlock);
		floor2.setVisible(true);
		
		addObj(floor2);
		
		GameObject floor3 = new GameObject(2800, 850, 100, 100, new Vec(1.0, 0.0));
		floor3.setShape(new Rectangle2D.Double(), walkBlock);
		floor3.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		floor3.setVisible(true);
		
		addObj(floor3);
		
		GameObject floor4 = new GameObject(2900, 950, 100, 100, new Vec(1.0, 0.0));
		floor4.setShape(new Rectangle2D.Double(), walkBlock);
		floor4.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		floor4.setVisible(true);
		
		addObj(floor4);
		
		GameObject floor5 = new GameObject(3000, 1050, 100, 100, new Vec(1.0, 0.0));
		floor5.setShape(new Rectangle2D.Double(), walkBlock);
		floor5.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		floor5.setVisible(true);
		
		addObj(floor5);
		
		GameObject floor6 = new GameObject(3100, 1150, 500, 100, new Vec(1.0, 0.0));
		floor6.setShape(new Rectangle2D.Double(), walkBlock);
		floor6.setVisible(true);
		
		addObj(floor6);
		
		GameObject pushBlock = new GameObject(3200,750,300,400,new Vec(1.0,0.0));
		pushBlock.setShape(new Rectangle2D.Double(),Color.white);
		pushBlock.setVisible(true);
		pushBlock.setSolid(false);
		pushBlock.setTriggerable(true);
		pushBlock.addTrigger(new AddForceTrigger(new Vec(-0.08,0.0)));
		
		addObj(pushBlock);
		
		GameObject floor7 = new GameObject(3600, 1050, 100, 100, new Vec(1.0, 0.0));
		floor7.setShape(new Rectangle2D.Double(), walkBlock);
		floor7.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		floor7.setVisible(true);
		
		addObj(floor7);
		
		GameObject floor8 = new GameObject(3700, 950, 100, 100, new Vec(1.0, 0.0));
		floor8.setShape(new Rectangle2D.Double(), walkBlock);
		floor8.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		floor8.setVisible(true);
		
		addObj(floor8);
		
		GameObject floor9 = new GameObject(3800, 850, 400, 100, new Vec(1.0, 0.0));
		floor9.setShape(new Rectangle2D.Double(), walkBlock);
		floor9.setVisible(true);
		
		addObj(floor9);
		
		GameObject jumpBlock2 = new GameObject(4200,850,100,100,new Vec(1.0,0.0));
		jumpBlock2.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock2.setVisible(true);
		jumpBlock2.setTriggerable(true);
		jumpBlock2.addTrigger(new AddForceTrigger(new Vec(0.0,-800)));
		
		addObj(jumpBlock2);
		
		//Create steps
		GameObject step1 = new GameObject(1000, 650, 800, 100, new Vec(1.0, 0.0));
		step1.setShape(new Rectangle2D.Double(), walkBlock);
		step1.setVisible(true);
		
		addObj(step1);
		
		GameObject step2 = new GameObject(4300, 650, 100, 300, new Vec(1.0, 0.0));
		step2.setShape(new Rectangle2D.Double(), walkBlock);
		step2.setVisible(true);
		
		addObj(step2);
		
		GameObject step3 = new GameObject(4400, 850, 100, 100, new Vec(1.0, 0.0));
		step3.setShape(new Rectangle2D.Double(), walkBlock);
		step3.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		step3.setVisible(true);
		
		addObj(step3);
		
		GameObject jumpBlock3 = new GameObject(4400,750,100,100,new Vec(1.0,0.0));
		jumpBlock3.setShape(new Rectangle2D.Double(),Color.green);
		jumpBlock3.setVisible(true);
		jumpBlock3.setTriggerable(true);
		jumpBlock3.addTrigger(new AddForceTrigger(new Vec(0.0,-800)));
		
		addObj(jumpBlock3);
		
		GameObject step4 = new GameObject(4500, 550, 100, 400, new Vec(1.0, 0.0));
		step4.setShape(new Rectangle2D.Double(), walkBlock);
		step4.setVisible(true);
		
		addObj(step4);
		
		GameObject hangBlock = new GameObject(4300,450,300,100,new Vec(1.0,0.0));
		hangBlock.setShape(new Rectangle2D.Double(),Color.white);
		hangBlock.setVisible(true);
		hangBlock.setSolid(false);
		hangBlock.setTriggerable(true);
		hangBlock.addTrigger(new AddForceTrigger(new Vec(0.0,0.03)));
		
		addObj(hangBlock);
		
		GameObject floor10 = new GameObject(4500, 550, 1600, 100, new Vec(1.0, 0.0));
		floor10.setShape(new Rectangle2D.Double(), walkBlock);
		floor10.setVisible(true);
		
		addObj(floor10);
		
		GameObject step5 = new GameObject(6100, 150, 100, 500, new Vec(1.0, 0.0));
		step5.setShape(new Rectangle2D.Double(), walkBlock);
		step5.setVisible(true);
		
		addObj(step5);
		
		//Ceilling
		GameObject roof1 = new GameObject(-100, 350, 1000, 100, new Vec(1.0, 0.0));
		roof1.setShape(new Rectangle2D.Double(), walkBlock);
		roof1.setVisible(true);
		
		addObj(roof1);
		
		GameObject roof2 = new GameObject(900, -150, 100, 600, new Vec(1.0, 0.0));
		roof2.setShape(new Rectangle2D.Double(), walkBlock);
		roof2.setVisible(true);
		
		addObj(roof2);
		
		GameObject roof3 = new GameObject(1300, 350, 200, 100, new Vec(1.0, 0.0));
		roof3.setShape(new Rectangle2D.Double(), walkBlock);
		roof3.setVisible(true);
		
		addObj(roof3);
		
		//GameObject roof4 = new GameObject(900, -250, 1000, 100, new Vec(1.0, 0.0));
		//roof4.setShape(new Rectangle2D.Double(), walkBlock);
		//roof4.setVisible(true);
		
		//addObj(roof4);
		
		GameObject roof5 = new GameObject(1800, -150, 100, 600, new Vec(1.0, 0.0));
		roof5.setShape(new Rectangle2D.Double(), walkBlock);
		roof5.setVisible(true);
		
		addObj(roof5);
		
		GameObject roof6 = new GameObject(1900, 350, 900, 100, new Vec(1.0, 0.0));
		roof6.setShape(new Rectangle2D.Double(), walkBlock);
		roof6.setVisible(true);
		
		addObj(roof6);
		
		GameObject roof7 = new GameObject(3800, 450, 200, 100, new Vec(1.0, 0.0));
		roof7.setShape(new Rectangle2D.Double(), walkBlock);
		roof7.setVisible(true);
		
		addObj(roof7);
		
		GameObject roof8 = new GameObject(3900, -250, 100, 700, new Vec(1.0, 0.0));
		roof8.setShape(new Rectangle2D.Double(), walkBlock);
		roof8.setVisible(true);
		
		addObj(roof8);
		
	//	GameObject roof9 = new GameObject(4000, -250, 15000, 100, new Vec(1.0, 0.0));
	//	roof9.setShape(new Rectangle2D.Double(), walkBlock);
	//	roof9.setVisible(true);
		
	//	addObj(roof9);
		
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
		MovableGameObject player1 = new MovableGameObject(0, 550, 40, 66, new Vec(1.0, 0.0), 5);
		player1.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("prisonerOrange")));
		//player1.setShape(new Rectangle2D.Double(), Color.blue);
		player1.setVisible(true);
		
		player1.getSprite().queueAnimation(0, true);
		
		player1.pushState(new PlayerOneState());
		
		addObj(player1);
		
		
		//Make the second player...invisible ;)
		GameObject player2 = new GameObject(0,0,10,10, new Vec(1.0, 0.0));
		player2.setVisible(false);
		FallingObstacleState fallingObstacleState = new FallingObstacleState(); //allows the player to drop obstacles
		player2.pushState(fallingObstacleState);
		player2.setTriggerable(true);
		addObj(player2);
		
		
		MovableGameObject fallingBlock = new MovableGameObject(100,-300,100,100,new Vec(1.0,0.0), 20);
		fallingBlock.setShape(new Rectangle2D.Double(),Color.blue);
		fallingBlock.setVisible(true);
		fallingBlock.pushState(new ClickableBlockState());
		fallingBlock.setTriggerable(true);
		fallingBlock.addTrigger(new KillOnCollideTrigger());
		addObj(fallingBlock);

		
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(player1);
	}

	@Override
	public void enter() {
		//Get reference to screenManager to get screen dimensions
		ScreenManager screen = (ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER);
		//Get reference to camera manager
		CameraManager cm = ((CameraManager)Engine.currentInstance.getManager(Managers.CAMERAMANAGER));

		//Add half of screen dimensions to translationVec
		//Get the screen dimensions
		Vec screenDimensions = new Vec(2);
		screenDimensions.setComponent(0, screen.getWindow().getWidth());
		screenDimensions.setComponent(1, screen.getWindow().getHeight());
		//Scale by 1/2
		screenDimensions.scalarMultiply(0.5);
		
		cm.setOffset(screenDimensions);
		
	}

}
