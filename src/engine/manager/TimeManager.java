package engine.manager;

public class TimeManager extends Manager {

	//Attributes
	private long startTime;
	private long microSecondsSinceStart;
	private long microSecondsSinceLastUpdate;
	
	//accessors / modifiers
	public long getStartTime(){
		return startTime;
	}
	
	public long getMicroSecondsSinceStart(){
		return microSecondsSinceStart;
	}
	
	/**
	 * Returns the change in milliseconds since last gameloop
	 * @return msSinceLastUpdate
	 */
	public long getMicroSecondsSinceLastUpdate(){
		return microSecondsSinceLastUpdate;
	}
	
	/**
	 * Creates a Time Manager
	 */
	public TimeManager() {
		super();
	}

	/**
	 * Initializes all member variables
	 */
	@Override
	public void init() {
		startTime = System.nanoTime() / 1000;
		microSecondsSinceLastUpdate = 0;
		microSecondsSinceStart = 0;
	}

	/**
	 * Updates the time manager
	 */
	@Override
	public void update() {
		long currentMicroSecond = System.nanoTime() / 1000;
		microSecondsSinceLastUpdate = currentMicroSecond - (microSecondsSinceStart + startTime);
		microSecondsSinceStart += microSecondsSinceLastUpdate;		
	}

}
