package state.object.buttons;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import mathematics.Vec;
import objects.GameObject;
import sprites.Sprite;
import engine.Engine;
import engine.manager.ContentManager;

public class InstructButtonState extends ButtonState{

	/**
	 * Constructs a instructions button state
	 */
	public InstructButtonState() {
		super("Instructions");
	}

	/**
	 * When the button is pressed, display an image
	 */
	@Override
	protected void action() {
		//Engine.currentInstance.pushState(new TestState());
		GameObject instructions = new GameObject(84,9,1000,700,new Vec(1.0f, 0.0f));
		instructions.setShape(new Rectangle2D.Double(), Color.blue);
		instructions.setSprite(new Sprite(((ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER)).getSprite("Instructions")));
		instructions.setVisible(true);
		instructions.pushState(new InstructionsOnState());
		//addObj(instructions);
		
		//I did it this way ;)
		Engine.currentInstance.getCurrentState().addObj(instructions);
	}

	/**
	 * Does nothing on hover
	 */
	@Override
	protected void onHover() {
		// TODO Auto-generated method stub
		
	}

}
