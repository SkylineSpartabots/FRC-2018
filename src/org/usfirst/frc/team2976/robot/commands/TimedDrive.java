package org.usfirst.frc.team2976.robot.commands;
import org.usfirst.frc.team2976.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedDrive extends Command {
	double power;
	double time;
	Timer timer; 
	/**
	 * @param power
	 * @param time in seconds
	 */
	public TimedDrive(double power, double time) { // distance in inches
		requires(Robot.drivetrain);
		timer = new Timer();
		this.power = power;
		this.time = time;
	}
	public TimedDrive(double time) { // distance in inches
		requires(Robot.drivetrain);
		this.power = 0.5;
		this.time = time;
	}
	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
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
		return timer.hasPeriodPassed(time);
		//return timer.get()>time;//Robot.drivetrain.getRightEncoderDistance()  >= distance;
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
		end();
	}
}
