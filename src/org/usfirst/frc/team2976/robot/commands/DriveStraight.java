package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;
import org.usfirst.frc.team2976.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {
	double power;
	// distance is in inches
	double time;
	Timer timer; 
	public DriveStraight(double time) { // distance in inches
		requires(Robot.drivetrain);
		timer = new Timer();
		// power of the motors
		power = 0.5;
		this.time = time;
		//this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("EncoderDistance", Robot.drivetrain.getAvgClicks());
		Robot.drivetrain.tankDrive(power, power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get()>time;//Robot.drivetrain.getRightEncoderDistance()  >= distance;
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.reset();
		Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
