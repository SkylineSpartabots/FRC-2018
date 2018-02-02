package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToSwitch extends CommandGroup {

    public DriveToSwitch(String switchLocation, int robotLocation) {
		if (switchLocation == "L" & robotLocation == 1) {

		} else if (switchLocation == "L" & robotLocation == 2) {

		} else if (switchLocation == "L" & robotLocation == 3) {

		}
		if (switchLocation == "R" & robotLocation == 1) {

		} else if (switchLocation == "R" & robotLocation == 2) {

		} else if (switchLocation == "R" & robotLocation == 3) {

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
