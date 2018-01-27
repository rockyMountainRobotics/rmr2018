package org.usfirst.frc.team662.robot;

import java.util.ArrayList;

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
    

   public XboxController xboxController;

    String side;
    
    ArrayList<Component> components;
 	ArrayList<Component> disabled;
    
    public Robot()
    {
    	
         
        components.add(new Drive());
 		//components.add(new Shifter());
    }
    
	@Override
	public void robotInit() 
	{
		side = DriverStation.getInstance().getGameSpecificMessage();

        xboxController = new XboxController(0);
        
       
	}
	
	public void teleopInt() {}
	
	@Override
	public void teleopPeriodic() 
	{
		if (isOperatorControl() && isEnabled()) 
		{
			for(int i = 0; i < components.size(); i++)
			{
				components.get(i).update();
			}
		}
	}
	
	public void autonomousPeriodic() 
	{
		// originally copied from 2018 game data java code
		
		
	}

}