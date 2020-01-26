/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int leftFrontDriveMotor = 1;//4;//1;
	public static final int leftBackDriveMotor = 3;//5;//2;
	public static final int rightFrontDriveMotor = 5;//1;//3;
	public static final int rightBackDriveMotor = 6;//3;//4;
	
	public static final int rightIntakeMotor = 4;
	public static final int leftIntakeMotor = 2;


	public static final int switchSolenoidPort = 4;

	// this scale factor means 1 click = 0.06 inches;
	public static final double encoderDistanceScaleFactor = 0.06;

}
