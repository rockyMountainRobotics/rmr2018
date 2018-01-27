package org.usfirst.frc.team662.robot;

//Import Statements for talons and X-box Controllers (so we can use code for them)
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.XboxController;


public class WinchManip implements Component 
{
	//Create talons for lifting the winch and pulling the winch
	WPI_TalonSRX liftWinchSet = new WPI_TalonSRX(RobotMap.WINCH_SET);
	WPI_TalonSRX pullWinch = new WPI_TalonSRX(RobotMap.WINCH_PULL);
	
	//Create XboxController object for the controller
	XboxController manipController = new XboxController(1);
	
	
	@Override
	public void update() 
	{
		//Set the liftWinchSet variable according to how far the joystick is pushed
		double speed = manipController.getRawAxis(1);
		liftWinchSet.set(speed);
		
		//If trigger is pushed and greater than dead zone, pull winch. Otherwise, don't.
		if (manipController.getRawAxis(2)>.1)
		{
			pullWinch.set(.25);
		}
		else
		{
			pullWinch.set(0);
		}
	}
	
	
	@Override
	public void autoUpdate()
	{
		
	}
	
	
	@Override
	public void disable()
	{
		//When disabling, this turns the motors off.
		liftWinchSet.set(0);
		pullWinch.set(0);
	}
}
