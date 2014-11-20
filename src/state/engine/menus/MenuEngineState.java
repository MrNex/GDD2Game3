package state.engine.menus;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import mathematics.Vec;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.CameraManager;
import engine.manager.ScreenManager;
import objects.GameObject;
import state.engine.EngineState;
import state.object.buttons.ButtonState;
import state.object.buttons.PopStateButtonState;
import state.object.ObjectState;

/**
 * Defines a stateof the engine made up of buttons
 * 
 * Each time this state updates it updates it's components.
 * Any objects waiting to be added/removed from the state are then
 * added/removed.
 * @author Nex
 *
 */
public class MenuEngineState extends EngineState {

	/**
	 * Constructs a menu engine state
	 */
	public MenuEngineState() {
		super();
	}
	
	@Override
	public void init(){
		super.init();
		
		//Get refrence to screen manager
		ScreenManager sm = ((ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER));
		
		//Create an exit button
		GameObject exitButton = new GameObject(
				sm.getPercentageWidth(85.0), sm.getPercentageHeight(5.0),
				sm.getPercentageWidth(10.0), sm.getPercentageHeight(5.0),
				new Vec(1.0f, 0.0f)
				);
		
		exitButton.setShape(new Rectangle2D.Double(), Color.gray);
		exitButton.setVisible(true);
		exitButton.pushState(new PopStateButtonState("Quit"));
		addObj(exitButton);
		
	}
	
	/**
	 * Loops through all objects in state, and if object is a button state
	 * It marks the buttonState as pressed- so the button will
	 * not register input until the mouse is released.
	 */
	public void press()
	{
		//loop through objects
		for(GameObject obj : objects){
			ObjectState attachedState = obj.getCurrentState();
			//If the object's state is a button
			if(attachedState instanceof ButtonState){
				//Set it as pressed so it doesn't accidently get clicked.
				((ButtonState)attachedState).setPressed();
			}
		}
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}
	
	

}
