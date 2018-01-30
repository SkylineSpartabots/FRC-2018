package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToSwitch extends Command {
	// 1 for right 2 for center 3 for left
	int robotLocation;
	boolean finished;
	String switchLocation;

	public DriveToSwitch(int AutonomousLocation, String switchSide) {
		robotLocation = AutonomousLocation;
		requires(Robot.drivetrain);
		switchLocation = switchSide;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.drive(0.0, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// every rotation wheel goes 18.8 inches 1 raw distance= 0.06 inches
		if (switchLocation == "L" & robotLocation == 1) {
			//go forward
			//the desired time is in seconds 
			long desiredTime=5;
			long initialTime= System.currentTimeMillis();
			while(desiredTime/1000>System.currentTimeMillis()-initialTime){
				
			}

		} else if (switchLocation == "L" & robotLocation == 2) {

		} else if (switchLocation == "L" & robotLocation == 3) {

		}
		if (switchLocation == "R" & robotLocation == 1) {

		} else if (switchLocation == "R" & robotLocation == 2) {

		} else if (switchLocation == "R" & robotLocation == 3) {

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
