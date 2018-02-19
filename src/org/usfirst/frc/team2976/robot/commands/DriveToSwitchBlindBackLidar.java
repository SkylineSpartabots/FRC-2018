package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**Timer timer;
	boolean switchSide; // where true is right
	int position; // where 0 is left and 2 is right
 */
public class DriveToSwitchBlindBackLidar extends CommandGroup {
	public DriveToSwitchBlindBackLidar() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {

			addParallel(new SwitchRetract());
			addSequential(new DriveStraightLidar(0.5,120));
			addSequential(new TurnCorner(-50));
			addSequential(new DriveStraightLidar(0.6,370));
			addSequential(new TurnCorner(50));
			addSequential(new TimedDrive(0.2));
			addSequential(new RollIntake(0.4, false));
			
			// Put left auto code here
			SmartDashboard.putString("Game Data 0", "Left");
		} else {
			// Put right auto code here
			addParallel(new SwitchRetract());
			addSequential(new DriveStraightLidar(0.5,120));
			addSequential(new TurnCorner(50));
			addSequential(new DriveStraightLidar(0.6,370));
			addSequential(new TurnCorner(-50));
			addSequential(new TimedDrive(0.2));
			addSequential(new RollIntake(0.4, false));
			
			SmartDashboard.putString("Game Data 0", "Right");
		}
		
		
	}
}
