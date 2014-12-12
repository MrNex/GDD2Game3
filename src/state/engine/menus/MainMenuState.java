package state.engine.menus;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import objects.GameObject;
import sprites.Sprite;
import state.object.buttons.InstructButtonState;
import state.object.buttons.StartGameButtonState;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.ContentManager;
import engine.manager.ScreenManager;
import mathematics.Vec;

public class MainMenuState extends MenuEngineState{

	public MainMenuState() {
		super();
	}
	
	/**
	 * Initialize all member variables
	 */
	@Override
	public void init(){
		super.init();
		
		//Get refrence to screen manager
				ScreenManager sm = ((ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER));
		
		//Add Background Image		
		GameObject backgroundPic = new GameObject(0,0,sm.getPercentageWidth(100),
									sm.getPercentageHeight(100),new Vec(1.0f,0.0f));
		backgroundPic.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("MenuPageClosed")));
		backgroundPic.setVisible(true);
		addObj(backgroundPic);
		
		//Add start button
		GameObject startButton = new GameObject(
				sm.getPercentageWidth(40.0), sm.getPercentageHeight(45.0),
				sm.getPercentageWidth(20.0), sm.getPercentageHeight(5.0),
				new Vec(1.0f, 0.0f)
				);
		
		GameObject instructButton = new GameObject(
				sm.getPercentageWidth(40.0), sm.getPercentageHeight(60.0),
				sm.getPercentageWidth(20.0), sm.getPercentageHeight(5.0),
				new Vec(1.0f, 0.0f)
				);
		
		startButton.setShape(new Rectangle2D.Double(), Color.white);
		startButton.setVisible(true);
		startButton.pushState(new StartGameButtonState());
		addObj(startButton);
		
		instructButton.setShape(new Rectangle2D.Double(), Color.white);
		instructButton.setVisible(true);
		instructButton.pushState(new InstructButtonState());
		addObj(instructButton);
		
	}

}
