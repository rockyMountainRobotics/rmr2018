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
    
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    XboxController xboxController;

    String side;
	@Override
	public void robotInit() 
	{
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

        xboxController = new XboxController(0);
	}
	
	public void teleopInt() {}
	
	@Override
	public void teleopPeriodic() 
	{
		if (isOperatorControl() && isEnabled()) 
		{
			//char[] positions;
			
			//positions = side.toCharArray();
			
			//positions[0] = 'l';
			
			//SmartDashboard.putString("GAME_SIDE", Character.toString(positions[0]));
			
			
			//differentialDriv;
			differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
			
			char in = GameData.GetSide();
			
			if(in == 'L')
			{
				leftFront.set(.5);
				leftBack.set(.5);
				//Used for testing
				SmartDashboard.putString("hi", "hi");
			}
			else 
			{
				rightFront.set(.5);
				rightBack.set(.5);
			}
			
			
		}
	}

}