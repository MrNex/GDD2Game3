package engine.manager;

public class TimeManager extends Manager {

	//Attributes
	private long startTime;
	private long nanoSecondsSinceStart;
	private long nanoSecondsSinceLastUpdate;
	private long nanoSecondsSinceArray[];
	private int sampleIndex;
	private long avgNanoSecondsSince;
	
	//accessors / modifiers
	public long getStartTime(){
		return startTime;
	}
	
	public long getNanoSecondsSinceStart(){
		return nanoSecondsSinceStart;
	}
	
	/**
	 * Returns the change in milliseconds since last gameloop
	 * @return msSinceLastUpdate
	 */
	public long getNanoSecondsSinceLastUpdate(){
		return nanoSecondsSinceLastUpdate;
	}
	
	/**
	 * Returns the average change in nanoseconds over the last 100 gameloops
	 * @return the average change in nanoseconds over last 100 gameloops
	 */
	public long getAvgNanoSecondsPassed(){
		return avgNanoSecondsSince;
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
		startTime = System.nanoTime();
		nanoSecondsSinceLastUpdate = 0;
		nanoSecondsSinceStart = 0;
		nanoSecondsSinceArray = new long[100];
		sampleIndex = 0;
		avgNanoSecondsSince = 0;
	}

	/**
	 * Updates the time manager
	 */
	@Override
	public void update() {
		long currentNanoSecond = System.nanoTime();
		nanoSecondsSinceLastUpdate = currentNanoSecond - (nanoSecondsSinceStart + startTime);
		if(avgNanoSecondsSince == 0){
			avgNanoSecondsSince = nanoSecondsSinceLastUpdate;
		}
		nanoSecondsSinceStart += nanoSecondsSinceLastUpdate;		
		
		nanoSecondsSinceArray[sampleIndex] = nanoSecondsSinceLastUpdate;
		sampleIndex++;
		if(sampleIndex == 100){
			avgNanoSecondsSince = 0;
			for(int i = 0; i < 100; i++){
				avgNanoSecondsSince += nanoSecondsSinceArray[i];
			}
			
			avgNanoSecondsSince /= 100;
			sampleIndex = 0;
		}
	}

}
