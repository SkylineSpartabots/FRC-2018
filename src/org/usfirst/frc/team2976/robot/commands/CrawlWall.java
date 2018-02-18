package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.PIDMain;
import util.PIDSource;

/**
 * @author NeilHazra Code runs PID loop to stay a fixed distance from wall at
 *         all times
 */
public class CrawlWall extends Command {
	PIDMain wallDistancePID;
	PIDSource lidarSource;

	double baseSpeed = 0.5;
	int distanceFromWall = 55;
	double angle = 0;

    public CrawlWall() {
    	Robot.rps.reset();
    	angle = Robot.rps.getAngle();
    	lidarSource =  new PIDSource()	{
    		public double getInput()	{
    			double distance = 0;
    			distance = Robot.arduino.getDistance();
    			distance = distance * Math.cos(Math.toRadians(Robot.rps.getAngle()-angle));
    			System.out.println(Robot.rps.getAngle());
    			return distance;
    		}
    	};
    	wallDistancePID = new PIDMain(lidarSource, distanceFromWall, 100, 0.004,0.00001,0.0);
    
    	
    	requires(Robot.drivetrain);
    }
    protected void execute() {
    	SmartDashboard.putNumber("PIDout", wallDistancePID.getOutput());
    	SmartDashboard.putNumber("AdjustedDistance", lidarSource.getInput());
    	double output = wallDistancePID.getOutput();
   		Robot.drivetrain.tankDrive(baseSpeed-output, baseSpeed + output);
    }
	protected void initialize() {
		wallDistancePID.resetPID();
		Robot.drivetrain.tankDrive(0, 0);
		wallDistancePID.enable(true);
	}
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    	Robot.rps.reset();
    	Robot.drivetrain.tankDrive(0, 0);
    	wallDistancePID.resetPID();
    }
    protected void interrupted() {
    	end();
    }
}
