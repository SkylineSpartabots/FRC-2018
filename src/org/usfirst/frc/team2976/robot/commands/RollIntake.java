/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2976.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class RollIntake extends Command {
	private double power = 0;
	private boolean rollIn = true;
	public RollIntake(double power, boolean rollIn) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
    	this.power = power;
    	this.rollIn = rollIn;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intake.setPower(power, rollIn);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.intake.setPower(power, rollIn);
		SmartDashboard.putNumber("IntakeCurrent", Robot.intake.getAvgCurrentDraw());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intake.setPower(0, rollIn);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.intake.setPower(0, rollIn);
	}
}
