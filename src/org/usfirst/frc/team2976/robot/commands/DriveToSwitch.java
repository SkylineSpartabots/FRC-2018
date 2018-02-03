package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import Vision.VisionProcess;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToSwitch extends CommandGroup{
	
	Timer timer;
	
	boolean switchSide;
	VisionProcess vp = new VisionProcess(800, 640, 1.06, 0.6);
	
    public DriveToSwitch() {
    	timer = new Timer();
    	timer.start();
    	
    	requires(Robot.drivetrain);
    	requires(Robot.robotArm);
    	
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	switch(gameData.charAt(0)) {
    	case 'L':
    		switchSide = false;
    	case 'R':
    		switchSide = true; 
    	}
    	
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    	
    	if(switchSide) {
    		rightMovement();
    	}else {
    		leftMovement();
    	}
    	
    	Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
    }
    
    public void leftMovement() {
		//grab frame
		//vp.targeting(input);
		double tHeading = vp.getCurrentTarget().getHeading();
		//rotate to 20 degrees left of tHeading
		while(true) {
			//drivestraight, grabbing frames simultaneously
			//vp.targeting(input);
			tHeading = vp.getCurrentTarget().getHeading();
			if(tHeading > 20) {
				//rotate to even ~0 degrees with target
				break;
			}
		}
		//driveStraight, grabbing frames 
		//vp.targeting(input)
		//drive set distance
		
		//activate solenoids to hydraulic cube onto switch
		//rotate left 90, drive 3-4 ft, rotate right 90, drive straight 3ft
		}
    
    public void rightMovement() {
    	//grab frame
    	//vp.target(input);
    	double tHeading = vp.getCurrentTarget().getHeading();
    	//rotate to 20 degrees right of tHeading
    	while(true) {
    		//drivestraight, grabbing frames simultaneously
    		//vp.target(input);
    		tHeading = vp.getCurrentTarget().getDimensionRatio();
    		if(tHeading < -20) {
    			//rotate to even ~0 degrees with target
    			break;
    		}
    	}
    	//driveStraight, grabbing frames 
		//vp.targeting(input)
		//drive set distance
		
		//activate solenoids to hydraulic cube onto switch
		//rotate right 90, drive 3-4 ft, rotate left 90, drive straight 3ft
    }
}
