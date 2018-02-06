package org.usfirst.frc.team662.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GameData implements Component {
	public static char GetSide()
	{
		//TODO put all of this into the autonomousInit() method in Robot.java, and get rid of this class.
		String side;
		//char[] positions = new char[3];
		
		side = DriverStation.getInstance().getGameSpecificMessage();
		
		//positions = side.toCharArray();
		
		char gameData = side.charAt(0);
		
		//positions[0] = 'l';
		
		SmartDashboard.putString("GAME_SIDE", Character.toString(gameData));
		
		return gameData;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
