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
	
	
	//XboxController xboxController = new XboxController(0);
	public void update(){
		
		//set whether the button is being pressed
		current = Robot.driveController.getRawButton(XboxMap.B);
		
		//if its being pressed and it isn't rereading the same press of the button
		if(current == true && past == false){
			//set the solenoid to the opposite of what it currently is
			solenoid.set(!solenoid.get());
		}
		//set the value on smart dashboard
		SmartDashboard.putBoolean("High Gear", !solenoid.get());
		//set the past to the present
		past = current;
	}
	
	
}