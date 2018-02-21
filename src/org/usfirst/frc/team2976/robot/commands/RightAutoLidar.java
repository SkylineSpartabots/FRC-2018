package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightAutoLidar extends CommandGroup {

    public RightAutoLidar() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			addSequential(new DriveStraightLidar(0.6,400));
			SmartDashboard.putString("Game Data 0", "Left");
		} else {
			addParallel(new SwitchRetract());
	    	addSequential(new DriveStraightLidar(0.75,337));
	    	addSequential(new TurnCorner(-90));
	    	addSequential(new TimedDrive(0.5));
	    	addSequential(new RollIntake(0.4,1, false));
	    	addSequential(new TimedDrive(-0.5,1));
			addParallel(new SwitchExtend());
			SmartDashboard.putString("Game Data 0", "Right");
		}
    }
}