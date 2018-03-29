package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FancyAuto extends Command {
	Timer timer = new Timer();
	double time = 1.5;
	double power = 0.6;
	double curve = 2;
    public FancyAuto(double time, double power, double curve) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.time = time;
    	this.power = power;
    	this.curve = curve;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(0.5 + Math.pow((timer.get()/6),3), 0.5 - Math.pow((timer.get()/6),3));
    	//Robot.drivetrain.arcadeDrive(power, 2*Math.sin(curve*timer.get()));
    	System.out.println(timer.get());
    }
 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get()>time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
