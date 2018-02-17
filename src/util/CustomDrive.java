package util;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author JasmineCheng
 *
 */
public class CustomDrive extends DifferentialDrive {

	public CustomDrive(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		// TODO Auto-generated constructor stub
	}

	public void customRotationArcadeDrive(double xSpeed, double zRotation) {
		
		
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);

		zRotation = logCurve(zRotation)*0.5;
		
		zRotation = limit(zRotation);
		zRotation = applyDeadband(zRotation, m_deadband);
		
		
		SmartDashboard.putNumber("Z Rotation", zRotation);
		double leftMotorOutput;
		double rightMotorOutput;

		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

		if (xSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			} else {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			}
		}
		
		SmartDashboard.putNumber("X Speed", xSpeed);
		SmartDashboard.putNumber("Left Motor Output", leftMotorOutput);
		SmartDashboard.putNumber("Right Motor Output", rightMotorOutput);
		tankDrive(leftMotorOutput, rightMotorOutput, false);
	}
	
	double squared(double value) {
		return Math.copySign(value * value, value);
	}
	
	double logCurve(double value) {
		SmartDashboard.putNumber("Log Curve", value);
		double curvedValue = 0;
		if(Math.abs(value)>0.02) {
			curvedValue = Math.log(38.36*(Math.abs(value))+1)*0.2654;
		} 
		
		SmartDashboard.putNumber("Log Curve post", curvedValue);
		return Math.copySign(curvedValue, value);
		
	}
}
