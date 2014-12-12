package state.object.buttons;

import state.engine.TestState;
import state.engine.menus.MainMenuState;
import engine.Engine;

public class InstructionsOnState extends ButtonState{

	/**
	 * Constructs a start game button tate
	 */
	public InstructionsOnState() {
		super("");
	}

	/**
	 * When the button is pressed, push a new game state to the engine
	 */
	@Override
	protected void action() {
		Engine.currentInstance.pushState(new MainMenuState());
	}

	/**
	 * Does nothing on hover
	 */
	@Override
	protected void onHover() {
		// TODO Auto-generated method stub
		
	}

}
