package org.usfirst.frc.team662.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GameData {
	public static char GetSide()
	{
		String side;
		//char[] positions = new char[3];
		
		side = DriverStation.getInstance().getGameSpecificMessage();
		
		//positions = side.toCharArray();
		
		char gameData = side.charAt(0);
		
		//positions[0] = 'l';
		
		SmartDashboard.putString("GAME_SIDE", Character.toString(gameData));
		
		return gameData;
	}
}
