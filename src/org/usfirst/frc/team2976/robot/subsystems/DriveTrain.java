/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.CustomDrive;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	WPI_TalonSRX leftFront, leftBack, rightFront, rightBack;
	SpeedControllerGroup left, right;
	Encoder encoderLeft, encoderRight;
	CustomDrive m_drive;

	public DriveTrain() {
		leftFront = new WPI_TalonSRX(RobotMap.leftFrontDriveMotor);
		leftBack = new WPI_TalonSRX(RobotMap.leftBackDriveMotor);
		rightFront = new WPI_TalonSRX(RobotMap.rightFrontDriveMotor);
		rightBack = new WPI_TalonSRX(RobotMap.rightBackDriveMotor);

		// Set in brake mode
		leftFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);

		left = new SpeedControllerGroup(leftFront, leftBack);
		right = new SpeedControllerGroup(rightFront, rightBack);
		encoderLeft = new Encoder(RobotMap.leftDriveEncoder1, RobotMap.leftDriveEncoder2);
		encoderRight = new Encoder(RobotMap.rightDriveEncoder1, RobotMap.rightDriveEncoder2);

		setEncoderParameters(encoderLeft);
		setEncoderParameters(encoderRight);

		m_drive = new CustomDrive(left, right);
	}

	public double getLeftEncoderDistance() {
		return encoderLeft.getDistance() / 360;

	}

	public double getRightEncoderDistance() {
		return encoderRight.getDistance();

	}

	public double getLeftEncoderCount() {
		return encoderLeft.get() / 360;
	}

	public double getRightEncoderCount() {
		return encoderRight.get();
	}

	public double getLeftEncoderRate() {
		return encoderLeft.getRate();

	}

	public double getRightEncoderRate() {
		return encoderRight.getRate();

	}

	public void resetEncoders() {
		encoderLeft.reset();
		encoderRight.reset();

	}

	public void setEncoderParameters(Encoder enc) {
		enc.setMaxPeriod(.1);
		enc.setMinRate(10);
		enc.setDistancePerPulse(5);
		enc.setReverseDirection(true);
		enc.setSamplesToAverage(7);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}

	public void drive(double speed, double rotation) {
		SmartDashboard.putNumber("Power", speed);
		m_drive.arcadeDrive(speed, rotation, true);
	}

	public void customRotationDrive(double speed, double rotation) {
		m_drive.customRotationArcadeDrive(speed, rotation);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ArcadeDriveWithJoystick());
	}
}
