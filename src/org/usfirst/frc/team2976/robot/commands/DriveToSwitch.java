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
public class DriveToSwitch extends CommandGroup {

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
		requires(Robot.switchArm);

		Robot.drivetrain.tankDrive(0.0, 0.0);
		usbCamera = CameraServer.getInstance().startAutomaticCapture();
		cvSink = CameraServer.getInstance().getVideo();
		rawImage = new Mat();

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
			addSequential(new TurnCorner(30)); // get other target out of FOV
			while (cvSink.grabFrame(rawImage) == 0) { // Get good frame

			}

			vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			addSequential(new TurnCorner(tHeading + 20)); // approach wide to avoid
														// coming in sideways to
														// wall

			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while (true) {
				addSequential(new TimedDrive(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if (tHeading < -20) { // rotate even with target, come in
										// straight
					addSequential(new TurnCorner(tHeading));
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new TimedDrive(vp.getCurrentTarget().getYDistance()));
			addSequential(new SwitchExtend());
			addSequential(new TurnCorner(90));
			addSequential(new TimedDrive(3));
			addSequential(new TurnCorner(-90));
			addSequential(new TimedDrive(3));

		} else { // same but for the left side switch
			addSequential(new TurnCorner(-20));
			while (true) {
				if (cvSink.grabFrame(rawImage) == 0) { // unsuccessful grab

				} else {
					break;
				}
			}
			vp.targeting(rawImage);
			double tHeading = vp.getCurrentTarget().getHeading();
			addSequential(new TurnCorner(tHeading - 20));

			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			while (true) {
				addSequential(new TimedDrive(1));
				cvSink.grabFrame(rawImage);
				vp.targeting(rawImage);
				tHeading = vp.getCurrentTarget().getHeading();
				if (tHeading > 20) {
					addSequential(new TurnCorner(tHeading));
					break;
				}
			}
			cvSink.grabFrame(rawImage);
			vp.targeting(rawImage);
			addSequential(new TimedDrive(vp.getCurrentTarget().getYDistance()));

			addSequential(new SwitchExtend());

			addSequential(new TurnCorner(-90));
			addSequential(new TimedDrive(3));
			addSequential(new TurnCorner(90));
			addSequential(new TimedDrive(3));
		}
	}

	public void leftMovement() { // switch is on robot's side for these
		addSequential(new TimedDrive(14));
		addSequential(new TurnCorner(-90));
		addSequential(new SwitchExtend());
		addSequential(new TurnCorner(90));
	}

	public void rightMovement() {
		addSequential(new TimedDrive(14));
		addSequential(new TurnCorner(90));
		addSequential(new SwitchExtend());
		addSequential(new TurnCorner(-90));
	}
}
