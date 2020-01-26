/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.util.TelemetryUtil;
import frc.robot.util.TelemetryUtil.PrintStyle;
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
		rightIntakeMotor.setNeutralMode(NeutralMode.Brake);
		rightIntakeMotor.setInverted(true);
		leftIntakeMotor.setNeutralMode(NeutralMode.Brake);

		TelemetryUtil.print("Intake has been initialized", PrintStyle.INFO, true);
	}
	
			
	public void setPower(double power) {
		rightIntakeMotor.set(power);
		leftIntakeMotor.set(power);
	}
	
	public void initDefaultCommand() {
	
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
