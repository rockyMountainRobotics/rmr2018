package org.usfirst.frc.team662.robot;

import edu.wpi.first.wpilibj.SensorBase;
import com.armabot.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;

	
	
public class LiDARAutonmous {
	WPI_TalonSRX leftFront;
	public void LidarAutonomous()
	{
		leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
	}
	public void update()
	{
		//scanner initialization
		long ourScanner; 
		//sample data that we get from the scan
		SweepSample[] scanData;
		
		
		//construct scanner
		ourScanner = SweepJNI.constructSimple();
	
		//begin scanning
		SweepJNI.startScanning(ourScanner);
		
		//get sample data
		scanData = SweepJNI.getScan(ourScanner);
		
		
		for(int i = 0; i < scanData.length; i++)
		{
			 if((scanData[i].distance >= (100) && scanData[i].distance <= (100)) && (scanData[i].angle >= (34) && scanData[i].angle <= (43))) {
				 leftFront.set(0);
			 }
			 else { 
				 leftFront.set(.5);
			 }	 
		}
		
	}
}
