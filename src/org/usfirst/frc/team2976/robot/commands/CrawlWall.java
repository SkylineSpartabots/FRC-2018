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
	double baseSpeed = 0.4;
	int distanceFromWall = 65;

	public CrawlWall() {
		lidarSource = new PIDSource() {
			public double getInput() {
				int distance = Robot.arduino.getDistance();
				return distance;
			}
		};
		wallDistancePID = new PIDMain(lidarSource, distanceFromWall, 100, 0.005, 0, 0);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		wallDistancePID.resetPID();
		// Robot.drivetrain.tankDrive(0, 0);
		wallDistancePID.enable(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("PIDout", wallDistancePID.getOutput());
		Robot.drivetrain.tankDrive(baseSpeed - wallDistancePID.getOutput(), baseSpeed + wallDistancePID.getOutput());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
		wallDistancePID.resetPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
