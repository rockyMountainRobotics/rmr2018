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

public class Robot extends IterativeRobot {
    //TODO put all of the drive stuff in the actual drive class
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    //LiDAR testLidar;
    String side;
    
    Component[] parts = new Component[10];
    
    DigitalInput autoSwitchA = new DigitalInput(RobotMap.AUTO_ENABLE_A);
    DigitalInput autoSwitchB = new DigitalInput(RobotMap.AUTO_ENABLE_B);
    
	@Override
	public void robotInit() 
	{
		//add manip stuff
		//parts[0] = new BoxManip();
		//parts[1] = new WinchManip();
		//parts[2] = new Drive();
		parts[0] = new CompressorSwitch();
		//parts[4] = new Shifter();
		//parts[5] = new GameData();
		//parts[6] = new LiDAR();
		
		
		//TODO put all of the drive stuff in the actual drive class
		side = DriverStation.getInstance().getGameSpecificMessage();
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
	
	public void teleopInit() 
	{
		System.out.println("Enter telopInit");
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
			differentialDrive.arcadeDrive(RobotMap.DRIVE_CONTROLLER.getY(GenericHID.Hand.kLeft), -RobotMap.DRIVE_CONTROLLER.getX(GenericHID.Hand.kLeft));
			
			//TODO get rid of this after testing
			System.out.println("Auto A:" + !autoSwitchA.get() + "\nAuto B:" + !autoSwitchB.get());
			parts[0].update(); //updates compressorswitch
			
			
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
			}*/
			
			
		}
	}
	
	public void autonomousInit() {
		
	}
	
	public void autonomousPeriodic() {
		if(!isOperatorControl() && isEnabled()) {
			//Do ALL of the autonomous updating in here! nothing outside!
		}
	}
}