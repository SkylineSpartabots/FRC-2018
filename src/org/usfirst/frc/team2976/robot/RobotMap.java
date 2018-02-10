/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int rightFrontDriveMotor = 3;
	public static final int rightBackDriveMotor = 4;
	public static final int leftFrontDriveMotor = 1;
	public static final int leftBackDriveMotor = 2;
	public static final int robotArmMotorPort = 7;
	public static final int robotArmLimitSwitchPortUp = 5;
	public static final int robotArmLimitSwitchPortDown = 6;
	public static final int solenoidPort = 9;//TODO Invalid port number

	
}
