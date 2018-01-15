<<<<<<< HEAD
package org.usfirst.frc.team662.robot;

public class Robot
{
	
=======
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team662.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
    
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightBack;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    XboxController xboxController;

	@Override
	public void robotInit() {
		leftFront = new WPI_TalonSRX(LEFT_FRONT);
        leftBack = new WPI_TalonSRX(LEFT_BACK);
        rightFront = new WPI_TalonSRX(RIGHT_FRONT);
        rightBack = new WPI_TalonSRX(RIGHT_BACK);

        rightFront.setInverted(true);
        rightBack.setInverted(true);
        
        leftDrive = new SpeedControllerGroup(leftFront, leftBack);
        rightDrive = new SpeedControllerGroup(rightFront, rightBack);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        xboxController = new XboxController(0);
	}
	
	public void teleopInt() {}
	
	@Override
	public void teleopPeriodic() {
		if (isOperatorControl() && isEnabled()) {
			//differentialDriv;
			differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
			
		}
	}
>>>>>>> f28870b477370d904fce8f985c667c5786144793
}