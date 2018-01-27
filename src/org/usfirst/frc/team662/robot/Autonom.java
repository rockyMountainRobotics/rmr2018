package org.usfirst.frc.team662.robot;

public class Autonom 
{
	
	//declares variables
	double dt, dt1; 			// dist to t0(left edge) or t1(left edge)
	double ht, ht1; 			// height of image to t0, or t1 respectively
	double a;		 			// something idk
	double Xpos, Ypos;			// position X and Y obviously, lol ur mum gay
	final double Xl = 20.125; 	// Xl idk but its 20.125
	double angleG; 				// angle G? determines pos? spell icup
	
	public Autonom()
	{
		//calculates distance based off of height of image
		dt = 406/ht; 	
		dt1 = 406/ht1;

		//red - generalized form(i don't know what for tho)
		a = Math.acos((-Math.pow(dt, 2) - Math.pow(dt1, 2) - 36) / (2*(6 * dt1)));
		
		//green - determines position
		Xpos = (a/90) * dt1 + Xl;			//Xpos
		Ypos = (1 - a/90) * dt1;			//Ypos
		angleG = Math.asin(Xpos/Ypos);		//angle G (asin(Xpos/Ypos))
		
	}
}