package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToSwitch extends Command{
	
	boolean switchSide;
	int position = - 1; // 0 - 2 is left to right on alliance wall
	Timer timer;

    public DriveToSwitch(int position) {
    	requires(Robot.drivetrain);
    	this.position = position;
    	timer = new Timer();
    	timer.start();
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	switch(gameData.charAt(0)) {
    	case 'L':
    		switchSide = false;
    	case 'R':
    		switchSide = true; 
    	}
    }
    
    protected void initialize() {
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }
    
    protected void execute() {
    	
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(timer.get() > 14) {
			return true;
		}else {
			return false;
		}
		
	}
}
