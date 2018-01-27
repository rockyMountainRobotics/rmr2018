package org.usfirst.frc.team662.robot;

public class Autonomous 
{
	
	//playing with Lidar
	//SweepJNI.construct("9", 16); 
	
	
	//VARIABLES
	//These two variables correspond to the rectangles of the reference tape, and their heights.
	double leftHeight = 0.0; //Corresponds to ht0
	double rightHeight = 0.0; //Corresponds to ht1
	
	//These two variables correspond to the left and right edges of the camera's view.
	double leftCamEdge = 0.0; //Corresponds to dt0
	double rightCamEdge = 0.0; //Corresponds to dt1
	
	//These two variables correspond to the x and y values of the robot's position.
	double roboYPos = 0.0; //Corresponds to ds
	double roboXPos = 0.0; //Corresponds to dx
	
	//This variable represents an angle necessary to calculate the position of the robot.
	double angle1 = 0.0; //Corresponds to a1
	
	//This variable represents the angle between the robot and the edge of the switch.
	double edgeAngle = 0.0;
	
	{
		//Calculates the edges of the camera based on a constant and the height of the reference tape; see board.
		leftCamEdge = 406/leftHeight;
		rightCamEdge = 406/rightHeight;
		
		//Calculates angle1 based off of the law of cosines; see board for equation in human terms.
		angle1 = Math.acos(-(((Math.pow(leftCamEdge, 2)) - (Math.pow(rightCamEdge, 2)) - 36)/(12 * rightCamEdge)));
		
		//Finds the robot's y position.
		roboYPos = (1-angle1/90) * rightCamEdge;
		//Finds the robot's x position. The 20.125 is in inches, and represents the distance between the left edge of the tape and 
		//the edge of the switch.
		roboXPos = (angle1/90) * rightCamEdge + 20.125;
		
		//This finds the angle between the robot and the edge of the switch.
		edgeAngle = Math.asin(roboXPos/roboYPos);
		
		//We need to turn the robot 41.6403 degrees to the left in order to line up with the edge of the switch.
		
		
	}
}
