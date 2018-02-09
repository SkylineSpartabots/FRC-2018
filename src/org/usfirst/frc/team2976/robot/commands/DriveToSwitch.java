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
    		addSequential(new Rotate(30)); //get other target out of FOV
    		while(true) {
    			if(cvSink.grabFrame(rawImage) == 0) { //unsuccessful grab
	    			
	    		}else {
	    			break;
	    		}
    		}
    		
    		vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			addSequential(new Rotate(tHeading + 20)); //approach wide to avoid coming in sideways to wall
			
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while(true) {
				addSequential(new DriveStraight(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if(tHeading < -20) { //rotate even with target, come in straight
					addSequential(new Rotate(tHeading));
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new DriveStraight(vp.getCurrentTarget().getYDistance()));
			
			addSequential(new RampExtend());
			
			addSequential(new Rotate(90));
			addSequential(new DriveStraight(3));
			addSequential(new Rotate(-90));
			addSequential(new DriveStraight(3));
    		
    	}else { //same but for the left side switch
	    	addSequential(new Rotate(-20));
	    	while(true) {
	    		if(cvSink.grabFrame(rawImage) == 0) { //unsuccessful grab
	    			
	    		}else {
	    			break;
	    		}
	    	}
			vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			addSequential(new Rotate(tHeading - 20));
			
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while(true) {
				addSequential(new DriveStraight(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if(tHeading > 20) {
					addSequential(new Rotate(tHeading));
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new DriveStraight(vp.getCurrentTarget().getYDistance()));
			
			addSequential(new RampExtend());
			
			addSequential(new Rotate(-90));
			addSequential(new DriveStraight(3));
			addSequential(new Rotate(90));
			addSequential(new DriveStraight(3));
    	}
    }
    
    public void leftMovement() { //switch is on robot's side for these
    	addSequential(new DriveStraight(14));
    	addSequential(new Rotate(-90));
    	addSequential(new RampExtend());
    	addSequential(new Rotate(90));
	}
    
    public void rightMovement() {
    	addSequential(new DriveStraight(14));
    	addSequential(new Rotate(90));
    	addSequential(new RampExtend());
    	addSequential(new Rotate(-90));
    }
}
