import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

/**
 * This class represents a runway
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class Runway 
{
	private boolean isClear; // whether the runway is cleared or not
	private double timeToClear; // how much time in minutes the runway has left to clear
	private Flight currentLanding; // the flight that is currently occupying the runway
	private ArrayList<Integer> clearedIDs; // holds the flight IDS of all flights cleared from the runway
	private Timer t; // Timer for self-updating runway000
	
	/**
	 * Default constructor initializes all instance variables
	 */
	public Runway()
	{
		isClear = true;
		timeToClear = 0;
		currentLanding = null;
		clearedIDs = new ArrayList<Integer>();
	} // end default constructor

	/**
	 * Updates the runway's time to clear by 2.5 minutes
	 */
	public void updateRunway()
	{
		if(timeToClear == 0)
			stopUpdating();
		else
			timeToClear -= 2.5;
	} // end updateRunway

	/**
	 * Updates the runway's time to clear every 2.5 seconds
	 */
	public void startUpdating()
	{
		t = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run()
			{
				updateRunway();
			}
		};
		t.scheduleAtFixedRate(task, 2500, 2500);
	} // end startUpdating

	/**
	 * Cancels the timer automatically updating the runway, sets the current landing to null, and
	 * asserts that the runway is clear
	 */
	public void stopUpdating()
	{
		t.cancel();
		clearedIDs.add(currentLanding.getFlightID());
		currentLanding = null;
		isClear = true;
	} // end stopUpdating

	/**
	 * Setter for isClear
	 * @param isClear the boolean value to set
	 */
	public void setClear(boolean isClear) 
	{
		this.isClear = isClear;
	} // end setClear
	
	/**
	 * Getter for isClear
	 * @return if runway is clear or not
	 */
	public boolean isClear() 
	{
		return isClear;
	} // end isClear

	/**
	 * Getter for timeToClear
	 * @return the timeToClear the runway
	 */
	public double getTimeToClear() 
	{
		return timeToClear;
	} // end getTimeToClear
	
	/**
	 * Setter for timeToClear
	 * @param timeToClear the time to set until runway is clear
	 */
	public void setTimeToClear(double timeToClear) 
	{
		this.timeToClear = timeToClear;
	} // end setTimeToClear

	/**
	 * Getter for currentLanding
	 * @return the current flight on the runway
	 */
	public Flight getCurrentLanding() 
	{
		return currentLanding;
	} // end getCurrentLanding

	/**
	 * Setter for currentLanding
	 * @param currentLanding the flight that is currently on the runway
	 */
	public void setCurrentLanding(Flight currentLanding) 
	{
		this.currentLanding = currentLanding;
		isClear = false;
		timeToClear = 5;
		startUpdating();
	} // end setCurrentLanding
	
	/**
	 * Getter for getClearedIDs
	 * @return the arraylist containing the list of flight ids that have cleared the runway
	 */
	public ArrayList<Integer> getClearedIDs()
	{
		return clearedIDs;
	} // end getClearedIDs
	
} // end class
