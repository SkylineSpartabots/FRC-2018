package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.AutoTargetSide;
import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MiddleAuto extends CommandGroup {
	double turnPower = 0.5;
	double turnTime = 0.5;
	public MiddleAuto() {
		addSequential(new TimedDrive(0.6,0.75));
			if(AutoTargetSide.Right == Robot.autoTargetSide)	{
				addSequential(new TimedTurnRight(-turnPower,turnTime));
				addSequential(new TimedDrive(0.6,2));
				addSequential(new TimedTurnRight(turnPower,0.3));
				addSequential(new TimedDrive(0.6,2-0.2));
				addSequential(new RollIntake(0.4,1,false));			
			}			
			else  {	
				if(AutoTargetSide.Left == Robot.autoTargetSide)	{
				addSequential(new TimedTurnRight(turnPower,turnTime-0.1));
				addSequential(new TimedDrive(0.6,2-0.25));
				addSequential(new TimedTurnRight(-turnPower,0.3+0.07));
				addSequential(new TimedDrive(0.7,0.25));
				addSequential(new RollIntake(0.4,1,false));					
			}			
	
		}
		 
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
