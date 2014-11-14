package sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import engine.Engine;
import engine.manager.SpriteManager;

/**
 * Class defines a (Possibly animated) image of a gameObject
 * Sprites are drawn from spriteSheets which can have multiple rows of animations
 * Each column is a frame of the row that contains it
 * Each row in itself is it's own animation
 * @author Nex
 *
 */
public class Sprite {

	//Attributes
	private BufferedImage spriteSheet;
	private Queue <AnimationInstruction> animationQueue;
	private int numRows;
	private int[] numColumns;
	private int currentRow, currentColumn;
	private int frameWidth, frameHeight;
	private int frameXPos, frameYPos;
	private boolean repeating;
	private boolean playing;
	
	//Accessors / Modifiers
	/**
	 * Gets the frame width of this sprite
	 * @return The width of a single frame in the spritesheet
	 */
	public int getFrameWidth(){
		return frameWidth;
	}
	
	/**
	 * Gets the frameHeight of this sprite
	 * @return The height of a single frame in the spriteSheet
	 */
	public int getFrameHeight(){
		return frameHeight;
	}
	
	/**
	 * Gets the current row of the frame in the spriteshet
	 * @return The current row of the current frame in the spritesheet
	 */
	public int getCurrentRow(){
		return currentRow;
	}
	
	/**
	 * Constructs a Sprite
	 * @param spriteSheet Image to draw frames from
	 * @param numRows Number of rows of animations
	 * @param numColumns Number of columns / frames in each row / animation
	 * @param frameWidth Width of each frame
	 * @param frameHeight Height of each frame
	 */
	public Sprite(BufferedImage spriteSheet, int numRows, int[] numColumns, int frameWidth, int frameHeight) {
		this.spriteSheet = spriteSheet;
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		currentRow = 0;
		currentColumn = 0;
		repeating = false;
		
		animationQueue = new LinkedList<AnimationInstruction>();
		
		setFrame();
	}
	
	/**
	 * Sets the position of the frame based on the current
	 * Row and column of spritesheet being drawn
	 */
	private void setFrame(){
		frameXPos = currentColumn * frameWidth;
		frameYPos = currentRow * frameHeight;
	}
	
	/**
	 * Returns whether a particular row in a spritesheet is already playing
	 * @param row determines whether this row is playing
	 * @return Whether specified row is playing.
	 */
	public boolean isPlaying(int row){
		return currentRow == row;
	}
	
	public void playAnimation(int row, boolean repeat){
		currentRow = row;
		this.repeating = repeat;
		currentColumn = 0;
	}
	
	
	/**
	 * Queues an animation to be played
	 * 
	 * Creates a set of {@link AnimationInstruction}s to be added to the animationQueue
	 * And enqueues the set of instructions
	 * 
	 * @param row The row of the animation to play
	 * @param repeat Should this animation repeat so long as no others are queued
	 */
	public void queueAnimation(int row, boolean repeat){
		//Create instruction set
		queueAnimation(new AnimationInstruction(row, repeat));
	}
	
	private void queueAnimation(AnimationInstruction instruction){
		animationQueue.add(instruction);
		playing = true;
	}
	
	/**
	 * Queues an animation to be played only if nothing else is currently playing
	 * 
	 * @param row The row of the animation to play
	 * @param repeat Should this animation repeat so long as no others are queued
	 */
	public void queueAnimationIfNotBusy(int row, boolean repeat){
		if(!playing){
			queueAnimation(row, repeat);
		}
		
	}
	
	/**
	 * Queues an animation to be played only if it's not already queued
	 * or the currently playing row
	 * 
	 * @param row The row of the animation to play
	 * @param repeat Should this animation repeat so long as no others are queued
	 */
	public void queueAnimationIfNotQueued(int row, boolean repeat){
		
		//CHANGED DUE TO THE FACT THAT IT WAS TRYING TO SEE IF A COMPLETELY NEW INSTRUCTION WAS IN THE QUEUE
		boolean inQueue = false;
		for (AnimationInstruction inst : animationQueue) {
			if (inst.animationRowIndex == row && inst.repeatAnimation == repeat) {
				inQueue = true;
			}
		}
		
		if(!inQueue){
			queueAnimation(new AnimationInstruction(row, repeat));
		}
	}
	
	/**
	 * prematurely ends the currently playing animation by playing
	 * the next animation in the queue, or setting the current frame 
	 * to the final frame in the row if there is none.
	 */
	public void killAnimation(){
		if(animationQueue.size() > 0){
			//Dequeue next set of instructions
			AnimationInstruction instructionSet = animationQueue.poll();
			//Load next animation
			currentRow = instructionSet.animationRowIndex;
			repeating = instructionSet.repeatAnimation;
			
			//Set column to 0 to start animation from beginning
			currentColumn = 0;
		}
		else{
			currentColumn = numColumns[currentRow] - 1;
		}
	}
	
	
	/**
	 * Updates the sprite
	 * 
	 * Get the change in frames since last update from {@link SpriteManager}
	 * Increment currentColumn by change in frames
	 * If the final column of this animation has already been reached and there is something in the animationQueue
	 * 		Start next set of {@link AnimationInstruction}s in animationQueue
	 * If there is nothing in the animationQueue, and this animation is set to repeat
	 * 		Back to column 1 of current animation
	 * If this animation is NOT set to repeat and there is nothing left in the animationQueue
	 * 		Do nothing. Keep displaying the current frame
	 * 
	 * Finally, call setFrame to move frame to (possibly) new image
	 */
	public void update(){
		//Get reference to sprite manager
		SpriteManager manager = (SpriteManager)Engine.currentInstance.getManager(Engine.Managers.SPRITEMANAGER);
		
		//Get number of frames that passed
		double exactDF = manager.getDeltaFrames();
		if(exactDF >= 1){
			int dF = 0;

			dF = (int) Math.floor(exactDF);
			manager.flagDeltaFramesReset();
			
			//If the current column is the last column
			if(currentColumn == numColumns[currentRow] - 1){
				//If there is another set of drawing instructions ready
				if(animationQueue.size() > 0){				
					//Dequeue next set of instructions
					AnimationInstruction instructionSet = animationQueue.poll();
					//Load next animation
					currentRow = instructionSet.animationRowIndex;
					repeating = instructionSet.repeatAnimation;
					
					//Set column to 0 to start animation from beginning
					currentColumn = 0;
				}
				//Else there is nothing queued, if the current animation is set to repeat
				else if(repeating){
					//Set column to 0 to start animation from beginning
					currentColumn = 0;
				}
				//There is nothing else queued and no animation is repeating
				else
				{
					playing = false;
				}
			}
			//Else not at last column of animation yet
			else{
				//If the current column + the change in frames is still not greater than the last column
				if(currentColumn + dF < numColumns[currentRow]){
					//Increment the current column by the number of frame changes
					currentColumn += dF;
				}
				//Else, the current column + the changes in frames results in an out of bounds frame
				else{
					//Set current column to last column
					currentColumn = numColumns[currentRow] - 1;
				}
			}
			
			//Finally, once frame variables are ready, set the frame
			setFrame();
			
		}
		
	}
	
	/**
	 * Draws the current frame of this sprite
	 * @param g2d Reference to renderer to draw sprite with
	 */
	public void draw(Graphics2D g2d, int xPos, int yPos, int width, int height){
		//Draw the spriteSheet
		g2d.drawImage(spriteSheet,
				(int)(xPos - width / 2.0), (int)(yPos - height / 2.0), (int)(xPos + width / 2.0), (int)(yPos + height / 2.0), 
				frameXPos, frameYPos, frameXPos + frameWidth, frameYPos + frameHeight, null);
	}

}
