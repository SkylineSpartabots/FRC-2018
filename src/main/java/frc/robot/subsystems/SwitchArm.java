package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.util.TelemetryUtil;
import frc.robot.util.TelemetryUtil.PrintStyle;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SwitchArm extends Subsystem {
	Solenoid rampSolenoid;

	public SwitchArm() {
		rampSolenoid = new Solenoid(RobotMap.switchSolenoidPort);
		rampSolenoid.set(false);
		TelemetryUtil.print("Solenoid Arm has been initialized", PrintStyle.INFO, true);
	}

	public void initDefaultCommand() {}

	public void extend() {	
		rampSolenoid.set(true);
	}

	public void retract() {
		rampSolenoid.set(false);
	}

	public Solenoid getRampSolenoid() {
		return rampSolenoid;
	}
}
