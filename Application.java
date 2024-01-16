import java.util.Timer;

/**
 * This class simulates an airport for 2 and a half minutes of real time, simulating 2 and a half hours
 * @author Kirin Sharma
 * @version 1.0
 * CS 215
 * Airport Simulation Project
 */

public class Application 
{
	
	public static void main(String[] args)
	{
		
		Timer timer = new Timer();		
		timer.scheduleAtFixedRate(new Timestep(), 0, 2500);
		
	} // end main
	
} // end class
