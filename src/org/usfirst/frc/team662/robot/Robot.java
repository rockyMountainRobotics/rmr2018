package org.usfirst.frc.team662.robot;

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
    
    //Creates a variable for states A, B, C, and D in Autonomous. These are used in the following
    //classes: Drive, LiDAR(if we keep this as its own class), BoxManip
    String autoUpdateS = "a";
    
    
    
    
    //Creates the gyro object. Initialization happens in robotInit
    public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
   // DifferentialDrive differentialDrive;

    LiDAR testLidar;
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
		//parts[5] = new GameData();
		parts[1] = new LiDAR();
		
		
		
		
		
		//TODO put all of the drive stuff in the actual drive class
		side = DriverStation.getInstance().getGameSpecificMessage();
		//leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		//leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
        //rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
        //rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

        //rightFront.setInverted(true);
        //rightBack.setInverted(true);
        
        //leftDrive = new SpeedControllerGroup(leftFront, leftBack);
        //rightDrive = new SpeedControllerGroup(rightFront, rightBack);

        //differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        
        
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
			char[] positions;
			
			//testLidar.update();
			//differentialDrive.arcadeDrive(RobotMap.DRIVE_CONTROLLER.getY(GenericHID.Hand.kLeft), -RobotMap.DRIVE_CONTROLLER.getX(GenericHID.Hand.kLeft));
			
			//TODO get rid of this after testing
			parts[0].update(); //updates compressor switch
			parts[1].update();
			parts[2].update();
			
			System.out.println("Gyro Angle: " + gyro.getAngle());
			/*positions = side.toCharArray();
			
			positions[0] = 'l';
			
			SmartDashboard.putString("GAME_SIDE", Character.toString(positions[0]));
			
			
			//differentialDriv;
			
			/*TODO put this stuff in autonomous
			 * char in = GameData.GetSide();
			
			if(in == 'L')
			{
				leftFront.set(.5);
				leftBack.set(.5);
				
				SmartDashboard.putString("hi", "hi");
			}
			else 
			{
				rightFront.set(.5);
				rightBack.set(.5);
			}
			*/
			
			
		}
	}
	
	public void autonomousInit() 
	{
		
	}
	
	public void autonomousPeriodic() 
	{
		if(!isOperatorControl() && isEnabled()) 
		{
			
			//Do ALL of the autonomous updating in here! Nothing outside!
		}
	}
}