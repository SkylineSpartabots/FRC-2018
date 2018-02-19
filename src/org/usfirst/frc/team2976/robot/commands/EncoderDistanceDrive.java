package org.usfirst.frc.team2976.robot.commands;
import org.usfirst.frc.team2976.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class EncoderDistanceDrive extends Command {
	double distance = 0;
	double power = 0.5;
	/*
	 * Distance in feet
	 */
    public EncoderDistanceDrive(double power, double distance) {
    	this.distance = distance;
    	this.power = power;
    	requires(Robot.drivetrain);
    }
    public EncoderDistanceDrive(double distance) {
    	this.distance = distance;
    	this.power = 0.5;
    	requires(Robot.drivetrain);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.tankDrive(0, 0);
    	Robot.drivetrain.rotationLock.resetPID();
    	Robot.drivetrain.rotationLock.setSetpoint(Robot.rps.getAngle());
    	Robot.drivetrain.tankDrive(power, power);
    	Robot.drivetrain.resetEncoders();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.rotationLockTankDrive(power, power);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getAvgDistance()>distance;
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
