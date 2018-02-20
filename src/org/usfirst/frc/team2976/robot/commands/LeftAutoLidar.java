package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftAutoLidar extends CommandGroup {

    public LeftAutoLidar() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'R') {
			addSequential(new DriveStraightLidar(0.6,400));
			// Put left auto code here
			SmartDashboard.putString("Game Data 0", "Left");
		} else {
			addParallel(new SwitchRetract());
	    	addSequential(new DriveStraightLidar(0.75,337));
	    	addSequential(new TurnCorner(90));
	    	addSequential(new TimedDrive(0.5));
	    	addSequential(new RollIntake(0.4,1, false));
	    	addSequential(new TimedDrive(-0.5,1));
			addParallel(new SwitchExtend());
			SmartDashboard.putString("Game Data 0", "Right");
		}

    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
