/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_TalonSRX rightIntakeMotor, leftIntakeMotor;
	
	public Intake() {
		rightIntakeMotor = new WPI_TalonSRX(RobotMap.rightIntakeMotor);
		leftIntakeMotor = new WPI_TalonSRX(RobotMap.leftIntakeMotor);
	}
	
	public double getAvgCurrentDraw()	{
		return 0.5*(rightIntakeMotor.getOutputCurrent() + leftIntakeMotor.getOutputCurrent());
	}
	
	public void setPower(double abspower, boolean rollIn) {
		double power = Math.abs(abspower);
		if(rollIn) {
			rightIntakeMotor.set(ControlMode.PercentOutput,power);
			leftIntakeMotor.set(ControlMode.PercentOutput,-power);
		} else {
			rightIntakeMotor.set(ControlMode.PercentOutput,-power);
			leftIntakeMotor.set(ControlMode.PercentOutput,power);
		}		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
