import java.io.IOException;
import java.util.TimerTask;

/**
 * This class contains the logic detailing what the application should do at
 * each timestep
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class Timestep extends TimerTask
{

	private Airport airport; // The airport to simulate
	private int numFlights; // The number of flights created
	private int count; // The iteration the timestep is currently on
	
	/**
	 * Default constructor initializes all instance variables
	 */
	public Timestep()
	{
		airport = new Airport();
		numFlights = 1;
		count =  1;
	} // end default constructor
	
	@Override
	/**
	 * Override of the run method, calling various methods to execute in the timestep
	 */
	public void run() 
	{	
		// Clear the screen
		clear();
		airport.getClearedFlights().clear();
		
		// Check if any flights in air are ready to land, add them to landing queue
		addToLandingQueue();
		
		// if there are flights ready to land, find an open runway and add to it
		addToRunways();
		
		// create a flight 25% of the time
		if((int) ((Math.random() * 99) + 1) <= 25)
			createFlight();
		
		airport.getClearedFlights().addAll(airport.getRunway1().getClearedIDs());
		airport.getClearedFlights().addAll(airport.getRunway2().getClearedIDs());
		airport.getClearedFlights().addAll(airport.getRunway3().getClearedIDs());

		// Run 60 times, print final data
		if(count >= 59)
			printFinalData();
		
		printCurrentData();
		
		count++;
	} // end run
	
	/**
	 * If there are any planes that are ready to land, poll them from the approach queue
	 * and add them to the landing queue
	 */
	private void addToLandingQueue()
	{
		while(!airport.getApproachQueue().isEmpty())
		{
			if(airport.getApproachQueue().peek().getDistanceToAirport() == 0)
			{
				airport.addToLandingQueue(airport.getApproachQueue().poll());
			}
			else
				break;
		}
	} // end addToLandingQueue
	
	/**
	 * If there are any flights ready to land, check if a runway is open and assign the flight to the runway
	 */
	private void addToRunways()
	{
		while(!airport.getLandingQueue().isEmpty())
		{
			if(airport.getRunway1().isClear())
				airport.getRunway1().setCurrentLanding(airport.getLandingQueue().poll());
			else if(airport.getRunway2().isClear())
				airport.getRunway2().setCurrentLanding(airport.getLandingQueue().poll());
			else if(airport.getRunway3().isClear())
				airport.getRunway3().setCurrentLanding(airport.getLandingQueue().poll());
			else
				break;
		}
	} // end addToRunways
	
	/**
	 * Creates a flight with a random distance from 50 to 300 km, and appends to approach queue
	 */
	private void createFlight()
	{
		// distance of 50 to 300
		int distance = (int) ((Math.random() * 299) + 50);
		Flight f = new Flight(numFlights, distance);

		// add the flight to the approach queue
		airport.addToApproachQueue(f);
		numFlights++;
		f.startUpdating();
	} // end createFlight
	
	/**
	 * Prints the final data: flights in air and ready to land,
	 * the runways, and the cleared flights, then terminates execution
	 */
	private void printFinalData()
	{
		printCurrentData();
		cancel();
		System.exit(0);
	} // end printFinalData
	
	/**
	 * Displays the statuses of all of the flights and runways
	 */
	private void printCurrentData()
	{
		// Prints the approaching flights
		airport.printApproachingFlights();
		
		// Prints the flights waiting to land
		airport.printReadyToLand();
		
		// Prints the flights on the runways
		System.out.println();
		airport.printFlightsOnRunway();
		
		System.out.println("\n\n");
		printClearedFlights();
	} // end printCurrentData
	
	/**
	 * Prints the flight IDs that have landed and cleared the runways
	 */
	private void printClearedFlights()
	{
		int count = 0;
		System.out.println("\t\t  Flights Landed and Cleared");
		System.out.println("       -----------------------------------------------");
		System.out.print("  \t");
		for(Integer i : airport.getClearedFlights())
		{
			System.out.print("Flight " + i + "\t");
			count++;
			if(count % 3 == 0) System.out.print("\n\t");
		}
		
	} // end printClearedFlights

	/**
	 * Clears the command prompt screen of all text
	 */
	@SuppressWarnings("deprecation")
	private static void clear()
	{
	    try
	    {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	} // end clear
	
} // end class
