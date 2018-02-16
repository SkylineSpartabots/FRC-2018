package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;
import org.usfirst.frc.team2976.robot.subsystems.RobotArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RobotArmMovementUp extends Command {

	public RobotArmMovementUp() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.robotArm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// setting motor off

		Robot.robotArm.setPower(0.0);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if the limit switch is not pressed
		// set power to 0.6 if (to slow||too fast){ please feel free to change}
		if (!Robot.robotArm.getArmUpLimitSwitch()) {
			Robot.robotArm.setPower(RobotArm.DEFAULT_ARM_POWER);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.robotArm.setPower(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
