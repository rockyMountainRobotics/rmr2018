package org.usfirst.frc.team662.robot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

public class Shifter 
{
	public static final int SOLENOID_PORT = 0;
	Solenoid solenoid = new Solenoid(SOLENOID_PORT);
	boolean current = true;
	boolean past = false;
	
	
	XboxController xboxController = new XboxController(0);
	public void teleopPeriodic(){
		current = xboxController.getRawButton(XboxMap.B);
		
		if(current == true && past == false){
			solenoid.set(!solenoid.get());
		}
		SmartDashboard.putBoolean("High Gear", !solenoid.get());
		past = current;
	}
	
	
}