package org.usfirst.frc.team662.robot;

import com.armabot.SweepJNI;
import com.armabot.SweepSample;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Robot extends IterativeRobot 
{
    //TODO put all of the drive stuff in the actual drive class
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;
    
    //Please comment what these are.
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    
    //Makes an array of scan data.
    SweepSample[] scanData;
	long ourScanner = 0;
	String gameData;
	
    
    //Creates a variable for states A, B, C, and D in Autonomous. These are used in the following
    //classes: Drive, LiDAR(if we keep this as its own class), BoxManip
    public static char autoState = 'a';
    public static String  driftSpeed = "stop";
    //States for Autonomous:
    //State A: Turning away from wall
    //State B: Moving forward from the alliance wall to the switch wall
    //State C: Touching the switch w/ one corner
    //State D: Touching the switch w/ one side (that's when we drop the cube)

    
    //Creates the gyro object. Initialization happens in robotInit
    public ADXRS450_Gyro gyro;
    
    public final double GYRO_RIGHT_ANGLE = 16.241;
    public final double GYRO_LEFT_ANGLE = 20.13;
    public final double LIDAR_LEFT_DISTANCE = 358.895;
    public final double LIDAR_RIGHT_DISTANCE = 339.35;
        
   // DifferentialDrive differentialDrive;

    //LiDAR testLidar;
    String side;
    
    Component[] parts = new Component[10];
    //Creates the controller for drive.
    public static XboxController driveController = new XboxController(0);

    
    DigitalInput autoSwitchA = new DigitalInput(RobotMap.AUTO_ENABLE_A);
    DigitalInput autoSwitchB = new DigitalInput(RobotMap.AUTO_ENABLE_B);
    
	@Override
	public void robotInit() 
	{
		//Add manip stuff.
		//parts[0] = new BoxManip();
		//parts[1] = new WinchManip();
		parts[2] = new Drive();
		parts[0] = new CompressorSwitch();
		//parts[4] = new Shifter();
		gyro = new ADXRS450_Gyro();
		
		//Construct scanner.
		try
		{
			ourScanner = SweepJNI.construct("/dev/ttyUSB0", 115200);
		}
		catch(Exception e)
		{
			System.out.println(e + "LiDAR not created");
		
		}
		
		SweepJNI.startScanning(ourScanner);
		
		scanData = SweepJNI.getScan(ourScanner);
		gyro.calibrate();
		//TODO put all of the drive stuff in the actual drive class
		side = DriverStation.getInstance().getGameSpecificMessage();
        
 	}
	
	public void teleopInit() 
	{
		System.out.println("Enter teleopInit");
	       //testLidar = new LiDAR();
	}
	
	@Override
	public void teleopPeriodic() 
	{
		if (isOperatorControl() && isEnabled()) 
		{
			//TODO really the only thing in here should be updating all of the components
			//testLidar.update();
			//differentialDrive.arcadeDrive(RobotMap.DRIVE_CONTROLLER.getY(GenericHID.Hand.kLeft), -RobotMap.DRIVE_CONTROLLER.getX(GenericHID.Hand.kLeft));
			
			//TODO get rid of this after testing
			parts[0].update(); //updates compressor switch
			//parts[1].update();
			parts[2].update();
			
			//System.out.println("Gyro Angle: " + gyro.getAngle());
		}
	}
	
	public void autonomousInit() 
	{
		System.out.println("Enter autoInit");
		autoState = 'a';
		System.out.println("Calibrate gyro");
		gyro.calibrate();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}
	
	//angle and distance variables for checking things
	double angle;
	double distance;
	double previousDistance;
	double distanceChange;
	double bigJump = 20;
	
	public void autonomousPeriodic() 
	{
		if(isEnabled() && isAutonomous()) 
		{
			/*
			 State a: Robot is on the back wall and rotating
			 State b: Robot is moving forwards
			 State c: Robot is turning to face switch
			 State d: Robot is dropping the cube
			 */
			//Gets the gamedata from the game server to see what side our switch is on.
			
			angle = gyro.getAngle();
			
			
			distance = scanData.length;
			distanceChange = distance - previousDistance;
			System.out.println("Angle: " + angle);
			parts[2].autoUpdate();
			//If the switch is on the left.
			if (gameData.charAt(0) == 'L')
			{
				//Sets autoState to the second stage if we have rotated the correct amount.
				if(angle >= GYRO_RIGHT_ANGLE-1 && angle <= GYRO_RIGHT_ANGLE+1 && autoState == 'a')
			    {
			    	autoState = 'b';
			    
			    }
				//Add a parameter for angle for scanData (something like scanData.angle)
				else if(distance >= LIDAR_LEFT_DISTANCE -1.5 && distance <= LIDAR_LEFT_DISTANCE +1.5 && autoState == 'b')
			    {
			    	autoState = 'c';
			    }
				
				if(autoState == 'b') 
				{
					
					//drift correction
					if(angle <= GYRO_RIGHT_ANGLE-1)
					{	
						driftSpeed = "liststarboard";
					}
					if(angle >=  GYRO_RIGHT_ANGLE+1)
					{	
						driftSpeed = "listport";
					}
					
					//checks for if there is a big jump in the lidar's distance readings
					if(distanceChange >= bigJump || distanceChange <= -1 * bigJump)
					{
						driftSpeed = "slow";
						/*if ( ) 
						{
							//do not replace with angle
							gyro.getAngle(); 
						}*/
					}	
					else
					{	
						//sets the current distance to the previous distance
						previousDistance = distance;
					}
					
				}
				
				
			}
			else if (gameData.charAt(0) == 'R')
			{
				//Sets autoState to the second stage if we have rotated the correct amount.
				if(gyro.getAngle() >= GYRO_LEFT_ANGLE -1 && gyro.getAngle() <= GYRO_LEFT_ANGLE +1 && autoState == 'a')
			    {
			    	autoState = 'b';
			    }
				//Add a parameter for angle for scanData (something like scanData.angle)
				else if(distance >= LIDAR_RIGHT_DISTANCE -1.5 && distance <= LIDAR_RIGHT_DISTANCE +1.5 && autoState == 'b')
			    {
			    	autoState = 'c';
			    }
				
				//corrections for during state b
				if(autoState == 'b') {
					//drift correction
					if(angle <= 19.13)
					{	
						driftSpeed = "listport";
					}
					if(angle >=  21.13)
					{	
						driftSpeed = "liststarboard";
					}
					
					//checks for if there is a big jump in the lidar's distance readings
					if(distanceChange >= bigJump || distanceChange <= -1 * bigJump)
					{
						driftSpeed = "slow"; 
						//if () {
							
						//}
					}
					else
					{
						
						//sets the current distance to the previous distance
						previousDistance = distance;
					}
				}
			}
			//changes autoState to the dropping of the cube stage (d) if we have rotated the correct amount
			if(angle >= -2 && angle <= 2 && autoState == 'c') 
			{
		    	autoState = 'd';
		    }
			

			//Do ALL of the autonomous updating in here! Nothing outside!
		}
	}
}