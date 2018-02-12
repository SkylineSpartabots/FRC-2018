package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SwitchArm extends Subsystem {
	Solenoid rampSolenoid;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public SwitchArm() {
		rampSolenoid = new Solenoid(RobotMap.rampSolenoidPort);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
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

