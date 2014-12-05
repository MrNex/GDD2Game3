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
import state.object.DoorState;
import state.object.PlayerOneState;
import state.object.FallingObstacleState;
import triggers.AddForceTrigger;
import triggers.KillOnCollideTrigger;
import triggers.PressurePlateTrigger;

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
		levelOne();
		
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
		
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(player1);
	}

	void levelOne(){
		Color guidenceBlock = new Color(128,128,128,64);
		
		//ceiling
		GameObject ceiling = new GameObject(0, -750, 81000, 100, new Vec(1.0, 0.0));
		ceiling.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		ceiling.setVisible(true);
		GameObject ceilingLeft = new GameObject(-100, -750, 100, 100, new Vec(1.0, 0.0));
		ceilingLeft.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		ceilingLeft.setVisible(true);
		GameObject ceilingRight = new GameObject(8100,-750, 100, 100, new Vec(1.0, 0.0));
		ceilingRight.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		ceilingRight.setVisible(true);
		
		addObj(ceiling);
		addObj(ceilingLeft);
		addObj(ceilingRight);
		
		GameObject startBumper = new GameObject(-100, -550, 100, 1200, new Vec(1.0, 0.0));
		startBumper.setShape(new Rectangle2D.Double(), walkBlock);
		startBumper.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		startBumper.setVisible(true);
		GameObject startBumperTop = new GameObject(-100, -650, 100, 100, new Vec(1.0, 0.0));
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
		
		
		//floor block
		GameObject floor = new GameObject(0, 750, 5000, 100, new Vec(1.0, 0.0));
		floor.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		floor.setVisible(true);
		GameObject floorLeft = new GameObject(-100, 750, 100, 100, new Vec(1.0, 0.0));
		floorLeft.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		floorLeft.setVisible(true);
		GameObject floorRight = new GameObject(5000, 750, 100, 100, new Vec(1.0, 0.0));
		floorRight.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		floorRight.setVisible(true);
		
		addObj(floor);
		addObj(floorLeft);
		addObj(floorRight);
		
		//block used to guide player where to place the block
		GameObject guide1 = new GameObject(2100, 650, 100, 100, new Vec(1.0, 0.0));
		guide1.setShape(new Rectangle2D.Double(), guidenceBlock);
		guide1.setVisible(true);
		guide1.setSolid(false);
		addObj(guide1);
		
		//pillar blocks
		GameObject pillar1 = new GameObject(2200, 550, 100, 100, new Vec(1.0, 0.0));
		pillar1.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar1.setVisible(true);
		GameObject pillar1Top = new GameObject(2200, 450, 100, 100, new Vec(1.0, 0.0));
		pillar1Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar1Top.setVisible(true);
		GameObject pillar1Bottom = new GameObject(2200, 650, 100, 100, new Vec(1.0, 0.0));
		pillar1Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar1Bottom.setVisible(true);
		
		addObj(pillar1);
		addObj(pillar1Top);
		addObj(pillar1Bottom);
		
		//guidence blocks
		GameObject guide2 = new GameObject(3200, 650, 300, 100, new Vec(1.0, 0.0));
		guide2.setShape(new Rectangle2D.Double(), guidenceBlock);
		guide2.setVisible(true);
		guide2.setSolid(false);
		GameObject guide3 = new GameObject(3300, 550, 200, 100, new Vec(1.0, 0.0));
		guide3.setShape(new Rectangle2D.Double(), guidenceBlock);
		guide3.setVisible(true);
		guide3.setSolid(false);
		GameObject guide4 = new GameObject(3400, 450, 100, 100, new Vec(1.0, 0.0));
		guide4.setShape(new Rectangle2D.Double(), guidenceBlock);
		guide4.setVisible(true);
		guide4.setSolid(false);
		
		addObj(guide2);
		addObj(guide3);
		addObj(guide4);
		
		
		GameObject pillar2 = new GameObject(3500, 350, 100, 300, new Vec(1.0, 0.0));
		pillar2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar2.setVisible(true);
		GameObject pillar2Top = new GameObject(3500, 250, 100, 100, new Vec(1.0, 0.0));
		pillar2Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar2Top.setVisible(true);
		GameObject pillar2Bottom = new GameObject(3500, 650, 100, 100, new Vec(1.0, 0.0));
		pillar2Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar2Bottom.setVisible(true);
		
		addObj(pillar2);
		addObj(pillar2Top);
		addObj(pillar2Bottom);
		
		GameObject pillar3 = new GameObject(5000, 450, 100, 200, new Vec(1.0, 0.0));
		pillar3.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar3.setVisible(true);
		GameObject pillar3Top = new GameObject(5000, 350, 100, 100, new Vec(1.0, 0.0));
		pillar3Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar3Top.setVisible(true);
		GameObject pillar3Bottom = new GameObject(5000, 650, 100, 100, new Vec(1.0, 0.0));
		pillar3Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar3Bottom.setVisible(true);
		
		addObj(pillar3);
		addObj(pillar3Top);
		addObj(pillar3Bottom);
		
		GameObject pillar4 = new GameObject(5000, -550, 100, 700, new Vec(1.0, 0.0));
		pillar4.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar4.setVisible(true);
		GameObject pillar4Top = new GameObject(5000, -650, 100, 100, new Vec(1.0, 0.0));
		pillar4Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar4Top.setVisible(true);
		GameObject pillar4Bottom = new GameObject(5000, 150, 100, 100, new Vec(1.0, 0.0));
		pillar4Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar4Bottom.setVisible(true);
		
		addObj(pillar4);
		addObj(pillar4Top);
		addObj(pillar4Bottom);
		
		//floor for next section
		GameObject floor2 = new GameObject(5200, 350, 700, 100, new Vec(1.0, 0.0));
		floor2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		floor2.setVisible(true);
		GameObject floor2Left = new GameObject(5100, 350, 100, 100, new Vec(1.0, 0.0));
		floor2Left.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		floor2Left.setVisible(true);
		GameObject floor2Right = new GameObject(5900, 350, 100, 100, new Vec(1.0, 0.0));
		floor2Right.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		floor2Right.setVisible(true);
		
		addObj(floor2);
		addObj(floor2Left);
		addObj(floor2Right);
		
		GameObject pillar5 = new GameObject(6000, -550, 100, 500, new Vec(1.0, 0.0));
		pillar5.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar5.setVisible(true);
		GameObject pillar5Top = new GameObject(6000, -650, 100, 100, new Vec(1.0, 0.0));
		pillar5Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar5Top.setVisible(true);
		GameObject pillar5Bottom = new GameObject(6000, -50, 100, 100, new Vec(1.0, 0.0));
		pillar5Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar5Bottom.setVisible(true);
		
		addObj(pillar5);
		addObj(pillar5Top);
		addObj(pillar5Bottom);
		
		//obstacle surrounding pressurePlate
		GameObject guard = new GameObject(5399, 250, 100, 100, new Vec(1.0, 0.0));
		guard.setShape(new Rectangle2D.Double(), walkBlock);
		guard.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		guard.setVisible(true);
		GameObject guard2 = new GameObject(5601, 250, 100, 100, new Vec(1.0, 0.0));
		guard2.setShape(new Rectangle2D.Double(), walkBlock);
		guard2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		guard2.setVisible(true);
		
		addObj(guard);
		addObj(guard2);
		
		//pressure plate puzzle
		GameObject removableWall = new GameObject(6000, 50, 100, 300, new Vec(1.0, 0.0));
		removableWall.setShape(new Rectangle2D.Double(), new Color(0,0,200));
		removableWall.setVisible(true);
		removableWall.pushState(new DoorState(new Color(0,0,255)));
		GameObject pressurePlate = new GameObject(5499, 330, 102, 20, new Vec(1.0, 0.0));
		pressurePlate.setShape(new Rectangle2D.Double(), new Color(0,0,200,50));
		pressurePlate.setVisible(true);
		pressurePlate.setSolid(false);
		pressurePlate.setTriggerable(true);
		pressurePlate.addTrigger(new PressurePlateTrigger(pressurePlate,removableWall));
		
		addObj(removableWall);
		addObj(pressurePlate);
		
		
		//Last Puzzle Area
		GameObject pillar6 = new GameObject(6000, 350, 100, 600, new Vec(1.0, 0.0));
		pillar6.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar6.setVisible(true);
		GameObject pillar6Top = new GameObject(6000, 250, 100, 100, new Vec(1.0, 0.0));
		pillar6Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar6Top.setVisible(true);
		GameObject pillar6Bottom = new GameObject(6000, 950, 100, 100, new Vec(1.0, 0.0));
		pillar6Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar6Bottom.setVisible(true);
		
		addObj(pillar6);
		addObj(pillar6Top);
		addObj(pillar6Bottom);
		
		GameObject pillar7 = new GameObject(6300, -550, 100, 1300, new Vec(1.0, 0.0));
		pillar7.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar7.setVisible(true);
		GameObject pillar7Top = new GameObject(6300, -650, 100, 100, new Vec(1.0, 0.0));
		pillar7Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar7Top.setVisible(true);
		GameObject pillar7Bottom = new GameObject(6300, 750, 100, 100, new Vec(1.0, 0.0));
		pillar7Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar7Bottom.setVisible(true);
		
		addObj(pillar7);
		addObj(pillar7Top);
		addObj(pillar7Bottom);
		
		//floor for third section
		GameObject floor3 = new GameObject(6100, 1050, 2000, 100, new Vec(1.0, 0.0));
		floor3.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		floor3.setVisible(true);
		GameObject floor3Left = new GameObject(6000, 1050, 100, 100, new Vec(1.0, 0.0));
		floor3Left.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		floor3Left.setVisible(true);
		GameObject floor3Right = new GameObject(8100, 1050, 100, 100, new Vec(1.0, 0.0));
		floor3Right.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		floor3Right.setVisible(true);
		
		addObj(floor3);
		addObj(floor3Left);
		addObj(floor3Right);
		
		//ledge
		GameObject ledge = new GameObject(7200, 750, 200, 100, new Vec(1.0, 0.0));
		ledge.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		ledge.setVisible(true);
		GameObject ledgeLeft = new GameObject(7100, 750, 100, 100, new Vec(1.0, 0.0));
		ledgeLeft.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		ledgeLeft.setVisible(true);
		GameObject ledgeRight = new GameObject(7400, 750, 100, 100, new Vec(1.0, 0.0));
		ledgeRight.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		ledgeRight.setVisible(true);
		
		addObj(ledge);
		addObj(ledgeLeft);
		addObj(ledgeRight);
		
		GameObject ledgeEnd = new GameObject(7400, 850, 100, 100, new Vec(1.0, 0.0));
		ledgeEnd.setShape(new Rectangle2D.Double(), walkBlock);
		ledgeEnd.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		ledgeEnd.setVisible(true);
		GameObject ledgeEndBottom = new GameObject(7400, 950, 100, 100, new Vec(1.0, 0.0));
		ledgeEndBottom.setShape(new Rectangle2D.Double(), walkBlock);
		ledgeEndBottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		ledgeEndBottom.setVisible(true);
		
		addObj(ledgeEnd);
		addObj(ledgeEndBottom);
		
		GameObject ledge2 = new GameObject(6500, 650, 100, 100, new Vec(1.0, 0.0));
		ledge2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		ledge2.setVisible(true);
		GameObject ledge2Left = new GameObject(6400, 650, 100, 100, new Vec(1.0, 0.0));
		ledge2Left.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		ledge2Left.setVisible(true);
		GameObject ledge2Right = new GameObject(6600, 650, 100, 100, new Vec(1.0, 0.0));
		ledge2Right.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		ledge2Right.setVisible(true);
		
		addObj(ledge2);
		addObj(ledge2Left);
		addObj(ledge2Right);
		
		GameObject ledge3 = new GameObject(6500, 250, 100, 100, new Vec(1.0, 0.0));
		ledge3.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorMiddle")));
		ledge3.setVisible(true);
		GameObject ledge3Left = new GameObject(6400, 250, 100, 100, new Vec(1.0, 0.0));
		ledge3Left.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorLeft")));
		ledge3Left.setVisible(true);
		GameObject ledge3Right = new GameObject(6600, 250, 100, 100, new Vec(1.0, 0.0));
		ledge3Right.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floorRight")));
		ledge3Right.setVisible(true);
		
		addObj(ledge3);
		addObj(ledge3Left);
		addObj(ledge3Right);
		
		GameObject ledgeEnd2Top = new GameObject(6600, 350, 100, 100, new Vec(1.0, 0.0));
		ledgeEnd2Top.setShape(new Rectangle2D.Double(), walkBlock);
		ledgeEnd2Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		ledgeEnd2Top.setVisible(true);
		GameObject ledgeEnd2Bottom = new GameObject(6600, 450, 100, 100, new Vec(1.0, 0.0));
		ledgeEnd2Bottom.setShape(new Rectangle2D.Double(), walkBlock);
		ledgeEnd2Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		ledgeEnd2Bottom.setVisible(true);
		
		addObj(ledgeEnd2Top);
		addObj(ledgeEnd2Bottom);
		
		GameObject block = new GameObject(6500, 450, 100, 100, new Vec(1.0, 0.0));
		block.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		block.setVisible(true);
		GameObject block2 = new GameObject(6700, 450, 100, 100, new Vec(1.0, 0.0));
		block2.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("floor")));
		block2.setVisible(true);
		addObj(block);
		addObj(block2);
		
		//pressurePlates
		GameObject removableWall2 = new GameObject(6400, 750, 700, 100, new Vec(1.0, 0.0));
		removableWall2.setShape(new Rectangle2D.Double(), new Color(0,180,200));
		removableWall2.setVisible(true);
		removableWall2.pushState(new DoorState(new Color(0,180,200)));
		GameObject pressurePlate2 = new GameObject(7380, 950, 20, 100, new Vec(1.0, 0.0));
		pressurePlate2.setShape(new Rectangle2D.Double(), new Color(0,180,200,50));
		pressurePlate2.setVisible(true);
		pressurePlate2.setSolid(false);
		pressurePlate2.setTriggerable(true);
		pressurePlate2.addTrigger(new PressurePlateTrigger(pressurePlate2,removableWall2));
		
		addObj(removableWall2);
		addObj(pressurePlate2);
		
		GameObject removableWall3 = new GameObject(6700, 250, 100, 100, new Vec(1.0, 0.0));
		removableWall3.setShape(new Rectangle2D.Double(), new Color(0,180,0));
		removableWall3.setVisible(true);
		removableWall3.pushState(new DoorState(new Color(0,180,0)));
		GameObject pressurePlate3 = new GameObject(6500, 430, 100, 20, new Vec(1.0, 0.0));
		pressurePlate3.setShape(new Rectangle2D.Double(), new Color(0,180,0,50));
		pressurePlate3.setVisible(true);
		pressurePlate3.setSolid(false);
		pressurePlate3.setTriggerable(true);
		pressurePlate3.addTrigger(new PressurePlateTrigger(pressurePlate3,removableWall3));
		
		addObj(removableWall3);
		addObj(pressurePlate3);
		
		GameObject removableWall4 = new GameObject(7100, 450, 100, 300, new Vec(1.0, 0.0));
		removableWall4.setShape(new Rectangle2D.Double(), new Color(180,0,0));
		removableWall4.setVisible(true);
		removableWall4.pushState(new DoorState(new Color(180,0,0)));
		GameObject pressurePlate4 = new GameObject(6700, 430, 100, 20, new Vec(1.0, 0.0));
		pressurePlate4.setShape(new Rectangle2D.Double(), new Color(180,0,0,50));
		pressurePlate4.setVisible(true);
		pressurePlate4.setSolid(false);
		pressurePlate4.setTriggerable(true);
		pressurePlate4.addTrigger(new PressurePlateTrigger(pressurePlate4,removableWall4));
		
		addObj(removableWall4);
		addObj(pressurePlate4);
		
		GameObject pillar8 = new GameObject(7100, -550, 100, 900, new Vec(1.0, 0.0));
		pillar8.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallMiddle")));
		pillar8.setVisible(true);
		GameObject pillar8Top = new GameObject(7100, -650, 100, 100, new Vec(1.0, 0.0));
		pillar8Top.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallTop")));
		pillar8Top.setVisible(true);
		GameObject pillar8Bottom = new GameObject(7100,350, 100, 100, new Vec(1.0, 0.0));
		pillar8Bottom.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("wallBottom")));
		pillar8Bottom.setVisible(true);
		
		addObj(pillar8);
		addObj(pillar8Top);
		addObj(pillar8Bottom);
	}
	
	void testLevel(){
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
		jumpBlock.addTrigger(new AddForceTrigger(new Vec(0.0,-7)));
		
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
		pushBlock.addTrigger(new AddForceTrigger(new Vec(-0.001,0.0)));
		
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
		jumpBlock2.addTrigger(new AddForceTrigger(new Vec(0.0,-7)));
		
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
		jumpBlock3.addTrigger(new AddForceTrigger(new Vec(0.0,-7)));
		
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
		hangBlock.addTrigger(new AddForceTrigger(new Vec(0.0,0.0008)));
		
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
