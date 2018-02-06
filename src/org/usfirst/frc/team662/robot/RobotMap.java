package org.usfirst.frc.team662.robot;

import edu.wpi.first.wpilibj.XboxController;

public class RobotMap {
	
	//Drive motor ports
	public final static int RIGHT_FRONT_MOTOR = 6;
	public final static int LEFT_FRONT_MOTOR = 5;
	public final static int RIGHT_BACK_MOTOR = 8;
	public final static int LEFT_BACK_MOTOR = 3;
	
	//Manipulator motor ports
	public final static int WINCH_SET = 4;
	public final static int WINCH_PULL = 5;
	public final static int CHAIN = 6;
	public final static int ARM = 7;
	
	//Limit switch ports
	public final static int LIMIT_TOP = 1;
	public final static int LIMIT_MIDDLE = 2;
	public final static int LIMIT_BOTTOM = 3;
	
	//Switch ports
	public final static int AUTO_ENABLE_A = 0;
	public final static int AUTO_ENABLE_B = 1;
	public final static int COMPRESSOR_ENABLE = 2;
	
	//Controllers. Not really a part of the robot, but meh.
	public final static XboxController MANIP_CONTROLLER = new XboxController(1);
	public final static XboxController DRIVE_CONTROLLER = new XboxController(0);
}