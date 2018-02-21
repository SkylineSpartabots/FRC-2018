package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author NeilHazra
 *Uses lidar sensor to drive in feet accurately
 *only works if facing parallel to a wall
 *probably will not use becuase I can get only one sensor working
 */
public class DriveStraightLidar extends Command {
	double power;
	// distance is in cm
	double distance;
	
	double initLidarDistance = 0; 

    public DriveStraightLidar(double power, double distance) {
        this.power = power;
    	this.distance = distance;
    	initLidarDistance = accessDistanceInFeet(); //read and convert to feet
    	requires(Robot.drivetrain);
    }
    /*
     * Convert raw cm from lidar to feet
     */
    private double accessDistanceInFeet()	{
    	return (Robot.arduino.getDistance());///2.54)/12.0; 
  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.rotationLock.resetPID();
    	Robot.drivetrain.rotationLock.setSetpoint(Robot.rps.getAngle());
    	Robot.drivetrain.tankDrive(power, power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(power, power);
    	//Robot.drivetrain.rotationLockTankDrive(power, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return accessDistanceInFeet() > distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.rotationLock.resetPID();
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
