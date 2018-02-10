package org.usfirst.frc.team662.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive implements Component{
	
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
	public void update() {
		// TODO Auto-generated method stub
		//differentialDrive.tankDrive(MULTIPLIER *(Robot.driveController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( Robot.driveController.getY(GenericHID.Hand.kLeft)));
		differentialDrive.arcadeDrive(MULTIPLIER *(Robot.driveController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( -Robot.driveController.getX(GenericHID.Hand.kLeft)));
	}

	@Override
	public void autoUpdate() {
		// TODO Auto-generated method stub
		String gameData;
		
		//Gets the game data from the game server, then stores it in gameData.
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//Checks whether gameData is left or right, and sets the motors accordingly.
		if (gameData.charAt(0) == 'L')
		{
			// left motors
			leftFront.set(.5);
			leftBack.set(.5);
		} 
		
		else 
		{
			// right motors
			rightFront.set(.5);
			rightBack.set(.5);
		}
		
		//When we are in the first state, (on the alliance wall area)
		//if(autoUpdateS.equalsIgnoreCase("a"))
		//	{ Turn the robot here, then change the case to "b"}
		
		//if(autoUpdateS.equalsIgnoreCase("b"))
		//	{ Move the robot forward on the angle we turned until a certain lidar value is obtained}
		//	then change to case "c"
		
		//if(autoUpdateS.equalsIgnoreCase("c"))
		//	{turn the robot until it is parallel with the switch, then change the case to "d"}
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
}
