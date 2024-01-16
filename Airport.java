import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * This class represents an airport with 3 runways
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class Airport
{
	private Runway runway1; // the first runway in the airport
	private Runway runway2; // the second runway in the airport
	private Runway runway3; // the third runway in the airport
	private PriorityQueue<Flight> landingQueue; // priority queue of all ready to land flights
	private PriorityQueue<Flight> approachQueue; // priority queue of all airborne flights
	private ArrayList<Integer> clearedFlights; // list of flight ids of all flights that have cleared the runways
	
	/**
	 * Default constructor initializes all instance variables
	 */
	public Airport()
	{
		runway1 = new Runway();
		runway2 = new Runway();
		runway3 = new Runway();
		landingQueue = new PriorityQueue<Flight>(new LandingQueueComparator());
		approachQueue = new PriorityQueue<Flight>();
		clearedFlights = new ArrayList<Integer>();
	} // end default constructor
	
	/**
	 * Adds a flight to the priority queue approachQueue
	 * @param f the flight to add to the priority queue
	 */
	public void addToApproachQueue(Flight f)
	{
		approachQueue.add(f);
	} // end addToApproachQueue
	
	/**
	 * Adds a flight to the priority queue landingQueue
	 * @param f the flight to add to the priority queue
	 */
	public void addToLandingQueue(Flight f)
	{
		landingQueue.add(f);
	} // end addToLandingQueue

	/**
	 * Prints all approaching flights to the screen in a formatted fashion
	 */
	public void printApproachingFlights()
	{
		System.out.println("\n\t\t\tApproaching Flights\n");
		System.out.printf("\t%5s       %15s     %8s", "Flight ID", "Distance To Airport", "Emergency?");
		System.out.println("\n       ----------------------------------------------------------------"); 
		
		if(getApproachQueue().isEmpty())
			System.out.println("          NONE");
		else
		{
			for(Flight f : getApproachQueue())
			{
				String s;
				if(f.hasEmergency())
					s = "Yes";
				else
					s = "No";
				
				System.out.format("\t%5s                  %5s              %5s", 
						f.getFlightID(), f.getDistanceToAirport(), s);
				System.out.println("\n       ----------------------------------------------------------------"); 
			}
		}
	} // end printApproachingFlights
	
	/**
	 * Prints all flights that are waiting to land in a formatted fashion
	 */
	public void printReadyToLand()
	{
		System.out.println("\n\t\t      Flights Waiting to Land\n");
		System.out.printf("\t%5s \t  %8s", "Flight ID", "Emergency?");
		System.out.println("\n       ----------------------------------------"); 
		
		if(getLandingQueue().isEmpty())
			System.out.println("          NONE");
		else
		{
			for(Flight f : getLandingQueue())
			{
				String s;
				if(f.hasEmergency())
					s = "Yes";
				else
					s = "No";
				
				System.out.format("\t%5s    \t      %5s", f.getFlightID(), s);
				System.out.println("\n       ----------------------------------------\n"); 
			}
		}
	} // end printReadyToLand
	
	/**
	 * Prints flights on each runway, if there are any in a formatted fashion
	 */
	public void printFlightsOnRunway()
	{
		System.out.println("\t\t\t   Runway 1\n");
		System.out.printf("\t%5s \t  %8s", "Flight ID", "Time To Clear");
		System.out.println("\n       ----------------------------------------"); 
		if(getRunway1().isClear())
			System.out.println("\t  EMPTY\n");
		else
		{
			System.out.print("\t   " + getRunway1().getCurrentLanding().getFlightID());
			System.out.println("\t\t  " + getRunway1().getTimeToClear() + " minutes\n");
		}
		
		System.out.println("\t\t\t   Runway 2\n");
		System.out.printf("\t%5s \t  %8s", "Flight ID", "Time To Clear");
		System.out.println("\n       ----------------------------------------"); 
		if(getRunway2().isClear())
			System.out.println("\t  EMPTY\n");
		else
		{
			System.out.print("\t   " + getRunway2().getCurrentLanding().getFlightID());
			System.out.println("\t\t  " + getRunway2().getTimeToClear() + " minutes\n");
		}
		
		System.out.println("\t\t\t   Runway 3\n");
		System.out.printf("\t%5s \t  %8s", "Flight ID", "Time To Clear");
		System.out.println("\n       ----------------------------------------");
		if(getRunway3().isClear())
			System.out.println("\t  EMPTY\n");
		else
		{
			System.out.print("\t   " + getRunway3().getCurrentLanding().getFlightID());
			System.out.println("\t\t  " + getRunway3().getTimeToClear() + " minutes\n");
		}
	} // end printFlightsOnRunway
	
	/**
	 * Getter for approachQueue
	 * @return the priority queue approachQueue
	 */
	public PriorityQueue<Flight> getApproachQueue()
	{
		return approachQueue;
	} // end getApproachQueue

	/**
	 * Getter for landingQueue
	 * @return the priority queue landingQueue
	 */
	public PriorityQueue<Flight> getLandingQueue()
	{
		return landingQueue;
	} // end getLandingQueue

	/**
	 * Getter for runway1
	 * @return the runway1
	 */
	public Runway getRunway1() 
	{
		return runway1;
	} // end getRunway1

	/**
	 * Getter for runway2
	 * @return the runway2
	 */
	public Runway getRunway2() 
	{
		return runway2;
	} // end getRunway2

	/**
	 * Getter for runway3
	 * @return the runway3
	 */
	public Runway getRunway3() 
	{
		return runway3;
	} // end getRunway3

	/**
	 * Getter for clearedFlights
	 * @return the clearedFlights ArrayList
	 */
	public ArrayList<Integer> getClearedFlights()
	{
		return clearedFlights;
	} // end getClearedFlights
	
} // end class
