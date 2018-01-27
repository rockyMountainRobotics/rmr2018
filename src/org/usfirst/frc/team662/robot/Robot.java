package org.usfirst.frc.team662.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends IterativeRobot {
    
	//Multiplier for the moters
	final double MULTIPLIER = .6;
	
	//Create the variables for the motors
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;
    
    
    //Create a speed controller group for each side of the driving code
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    //Declare a differential drive
    DifferentialDrive differentialDrive;

    //Public declaration of an xbox controller that will be used by other classes
    public static XboxController driveController;
    public static XboxController manipController;

    //String that allows us to determine which side of the field we are on (used later)
    String side;
    
    //Create a shifter on the robot
    Shifter compShifter = new Shifter();
    
    //Method that initializes the robot
	@Override
	public void robotInit() 
	{
		//Set the side string to the gamedata that we get at the beginning of the match
		side = DriverStation.getInstance().getGameSpecificMessage();
		
		//Instanciate the motors with the ports defined in RobotMap
		leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
        rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
        rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

        //Set the right side inverted so that the wheels go the same way
        rightFront.setInverted(true);
        rightBack.setInverted(true);
        
        //Instanciate the speed controllers
        leftDrive = new SpeedControllerGroup(leftFront, leftBack);
        rightDrive = new SpeedControllerGroup(rightFront, rightBack);

        //Differential drive :)
        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        //Instanciate the driveController to port 1 and ManipController to port 1
        driveController = new XboxController(0);
        manipController = new XboxController(1);
	}
	
	//not used
	public void teleopInt() {}
	
	//TeleopPeriodic() method that runs when we are driving the robot
	@Override
	public void teleopPeriodic() 
	{
		//if we're running the robot lol
		if (isOperatorControl() && isEnabled()) 
		{
			//update the shifter code
			compShifter.update();
			
			//Allows us to drive the robot :)
			differentialDrive.arcadeDrive(MULTIPLIER *(driveController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( -driveController.getX(GenericHID.Hand.kLeft)));
			
		}
	}
	
	//autonomousPeriodic() that runs during auton 
	public void autonomousPeriodic() 
	{
		// originally copied from 2018 game data java code
		
		//apparently we use this instead of side 
		String gameData;
		
		//gets the gameData
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//if the first character is left, move the left wheels (for testing)
		if (gameData.charAt(0) == 'L')
		{
			// left motors
			leftFront.set(.5);
			leftBack.set(.5);
		} 
		
		//otherwise, move the right wheels :)
		else 
		{
			// right motors
			rightFront.set(.5);
			rightBack.set(.5);
		}
		
	}

}