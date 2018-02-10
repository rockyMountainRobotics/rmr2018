package org.usfirst.frc.team662.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive implements Component
{
	
	final double MULTIPLIER = .4;
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    
    DifferentialDrive differentialDrive;
    
    
	
	public Drive()
	{
		leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
	    rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
	    rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);
	    
	    leftFront.setInverted(true);
        leftBack.setInverted(true);	
        
        leftDrive = new SpeedControllerGroup(leftFront, leftBack);
        rightDrive = new SpeedControllerGroup(rightFront, rightBack);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
        
        
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		//differentialDrive.tankDrive(MULTIPLIER *(Robot.driveController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( Robot.driveController.getY(GenericHID.Hand.kLeft)));
		differentialDrive.arcadeDrive(MULTIPLIER *(Robot.driveController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( -Robot.driveController.getX(GenericHID.Hand.kLeft)));
	}

	@Override
	public void autoUpdate() 
	{
		// TODO Auto-generated method stub
		String gameData;
		
		//Gets the game data from the game server, then stores it in gameData.
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (Robot.driftSpeed.equals("stop") )
			{
			rightFront.set(.0);
			leftFront.set(.0);
			rightBack.set(.0);
			leftBack.set(.0);
			}
		if (Robot.driftSpeed.equals("slow") )
			{
			rightFront.set(.3);
			leftFront.set(.3);
			rightBack.set(.3);
			leftBack.set(.3);
			}
		if (Robot.driftSpeed.equals("crusingspeed") )
			{
			rightFront.set(.5);
			leftFront.set(.5);
			rightBack.set(.5);
			leftBack.set(.5);
			}
		if (Robot.driftSpeed.equals("fullspeedahead") )
		{
			rightFront.set(.9);
			leftFront.set(.9);
			rightBack.set(.9);
			leftBack.set(.9);
		}
		if (Robot.driftSpeed.equals("listport") )
		{
			rightFront.set(.4);
			leftFront.set(.5);
			rightBack.set(.4);
			leftBack.set(.5);
		}	
		if (Robot.driftSpeed.equals("liststarboard") )
		{
			rightFront.set(.5);
			leftFront.set(.4);
			rightBack.set(.5);
			leftBack.set(.4);
		}	
		//Checks whether gameData is left or right, and sets the motors accordingly.
		if (gameData.charAt(0) == 'L')
		{
			switch(Robot.autoState)
			{
			//Robot turning
			case 'a':
				//Turn on right motors so robot turns left
				rightFront.set(.5);
				rightBack.set(.5);
				break;
				
			//Robot moving forward	
			case 'b':
				//Turn on all motors so robot goes forward
				rightFront.set(.5);
				leftFront.set(.5);
				rightBack.set(.5);
				leftBack.set(.5);
				break;
				
			case 'c':
				leftFront.set(.5);
				leftBack.set(.5);
				break;
				
			case 'd':
				//Manip Code
				break;
				
			}
		} 
		
		else 
		{
			
			switch(Robot.autoState)
			{
			//Robot turning
			case 'a':
				//Turn on left motors so robot turns right
				leftFront.set(.5);
				leftBack.set(.5);
				break;
				
			//Robot moving forward
			case 'b':
				//Turn on all motors so robot moves forward
				rightFront.set(.5);
				leftFront.set(.5);
				rightBack.set(.5);
				leftBack.set(.5);
				break;
				
			case 'c':
				rightFront.set(.5);
				rightBack.set(.5);
				break;
				
			case 'd':
				//Manip Code
				break;
			}
		}
		
		//When we are in the first state, (on the alliance wall area)
		
		//	{ Turn the robot here, then change the case to "b"}
		
		//if(autoUpdateS.equalsIgnoreCase("b"))
		//	{ Move the robot forward on the angle we turned until a certain lidar value is obtained}
		//	then change to case "c"
		
		//if(autoUpdateS.equalsIgnoreCase("c"))
		//	{turn the robot until it is parallel with the switch, then change the case to "d"}
		
	}

	@Override
	public void disable() 
	{
		// TODO Auto-generated method stub
		
	}
}
