package state.object.buttons;

import state.engine.EngineState;
import state.engine.menus.MenuEngineState;
import engine.Engine;

/**
 * Defines a button state which when pressed
 * Pops the current state of the engine
 * @author Nex
 *
 */
public class PopStateButtonState extends ButtonState {

	/**
	 * Constructs a popStateButtonState
	 * @param buttonText The text the button should display
	 * @param shifted Is the coordinate system translated to the attached object?
	 */
	public PopStateButtonState(String buttonText) {
		super(buttonText);

	}

	/**
	 * On action, pops current state off of engine
	 */
	@Override
	protected void action() {
		Engine.currentInstance.popState();
		//If the state underneath is a menustate
		EngineState currentState = Engine.currentInstance.getCurrentState();
		if(currentState instanceof MenuEngineState){
			((MenuEngineState)currentState).press();
		}
		
	}

	/**
	 * Currently does nothing on hover
	 */
	@Override
	protected void onHover() {
		// TODO Auto-generated method stub

	}

}
