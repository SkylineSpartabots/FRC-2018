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
 * An example command. You can replace me with your own command.
 */
public class VariableIntake extends Command {
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	double powerIn = 0;
	double powerOut = 0;

	public VariableIntake() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time

	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		leftSpeed = Robot.oi.secondStick.getRawAxis(OI.Axis.LX.getAxisNumber());
		rightSpeed = Robot.oi.secondStick.getRawAxis(OI.Axis.RX.getAxisNumber());
		
		powerIn = Robot.oi.secondStick.getRawAxis(OI.Axis.RTrigger.getAxisNumber());
		powerOut = Robot.oi.secondStick.getRawAxis(OI.Axis.LTrigger.getAxisNumber());
		
		if(leftSpeed > 0.05) {
			Robot.intake.setPowerIndv(leftSpeed, true);
		} else if (rightSpeed > 0.05) {
			Robot.intake.setPowerIndv(rightSpeed, false);
		} else if (powerIn > 0.05) {
			Robot.intake.setPower(powerIn, true);
		} else if (powerOut > 0.05) {
			Robot.intake.setPower(powerOut, false);
		}
	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intake.setPower(0, true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
