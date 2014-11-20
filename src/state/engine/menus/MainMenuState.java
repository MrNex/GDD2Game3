package state.engine.menus;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import objects.GameObject;
import state.object.buttons.StartGameButtonState;
import engine.Engine;
import engine.Engine.Managers;
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
		
		//Add start button
		GameObject startButton = new GameObject(
				sm.getPercentageWidth(40.0), sm.getPercentageHeight(45.0),
				sm.getPercentageWidth(20.0), sm.getPercentageHeight(5.0),
				new Vec(1.0f, 0.0f)
				);
		
		startButton.setShape(new Rectangle2D.Double(), Color.gray);
		startButton.setVisible(true);
		startButton.pushState(new StartGameButtonState());
		addObj(startButton);
		
		
	}

}
