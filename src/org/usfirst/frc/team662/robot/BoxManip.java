package org.usfirst.frc.team662.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.XboxController;

public class BoxManip implements Component {
	
	//
	final double LEFT_DEADZONE = .15;
	final double RIGHT_DEADZONE = .15;
	
	//
	WPI_TalonSRX chain = new WPI_TalonSRX(RobotMap.CHAIN);
	WPI_TalonSRX arm = new WPI_TalonSRX(RobotMap.ARM);
	
	//Create XboxController object for the controller
		XboxController manipController = new XboxController(1);
	
	@Override
	public void update() {
		// Check for the input in the right Joystick
		double armSpeed = manipController.getRawAxis(5);
		if (Math.abs(armSpeed) > RIGHT_DEADZONE) {
			arm.set(armSpeed);
		}
		
	}

	@Override
	public void autoUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
	
}
