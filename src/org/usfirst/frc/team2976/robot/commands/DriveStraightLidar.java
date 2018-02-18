package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author NeilHazra
 *Uses lidar sensor to drive in feet accurately
 *only works if facing 90 degrees from a wall
 */
public class DriveStraightLidar extends Command {
	double power;
	// distance is in inches
	double distance;
	
	double initLidarDistance = 0; 

    public DriveStraightLidar(double distance) {
        power = 0.5;
    	this.distance = distance;
    	initLidarDistance = accessDistanceInFeet(); //read and convert to feet
    	requires(Robot.drivetrain);
    }
    private double accessDistanceInFeet()	{
    	return (Robot.arduino.getDistance()/2.54)/12.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.tankDrive(power, power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(power, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return accessDistanceInFeet()-initLidarDistance > distance;
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
