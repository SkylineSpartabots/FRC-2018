/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class EncoderTest extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Encoder enc;
	
	public EncoderTest() {
		
		enc = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		
	}
	
	public void setEncParameters() {
		enc.setMaxPeriod(.1);
		enc.setMinRate(10);
		enc.setDistancePerPulse(5);
		enc.setReverseDirection(true);
		enc.setSamplesToAverage(7);
	}
	
	public String encString() {
		return enc.toString();
	}
	
	public void resetEnc() {
		enc.reset();
	}
	
	public void getValues() {
		int count = enc.get();
		double distance = enc.getRaw();
		distance = enc.getDistance();
		//double period = enc.getPeriod();
		double rate = enc.getRate();
		boolean direction = enc.getDirection();
		boolean stopped = enc.getStopped();

	}
	
	public int getCount() {
		return enc.get();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
