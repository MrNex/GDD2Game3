package engine.manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.GameObject;
import state.engine.EngineState;
import engine.Engine;
import engine.Engine.Managers;

/**
 * A {@link Manager} which takes care of drawing objects in
 * the engines current state to the screen. This includes camera
 * transformations and such other rendering related tasks.
 * @author Nex
 *
 */
public class ScreenManager extends Manager{

	//Attributes
	private JFrame window;
	private JPanel drawPanel;
	private Color backgroundColor;
	private int width, height;
	


	/**
	 * Constructs a ScreenManager
	 */
	public ScreenManager() {
		super();
	}
	
	//Accessors
	/**
	 * Get the window the program is running in
	 * @return The window containing the program
	 */
	public JFrame getWindow(){
		return window;
	}
	
	/**
	 * Gets the drawPanel the progrma is drawing on
	 * @return The panel containing all graphics.
	 */
	public JPanel getPanel(){
		return drawPanel;
	}
	
	
	/**
	 * Gets the graphics renderer of drawPanel
	 * @return An instance of the graphics renderer in drawPanel.
	 */
	public Graphics getGraphics(){
		return drawPanel.getGraphics();
	}

	
	/**
	 * Gets a percentage of the width of the screen
	 * @param percent The percent you want
	 * @return The specified percentage of the screenWidth
	 */
	public double getPercentageWidth(double percent)
	{
		return width / 100.0 * percent;
	}

	/**
	 * Gets a percentage of the height of the screen
	 * @param percent The percent of the height that you want
	 * @return The specified percentage of the screenHeight
	 */
	public double getPercentageHeight(double percent)
	{
		return height / 100.0 * percent;
	}
	
	/**
	 * Initializes all member variables of ScreenManager.
	 * Sets width and height of viewport,
	 * Creates the drawPanel with the drawing instructions
	 * TODO: Attaches input manager
	 */
	@Override
	public void init() {
		//Set internals
		width = 1200;
		height = 750;
		
		//SEt background color
		backgroundColor = Color.red;

		//Create the window
		window = new JFrame("Blank Engine");
		window.setSize(width, height);
		window.setResizable(false);

		//Create the panel
		drawPanel = new JPanel(){

			/**
			 * Set the drawing instructions for the panel
			 */
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				//Refresh screen
				g.setColor(backgroundColor);
				g.fillRect(0, 0, width, height);
				
				//If the engine is running
				if(Engine.currentInstance.isRunning()){
					//Cast to graphics2d
					Graphics2D g2d = (Graphics2D)g;

					//Construct camera coordinate system
					CameraManager cam = (CameraManager)Engine.currentInstance.getManager(Managers.CAMERAMANAGER);
					cam.constructCameraCoordinateSystem(g2d);

					//If there is a current state of the engine
					EngineState currentState = Engine.currentInstance.getCurrentState();
					if(currentState != null)
					{
						ArrayList<GameObject> drawList = currentState.getObjList();

						//For every game object in objects
						for(GameObject obj : drawList){
							obj.draw(g2d);
						}
					}

					
					//Destruct camera coordinate system
					cam.destructCameraCoordinateSystem(g2d);
				}


			}
		};

		drawPanel.setPreferredSize(new Dimension(800, 600));

		window.add(drawPanel);

		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Get listeners
		KeyListener listener = (KeyListener)Engine.currentInstance.getManager(Engine.Managers.INPUTMANAGER);
		MouseListener mlistener = (MouseListener)Engine.currentInstance.getManager(Engine.Managers.INPUTMANAGER);
		
		//Add input listeners
		drawPanel.addKeyListener(listener);
		drawPanel.addMouseListener(mlistener);
		//Set as focusable
		drawPanel.setFocusable(true);
	}

	/**
	 * Paints the screen
	 */
	@Override
	public void update() {
		drawPanel.repaint();
	}

}
