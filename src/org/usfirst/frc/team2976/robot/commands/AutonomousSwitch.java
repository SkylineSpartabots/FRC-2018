package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutonomousSwitch extends Command{

private int position = -1; //starting position on alliance wall, 0, 1, and 2 from left to right respectively
private boolean switchSide = null; //alliance switch side, true is right 

    public AutonomousSwitch(int position) { //distance from switch wall to alliance wall = 12ft
    	this.position = position;
    	requires(Robot.drivetrain);
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	switch(gameData.charAt(0)) {
    	case 'L':
    		switchSide = false;
    	case 'R':
    		switchSide = true;
    	}
    }
    
    protected void initialize() {
    	initialTime= System.currentTimeMillis();
    }
    
    protected void execute() {
    	switch (position) { 
		case 0:
			SmartDashboard.putString("Game Data 0", "Left");
			leftAuto();
			break;
		case 1: //center?
			SmartDashboard.putString("Game Data 0", "Center");
			centerAuto();
			break;
		case 2:
			SmartDashboard.putString("Game Data 0", "Right");
			rightAuto();
			break;
    	}
    }
    
    protected void leftAuto() {
		addSequential(new DriveStraightAuto());
	}
	
	protected void centerAuto() {
		
	}
	
	protected void rightAuto() {
		addSequential(new DriveStraightAuto());
	}
    
	protected void setPosition(int position) {
		this.position = position;
	}
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finalTime-initialTime>m_duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
