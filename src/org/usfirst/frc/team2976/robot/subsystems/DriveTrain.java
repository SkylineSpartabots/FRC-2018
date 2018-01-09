/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	SpeedController leftFront, leftBack, rightFront, rightBack;
	SpeedControllerGroup left, right;

	DifferentialDrive m_drive;
	
	public DriveTrain() {
		leftFront = (SpeedController) new TalonSRX(RobotMap.leftFrontDriveMotor);
		leftBack = (SpeedController) new TalonSRX(RobotMap.leftBackDriveMotor);
		rightFront = (SpeedController) new TalonSRX(RobotMap.rightFrontDriveMotor);
		rightBack = (SpeedController) new TalonSRX(RobotMap.rightBackDriveMotor);
		
		left = new SpeedControllerGroup(leftFront, leftBack);
		right = new SpeedControllerGroup(rightFront, rightBack);
		
		m_drive = new DifferentialDrive(left, right);
		
	}
	
	public void drive(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		m_drive.tankDrive(leftSpeed, rightSpeed);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
