package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**Timer timer;
	boolean switchSide; // where true is right
	int position; // where 0 is left and 2 is right
 */
public class SwitchAutoSimplified extends CommandGroup {
	Timer timer;

    public SwitchAutoSimplified() {
		requires(Robot.drivetrain);
		requires(Robot.switchArm);
		timer = new Timer();
		timer.start();
		addSequential(new DriveStraight(3));
		addSequential(new TurnCorner(-90));
		addSequential(new DriveStraight(4.5));
		addSequential(new TurnCorner(90));
		addSequential(new DriveStraight(7));
		addSequential(new RollIntake(0.6,false));
		
		
		timer.stop();
    }
	public Timer getTimer() {
		return timer;

	}
}
