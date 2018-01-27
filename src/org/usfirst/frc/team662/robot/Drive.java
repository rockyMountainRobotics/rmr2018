package org.usfirst.frc.team662.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive implements Component{
	
	final double MULTIPLIER = .6;
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    
    DifferentialDrive differentialDrive;
    
    public XboxController xboxController;
	
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
        
        xboxController = new XboxController(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		differentialDrive.arcadeDrive(MULTIPLIER *(xboxController.getY(GenericHID.Hand.kLeft)), MULTIPLIER*( -xboxController.getX(GenericHID.Hand.kLeft)));
	}

	@Override
	public void autoUpdate() {
		// TODO Auto-generated method stub
		String gameData;
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
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
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
}
