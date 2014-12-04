package state.object;

import java.awt.Color;
import java.awt.Graphics2D;

public class DoorState extends ObjectState{

	private boolean triggered;
	
	public DoorState(){
		super();
		triggered = false;
	}
	
	public void isTriggered(boolean t){
		triggered = t;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(!triggered){
			attachedTo.setSolid(true);
			attachedTo.setColor(new Color(0,0,255));
		}
		else{
			attachedTo.setSolid(false);
			attachedTo.setColor(new Color(0,0,255,50));
		}
		triggered = false;
	}

	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
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
