package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;
import org.usfirst.frc.team2976.robot.RobotMap;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	double power;
	// distance is in inches
	double distance;

	public DriveStraight(double distance) { // distance in inches
		requires(Robot.drivetrain);
		// power of the motors
		power = 0.5;
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.tankDrive(power, power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.getRightEncoderDistance() * RobotMap.encoderDistanceScaleFactor >= distance;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
