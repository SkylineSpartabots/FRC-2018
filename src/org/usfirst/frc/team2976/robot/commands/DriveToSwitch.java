package org.usfirst.frc.team2976.robot.commands;

import org.opencv.core.Mat;
import org.usfirst.frc.team2976.robot.Robot;

import Vision.VisionProcess;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
	
	UsbCamera usbCamera;
	CvSink cvSink;
	Mat rawImage;
	VisionProcess vp;
	
    public DriveToSwitch(boolean switchSide, int position) {
    	this.switchSide = switchSide;
    	timer = new Timer();
    	timer.start();
    	
    	requires(Robot.drivetrain);
    	requires(Robot.robotArm);
    	
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    	usbCamera = CameraServer.getInstance().startAutomaticCapture();
    	cvSink = CameraServer.getInstance().getVideo();
    	rawImage = new Mat();
    	
    	if(position == 1) { //center auto has two options
    		if(switchSide) {
        		rightMovement();
        	}else {
        		leftMovement();
        	}
    	}else {
    		centerMovement(switchSide);
    	}
    	
    	
    	Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
    }
    
    public void centerMovement(boolean switchSide) {
    	if(switchSide) {
    		//rotate 20 degrees right
    		while(true) {
    			if(cvSink.grabFrame(rawImage) == 0) { //unsuccessful grab
	    			
	    		}else {
	    			break;
	    		}
    		}
    		vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			//rotate to 20 degrees right of tHeading
			
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while(true) {
				addSequential(new DriveStraight(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if(tHeading < -20) {
					//rotate to even ~0 degrees with target
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new DriveStraight(vp.getCurrentTarget().getYDistance()));
			
			//activate solenoids to hydraulic cube onto switch
			
			
			//rotate right 90
			addSequential(new DriveStraight(3));
			//rotate left 90
			addSequential(new DriveStraight(3));
    		
    	}else {
	    	//rotate 20 degrees left
	    	while(true) {
	    		if(cvSink.grabFrame(rawImage) == 0) { //unsuccessful grab
	    			
	    		}else {
	    			break;
	    		}
	    	}
			vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			//rotate to 20 degrees left of tHeading
			
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while(true) {
				addSequential(new DriveStraight(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if(tHeading > 20) {
					//rotate to even ~0 degrees with target
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new DriveStraight(vp.getCurrentTarget().getYDistance()));
			
			//activate cube mech
			
			//rotate left 90 
			addSequential(new DriveStraight(3));
			//rotate right 90
			addSequential(new DriveStraight(3));
    	}
    }
    
    public void leftMovement() {	
    	addSequential(new DriveStraight(14));
    	//rotate 90 left
    	//activate cube mech
    	//rotate 90 right
	}
    
    public void rightMovement() {
    	addSequential(new DriveStraight(14));
    	//rotate 90 right
    	//activate cube mech
    	//rotate 90 left
    }
}
