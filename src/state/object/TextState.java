package state.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.ScreenManager;

/**
 * Defines an ObjectState which displays text centered on 
 * it's attached gameobject
 * 
 * @author Nex
 *
 */
public class TextState extends ObjectState {

	//Static Attributes
	private static int defaultFontSize = 24;
	private static Font defaultFont = new Font("Serif", Font.PLAIN, TextState.defaultFontSize);
	private static Color defaultFontColor = Color.black;

	//Attributes
	protected int fontSize;
	protected Font font;
	protected Color fontColor;
	protected String text;
	protected int textWidth, textHeight;
	protected boolean shifted;


	//Accessors / Modifiers
	public void setText(String newText){
		text = newText;
		measureString();
	}

	/**
	 * Constructs a textState
	 * 
	 * @param text Text to display
	 * @param shifted Is the text being displayed with a shifted coordinate system
	 * where 0, 0 is the center of the attached object.
	 */
	public TextState(String text) {
		super();

		//Set text to specified text
		this.text = text;

		//Set variables to default state

		fontSize = TextState.defaultFontSize;
		font = TextState.defaultFont;
		fontColor = TextState.defaultFontColor;

		//Measure the size of the string
		measureString();
	}


	/**
	 * Called upon attaching this state to a gameObject
	 * No preparations should need to be made here.
	 */
	@Override
	public void enter() {


	}

	/**
	 * Measures the length of the current displayString and sets the textWidth
	 * and textHeight properties to reflect this
	 */
	private void measureString(){
		//Get font metrics
		FontMetrics fMetrics = ((ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER)).getGraphics().getFontMetrics(font);
		textWidth = fMetrics.stringWidth(text);
		textHeight = fMetrics.getMaxAscent() - fMetrics.getMaxDescent();
	}

	/**
	 * This state does not need to update
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * Draws te display string centered on the attached object
	 * 
	 * Sets the font and font color of graphics renderer
	 * Draws string centered on object based on whether or not
	 * The current coordinate system is shifted
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		g2d.setFont(font);
		g2d.setColor(fontColor);

		g2d.drawString(text, 
				(int)(-textWidth / 2.0), 
				(int)(textHeight / 2.0));	

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
