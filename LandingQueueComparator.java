import java.util.Comparator;

/**
 * This class contains a comparator to be utilized for the landing priority queue in the airport class
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class LandingQueueComparator implements Comparator<Flight> 
{

	@Override
	/**
	 * Compares one flight to another based on emergencies, then distances, then flightIDs
	 * If both or neither have an emergency, their distance will first be considered, and then their flightIDs
	 * @return 1 if the f1 > f2, -1 if f1 < f2
	 */
	public int compare(Flight f1, Flight f2) 
	{
		// Check if only one flight has an emergency, determine
		if(f2.hasEmergency() && !f1.hasEmergency())
			return 1;
		else if(!f2.hasEmergency() && f1.hasEmergency())
			return -1;

		// Compare flight distances, then flightIDs if necessary
		if(f2.getDistanceToAirport() != f1.getDistanceToAirport())
		{
			if(f2.getDistanceToAirport() < f1.getDistanceToAirport())
				return -1;
			else
				return 1;
		}
		else
		{
			if(f2.getFlightID() > f1.getFlightID())
				return -1;
			else
				return 1;
		}	

	} // end compare

	
	
} // end class
