package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotArm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon robotMotorArm= new CANTalon(RobotMap.robotArmMotorPort); 
	
	DigitalInput RobotArmLimitSwitch= new DigitalInput(RobotMap.robotArmLimitSwitchPort);
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void setPower(double p){
		robotMotorArm.set(p);
	}
	public boolean getArmLimitSwitch(){
		boolean armSwitchState=RobotArmLimitSwitch.get();
		return armSwitchState;
	}
}

