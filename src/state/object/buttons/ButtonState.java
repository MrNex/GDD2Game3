package state.object.buttons;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.xml.soap.Text;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.InputManager;
import mathematics.Vec;
import state.object.ObjectState;
import state.object.TextState;

/**
 * Defines a class which has the ability to display text
 * And checks for the mouse to be clicked while the
 * attached object contains the mouse location.
 * 
 * If the attached object is clicked Action() is called.
 * IF the attached object contains the mouse but is not clicked,
 * Hover() is called.
 * 
 * @author Nex
 *
 */
public abstract class ButtonState extends TextState {

	//Attributes
	protected boolean pressed;
	
	
	//Accessors / modifiers
	/**
	 * Sets the button as being pressed so it will
	 * not register as pressed until the mouse is released
	 * 
	 * (Primarily used by PopStateButton to ensure when 
	 * swapping from a menu state to another menu state,
	 * buttons which appear where the PopStateButton was pressed
	 * do not get pressed)
	 */
	public void setPressed(){
		pressed = true;
	}
	
	/**
	 * Constructs a button state
	 * @param buttonText Text for the button to display
	 * @param shifted Is the object being drawn with a shifted coordinate system?
	 */
	public ButtonState(String buttonText) {
		super(buttonText);
		
		//Set button to be pressed by default
		pressed = true;
	}

	/**
	 * No special enter instruction needed
	 */
	@Override
	public void enter() {
		super.enter();
	}

	/**
	 * Updates the state of this button
	 * 
	 * So long as the button isn't already pressed
	 * Checks if the mouse is contained within the attached gameObject
	 * 	If true:
	 * 		Checks if mouse is being clicked:
	 * 		if true:
	 * 			Calls action method
	 * 		If false:
	 * 			Calls onHover method
	 * 
	 * If the button is pressedit will not be pressed again until
	 * the mouse is released.
	 */
	@Override
	public void update() {

		//Get reference to input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Managers.INPUTMANAGER);
		
		//If the button wasn't yet pressed
		if(!pressed){
			//Get mouse position
			Vec mousePos = input.getMousePosition();
			//If the button contains the mouse
			if(attachedTo.contains(mousePos.getComponent(0), mousePos.getComponent(1))){
				//System.out.println("Contains: " + mousePos.toString());
				//If the LMB is being pressed
				if(input.isMouseButtonPressed(MouseEvent.BUTTON1)){
					action();
					pressed = true;
				}
				else{
					onHover();
				}

			}
		}
		//Else if the button was pressed
		else{
			//Wait until the mouse is released to set the button back to unpressed
			if(!input.isMouseButtonPressed(MouseEvent.BUTTON1)){
				pressed = false;
			}
		}
	}
	
	/**
	 * The actionperformed when this button is clicked
	 */
	protected abstract void action();
	
	/**
	 * The action performed when this button is hovered over
	 */
	protected abstract void onHover();
	

	/**
	 * Draws the button text centered on the attached object,
	 * Buttons assume an un-altered coordinate system
	 * 
	 * 
	 * 
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		super.drawEffects(g2d);
	}

	/**
	 * No special exit instructions are needed for buttons
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
