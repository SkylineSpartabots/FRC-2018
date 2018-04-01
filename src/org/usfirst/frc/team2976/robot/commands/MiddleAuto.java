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
	double turnTime = 0.7;
	public MiddleAuto() {
		addSequential(new TimedDrive(0.6,0.75));
		System.out.println("MiddleAutoSide " + Robot.autoTargetSide);
			if(AutoTargetSide.Right == Robot.autoTargetSide)	{
				System.out.println("MiddleAutoSide " + "Right");
				SmartDashboard.putNumber("isRight", 1);
				addSequential(new TimedTurnRight(turnPower,turnTime));
				addSequential(new TimedDrive(0.55,1.45));
				addSequential(new TimedTurnRight(-turnPower,0.5));
				addSequential(new TimedDrive(0.6,1.5));
				addSequential(new RollIntake(0.4,2,false));			
			}	else if(AutoTargetSide.Left == Robot.autoTargetSide)	{
				System.out.println("MiddleAutoSide " + "Left");
				SmartDashboard.putNumber("isRight", 0);		
				addSequential(new TimedTurnRight(-turnPower,turnTime));
				addSequential(new TimedDrive(0.55,1.6));
				addSequential(new TimedTurnRight(turnPower,0.5));
				addSequential(new TimedDrive(0.6,1));
				addSequential(new RollIntake(0.4,2,false));					
			}	else {		
				SmartDashboard.putNumber("isRight", -1);
				
				System.out.println("MiddleAutoSide " + "N/A");
				addSequential(new TimedTurnRight(turnPower,turnTime));	
				addSequential(new TimedDrive(0.6,2.5));
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
