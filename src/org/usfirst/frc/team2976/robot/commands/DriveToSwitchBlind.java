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
public class DriveToSwitchBlind extends CommandGroup {
	Timer timer; 
	boolean switchSide;
	
	public DriveToSwitchBlind(boolean switchSide, int position) {
		this.switchSide = switchSide;
		timer = new Timer();
		timer.start();
		
		requires(Robot.drivetrain);
		requires(Robot.switchArm);
		
		if (position == 1) { // center auto has two options
			if (switchSide) {
				rightMovement();
			} else {
				leftMovement();
			}
		} else {
			centerMovement(switchSide);
		}

		Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
	}
	
	public void centerMovement(boolean switchSide) {
		if (switchSide) {
			//1st cube
			addSequential(new DriveStraight(3));
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(9));
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(11));
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(4));
			addSequential(new RollIntake(0.6, false));
			//2nd cube
			addSequential(new DriveStraight(-4));
			addSequential(new SwitchRetract());
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(5));
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(7));
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(2));
			addSequential(new SwitchExtend());
			addSequential(new DriveStraight(1));
			addSequential(new RollIntake(0.6, true));
		}else {
			//1st cube
			addSequential(new DriveStraight(3));
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(9));
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(11));
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(4));
			addSequential(new RollIntake(0.6, false));
			//2nd cube
			addSequential(new DriveStraight(-4));
			addSequential(new SwitchRetract());
			addSequential(new TurnCorner(-90));
			addSequential(new DriveStraight(5));
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(7));
			addSequential(new TurnCorner(90));
			addSequential(new DriveStraight(2));
			addSequential(new SwitchExtend());
			addSequential(new DriveStraight(1));
			addSequential(new RollIntake(0.6, true));
		}
	}

	public void leftMovement() { // switch is on robot's side for these
		addSequential(new DriveStraight(14));
		addSequential(new TurnCorner(-90));
		addSequential(new RollIntake(0.6, false));
		addSequential(new TurnCorner(90));
	}

	public void rightMovement() {
		addSequential(new DriveStraight(14));
		addSequential(new TurnCorner(90));
		addSequential(new RollIntake(0.6, false));
		addSequential(new TurnCorner(-90));
	}
	
	public Timer getTimer() {
		return timer;

	}
}
