package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class RobotArm extends PIDSubsystem {
	public static final double DEFAULT_ARM_POWER = 0.6;

	public RobotArm(double p, double i, double d) {
		super(p, i, d);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_TalonSRX robotMotorArm = new WPI_TalonSRX(RobotMap.robotArmMotorPort);
	// the limit switch that is pressed when the arm goes up
	DigitalInput robotArmLimitSwitchUp = new DigitalInput(RobotMap.robotArmLimitSwitchPortUp);
	// the limit switch that is pressed when the arm goes down
	DigitalInput robotArmLimitSwitchDown = new DigitalInput(RobotMap.robotArmLimitSwitchPortDown);

	Encoder armEncoder = new Encoder(10, 11); // TODO FIX PORTS

	public void initDefaultCommand() {
	}

	public void setPower(double p) {
		robotMotorArm.set(ControlMode.PercentOutput, p);
	}

	public boolean getArmUpLimitSwitch() {
		boolean armSwitchState = robotArmLimitSwitchUp.get();
		return armSwitchState;
	}

	public boolean getArmDownLimitSwitch() {
		boolean armSwitchState = robotArmLimitSwitchDown.get();
		return armSwitchState;
	}

	@Override
	protected double returnPIDInput() {

		return armEncoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		// Opened up if statements for easier maintenance
		if (getArmUpLimitSwitch()) {
			if (output > 0) {
				robotMotorArm.set(robotMotorArm.getControlMode(), 1);// Little
																		// power
																		// to
																		// keep
																		// arm
																		// up
			} else {
				robotMotorArm.set(robotMotorArm.getControlMode(), output);
			}
		} else if (getArmDownLimitSwitch()) {
			if (output < 0) {
				robotMotorArm.set(robotMotorArm.getControlMode(), 0);
			} else {
				robotMotorArm.set(robotMotorArm.getControlMode(), output);
			}
		} else {
			robotMotorArm.set(robotMotorArm.getControlMode(), output);
		}

	}
}
