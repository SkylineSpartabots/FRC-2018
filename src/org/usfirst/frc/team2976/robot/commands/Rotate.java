package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {
	double power = 0.5;
	double angle; //degrees
	boolean finished;

	public Rotate(double angle) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.angle = angle;
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (angle > 0) {
			Robot.drivetrain.tankDrive(power, -power);
		} else {
			Robot.drivetrain.tankDrive(-power, power);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (angle > 0) {
			finished = Robot.rps.getAngle() >= angle;
		} else {
			finished = Robot.rps.getAngle() <= angle;
		}
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
		Robot.rps.reset();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
