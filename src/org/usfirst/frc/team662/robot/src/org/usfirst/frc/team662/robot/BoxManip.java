package org.usfirst.frc.team662.robot;

//Import code for Xbox Controller, limit switches, and talons
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class BoxManip implements Component 
{
	//Create states for top, middle, and bottom (for arms)
	final static int TOP = 1;
	final static int MIDDLE = 0;
	final static int BOTTOM = -1;
	final double LEFT_DEADZONE = .15;
	final double RIGHT_DEADZONE = .15;	
	//Create a final static double variable for speed and a double variable, also for speed
	//Having two lets us use them as multiples of each other
	final static double SPEED = .25;
	double speed = 0;
	
	//Creates solenoid for arm grabbers.
	Solenoid armSolenoid = new Solenoid(RobotMap.SOLENOID_PORT); //FIX
	
	//Create the TalonSRX for the manipulator
	WPI_TalonSRX chainMotor = new WPI_TalonSRX(RobotMap.CHAIN);
	WPI_TalonSRX arm = new WPI_TalonSRX(RobotMap.ARM);
	
	//Create limit switches
	public DigitalInput limitSwitchTop = new DigitalInput(RobotMap.LIMIT_TOP);
	public DigitalInput limitSwitchMiddle = new DigitalInput(RobotMap.LIMIT_MIDDLE);
	public DigitalInput limitSwitchBottom = new DigitalInput(RobotMap.LIMIT_BOTTOM);
	
	//Create x-box controller object
	XboxController manipController = new XboxController(1);
	
	//Create an isTraveling boolean (true/false) variable
	boolean isTraveling = false;
	
	//Create a double variable for currentLocation of arms
	double currentLocation = BOTTOM;
	
	public void update()
	{
		//Make boolean (true/false) variables for each limit switch
		//When switch is pressed, variable is true; when not pressed, it is false
		boolean topLimit = !limitSwitchTop.get();
		boolean middleLimit = !limitSwitchMiddle.get();
		boolean bottomLimit = !limitSwitchBottom.get();
		
		double armSpeed = manipController.getRawAxis(5);
		
//MOVE ARMS ACCORDINGLY WHEN X, Y, OR A BUTTONS ARE PRESSED.		
		//When the "Y" button is pressed and the arms aren't currently moving, move them to top.
		if(manipController.getRawButton(XboxMap.Y) && currentLocation != TOP && !isTraveling)
		{
			speed = SPEED;
			isTraveling = true;
			currentLocation = TOP;
		}
		
		//When "X" button is pressed and the arms aren't moving, move them to middle.
		if(manipController.getRawButton(XboxMap.X)  && !isTraveling)
		{
			//If you're currently at the top, move down.
			if(currentLocation == TOP)
			{
				speed = -SPEED;
				isTraveling = true;
				currentLocation = MIDDLE;
			}
					
			//If you're currently at the bottom, move up.
			if(currentLocation == BOTTOM)
			{
				speed = SPEED;
				isTraveling = true;
				currentLocation = MIDDLE;
			}
		}
				
		//When the "A" button is pressed and the arms aren't currently moving, move them to bottom.
		if(manipController.getRawButton(XboxMap.A) && currentLocation != BOTTOM && !isTraveling)
		{
			speed = -SPEED;
			isTraveling = true;
			currentLocation = BOTTOM;
		}
		
		//SOLENOIDS
		//When the "B" button is pressed, the arms will release the box. Might need to change this once we know whether release is
		//when the solenoid is true or false. Currently, we are assuming that the solenoid will release when it is false.
		if(manipController.getRawButton(XboxMap.B)) 
		{
			armSolenoid.set(false);
		}
		//The solenoid's default state should always be closed, to hold onto the box.
		else
		{
			armSolenoid.set(true);
		}
		
//ARMS STOP MOVING WHEN THEY REACH THEIR DESTINATION
		if(isTraveling)
		{
			//If top limit switch is pressed and the arms were going to the top, stop the motors
			if(topLimit && currentLocation == TOP)
			{
				speed = 0;
				isTraveling = false;
					
			}
			
			//If middle limit switch is pressed and the arms were going to the middle, stop the motors
			if(middleLimit && currentLocation == MIDDLE)
			{
				speed = 0;
				isTraveling = false;
			}
			
			//If bottom limit switch is pressed and the arms were going to the bottom, stop the motors
			if(bottomLimit && currentLocation == BOTTOM)
			{
				speed = 0;
				isTraveling = false;
			}
		}
		
		// Check for the input in the right Joystick
		
		if (Math.abs(armSpeed) > RIGHT_DEADZONE) {
			arm.set(armSpeed);
		}
		
		//Sets the speed of the motor
		chainMotor.set(speed);
	}
	
	public void autoUpdate()
	{
		
	}
	
	public void disable()
	{
		
	}
}
