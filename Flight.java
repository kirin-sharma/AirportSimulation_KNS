import java.util.TimerTask;
import java.util.Timer;

/**
 * This class represents a unique flight
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class Flight implements Comparable<Flight>
{

	private int flightID; // unique flight id
	private double distanceToAirport; // distance of flight to destination airport in kilometers
	private boolean hasEmergency; // whether the flight is experiencing an emergency
	private final double speed; // the speed of the flight in kilometers per minute (avg is about 15km/min for planes)
	private Timer t; // Timer to be utilized for self-updating
	
	/**
	 * Preferred constructor initializes instance variables
	 * Emergency status will always begin as false, and speed is constant for all flights
	 * @param id the id of the particular flight
	 * @param distance the distance of the current flight to the destination airport
	 */
	public Flight(int id, double distance)
	{
		flightID = id;
		distanceToAirport = distance;
		hasEmergency = false;
		speed = 15;
	} // end default constructor
	
	/**
	 * Updates the flight's distance from the airport if it is not already there
	 * Additionally, there is a 1% chance that the flight will develop an emergency
	 */
	public void updateFlight()
	{
		// make flight develop emergency 1% of the time
		if((int) (Math.random() * 99 + 1) == 1)
			setEmergency(true);
		if(distanceToAirport != 0)
		{
			if(distanceToAirport - speed < 0)
				distanceToAirport = 0;
			else
				distanceToAirport -= speed;
		}
		else
			stopUpdating();
	} // end updateFlight

	/**
	 * Assigns a new fixed-rate task to a timer, to start updating the flight
	 */
	public void startUpdating()
	{
		t = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				updateFlight();
			}
		};
		t.scheduleAtFixedRate(task, 0, 2500);
	} // end startUpdating

	/**
	 * Cancels the timer updating the flight
	 */
	public void stopUpdating()
	{
		t.cancel();
	} // end stopUpdating

	/**
	 * Getter for flightID
	 * @return the flightID
	 */
	public int getFlightID() 
	{
		return flightID;
	} // end getFlightID

	/**
	 * Getter for distanceToAirport
	 * @return the distanceToAirport
	 */
	public double getDistanceToAirport() 
	{
		return distanceToAirport;
	} // end getDistanceToAirport

	/**
	 * Setter for distanceToAirport
	 * @param distanceToAirport the distanceToAirport to set
	 */
	public void setDistanceToAirport(double distanceToAirport) 
	{
		this.distanceToAirport = distanceToAirport;
	} // end setDistanceToAirport
	
	/**
	 * Getter for hasEmergency
	 * @return whether the flight has an emergency
	 */
	public boolean hasEmergency()
	{
		return hasEmergency;
	} // end hasEmergency
	
	/**
	 * Setter for hasEmergency
	 * @param b the boolean value to set hasEmergency to
	 */
	public void setEmergency(boolean b)
	{
		hasEmergency = b;
	} // end setEmergency
	
	/**
	 * Getter for speed
	 * @return the speed
	 */
	public double getSpeed() 
	{
		return speed;
	} // end getSpeed

	@Override
	/**
	 * Override of toString returns the flight object as a string
	 */
	public String toString() 
	{
		return "Flight [flightID=" + flightID + ", distanceToAirport=" + distanceToAirport + ", hasEmergency="
				+ hasEmergency + "]";
	} // end toString

	@Override
	/**
	 * Compares one flight to another based on distance and flightIDs
	 * If both or neither have an emergency, their distance will first be considered, and then their flightIDs
	 * @return 1 if the flight has lower priority than f, -1 if flight has higher priority than f
	 */
	public int compareTo(Flight f) 
	{	
		// Compare flight distances, then flightIDs if necessary
		if(this.getDistanceToAirport() != f.getDistanceToAirport())
		{
			if(this.getDistanceToAirport() < f.getDistanceToAirport())
				return -1;
			else
				return 1;
		}
		else
		{
			if(this.getFlightID() > f.getFlightID())
				return -1;
			else
				return 1;
		}
	} // end compareTo
	
} // end class
