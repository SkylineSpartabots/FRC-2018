package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.AutoTargetSide;
import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {
	double turnPower = 0.6;
	double turnTime = 1.3;
	public Autonomous(boolean onRightSide) {

		addSequential(new TimedDrive(0.6,3));
		
		
		if (onRightSide) {
			if(AutoTargetSide.Right == Robot.autoTargetSide)	{
				addSequential(new PIDTurn(-90));
				addSequential(new TimedDrive(0.6,1));
				addSequential(new RollIntake(0.4,0.75,false));
				//Second Cube
				/*
				addSequential(new TimedDrive(-0.6,0.75));
				addSequential(new TimedTurnRight(turnPower,turnTime));
				addSequential(new TimedDrive(0.6,1.5));
				addSequential(new TimedTurnRight(-turnPower,turnTime));
				addSequential(new TimedDrive(0.6,0.75));
				addSequential(new TimedTurnRight(-turnPower,turnTime));
				addSequential(new SwitchExtend());
				addParallel(new RollIntake(0.4,2.5,true));
				addSequential(new TimedDrive(0.5,1));
				addSequential(new TimedDrive(-0.6,0.75));
				addSequential(new SwitchRetract());
				addSequential(new TimedTurnRight(turnPower,0.3));		
				addSequential(new TimedDrive(0.5,1));
				addSequential(new RollIntake(0.4,0.75,false));				
				*/
			}	else	{	//drive around
				
			
			}
			
		} else  {
			if(AutoTargetSide.Left == Robot.autoTargetSide)	{
				addSequential(new PIDTurn(90));
				addSequential(new TimedDrive(0.6,1));
				addSequential(new RollIntake(0.4,1,false));	
				//#2
				/*
				addSequential(new TimedDrive(-0.6,0.75));
				addSequential(new TimedTurnRight(-turnPower,1.5));
				addSequential(new TimedDrive(0.6,1.5));
				*/
				//addSequential(new TimedTurnRight(turnPower,turnTime));
				//addSequential(new TimedDrive(0.6,0.75));
				//addSequential(new TimedTurnRight(turnPower,turnTime));
				//addSequential(new SwitchExtend());
				//addParallel(new RollIntake(0.4,2.5,true));
				//addSequential(new TimedDrive(0.5,1));
				//addSequential(new TimedDrive(-0.6,0.75));
				//addSequential(new SwitchRetract());
				//addSequential(new TimedTurnRight(-turnPower,0.3));		
				//addSequential(new TimedDrive(0.5,1));
				//addSequential(new RollIntake(0.4,0.75,false));		
			
			}	else	{	//drive around
				addSequential(new TimedDrive(0.6,1));
				addSequential(new PIDTurn(90));
				addSequential(new TimedDrive(0.6,4));
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
