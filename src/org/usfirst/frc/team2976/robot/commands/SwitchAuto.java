package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import Vision.VisionProcess;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwitchAuto extends CommandGroup {
	Timer timer;
	boolean switchSide; // where true is right
	int position; // where 0 is left and 2 is right

	public SwitchAuto(int position) {
		this.position = position;
		requires(Robot.drivetrain);
		requires(Robot.switchArm);
		timer = new Timer();
		timer.start();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("Game Message", gameData);
		switch (gameData.charAt(0)) {
		case 'L':
			switchSide = false;
		case 'R':
			switchSide = true;
		}
		switch (position) {
		case 0:
			if (switchSide) {
				addSequential(new DriveToSwitch(switchSide, position));
				addSequential(new TimedDrive(12));
			}
			break;
		case 1:
			if (switchSide) {
				addSequential(new DriveToSwitch(switchSide, position));
			} else {
				addSequential(new DriveToSwitch(switchSide, position));
			}
			break;
		case 2:
			if (switchSide) {
				addSequential(new TimedDrive(12));
			} else {
				addSequential(new DriveToSwitch(switchSide, position));
			}

			break;
		}
		timer.stop();
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}

	public Timer getTimer() {
		return timer;

	}
}
