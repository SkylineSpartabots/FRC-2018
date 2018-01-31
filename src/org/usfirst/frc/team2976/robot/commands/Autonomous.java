package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends Command{

private char position = 'z';

    public Autonomous() {
    	requires(Robot.drivetrain);
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	position = gameData.charAt(0);
		switch (position) {
			case 'L':
				SmartDashboard.putString("Game Data 0", "Left");
				leftAuto();
				break;
			case 'C': //center?
				SmartDashboard.putString("Game Data 0", "Center");
				centerAuto();
				break;
			case 'R':
				SmartDashboard.putString("Game Data 0", "Right");
				rightAuto();
		}
    }
    
	public void leftAuto() {
		
	}
	
	public void centerAuto() {
		
	}
	
	public void rightAuto() {
		
	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
