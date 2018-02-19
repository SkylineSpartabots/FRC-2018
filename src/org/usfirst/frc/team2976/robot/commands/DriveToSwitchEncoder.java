package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToSwitchEncoder extends CommandGroup {

    public DriveToSwitchEncoder() {
    	addParallel(new SwitchRetract());
		//addSequential(new DriveStraightLidar(0.5,120));
    //	addSequential(new EncoderDistanceDrive(0.5, xx));
		addSequential(new TurnCorner(50));
	//	addSequential(new EncoderDistanceDrive(0.5, xx));
		//addSequential(new DriveStraightLidar(0.6,370));
		addSequential(new TurnCorner(-45));
		addSequential(new TurnCorner(-45));
		addSequential(new RollIntake(0.4, false));
    }
}
