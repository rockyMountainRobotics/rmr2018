package org.usfirst.frc.team662.robot;

import edu.wpi.first.wpilibj.SensorBase;
import com.armabot.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;


	
public class LiDAR implements Component {
	
	long ourScanner = 0;

	public LiDAR()
	{
		//construct scanner
		ourScanner = SweepJNI.construct("/dev/ttyUSB0", 115200);
		SweepJNI.startScanning(ourScanner);
	}
	public void update()
	{
		//sample data that we get from the scan
		SweepSample[] scanData;
		
		//get sample data
//		scanData = SweepJNI.getScan(ourScanner);
		
		
/*		for(int i = 0; i < scanData.length; i++)
		{
			System.out.println(scanData[i].distance);
			 if((scanData[i].distance >= (100) && scanData[i].distance <= (100)) && (scanData[i].angle >= (34) && scanData[i].angle <= (43))) {
				 leftFront.set(0);
			 }
			 else { 
				 leftFront.set(.5);
			 }	 
		}
*/
		
	}
	@Override
	public void autoUpdate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		SweepJNI.stopScanning(ourScanner);
		SweepJNI.setMotorSpeed(ourScanner, 0);
	}
}
