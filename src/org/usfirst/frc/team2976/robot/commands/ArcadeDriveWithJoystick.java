/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2976.robot.OI;
import org.usfirst.frc.team2976.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDriveWithJoystick extends Command {
	double speed = 0;
	double rotation = 0;
	
	public ArcadeDriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		speed =  -Robot.oi.driveStick.getRawAxis(OI.Axis.LY.getAxisNumber());
		rotation =  Robot.oi.driveStick.getRawAxis(OI.Axis.RX.getAxisNumber());
		Robot.drivetrain.drive(speed, rotation);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
