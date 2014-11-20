package state.object.buttons;

import state.engine.TestState;
import engine.Engine;

public class StartGameButtonState extends ButtonState{

	/**
	 * Constructs a start game button tate
	 */
	public StartGameButtonState() {
		super("Play");
	}

	/**
	 * When the button is pressed, push a new game state to the engine
	 */
	@Override
	protected void action() {
		Engine.currentInstance.pushState(new TestState());
	}

	/**
	 * Does nothing on hover
	 */
	@Override
	protected void onHover() {
		// TODO Auto-generated method stub
		
	}

}
