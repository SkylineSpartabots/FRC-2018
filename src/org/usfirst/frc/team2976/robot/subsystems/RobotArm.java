package org.usfirst.frc.team2976.robot.subsystems;

import org.usfirst.frc.team2976.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotArm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX robotMotorArm= new TalonSRX(RobotMap.robotArmMotorPort); 
	//the limit switch that is pressed when the arm goes up
	DigitalInput robotArmLimitSwitchUp= new DigitalInput(RobotMap.robotArmLimitSwitchPortUp);
	//the limit switch that is pressed when the arm goes down
	DigitalInput robotArmLimitSwitchDown= new DigitalInput(RobotMap.robotArmLimitSwitchPortDown);
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void setPower(double p){
		//robotMotorArm.set(p);
		//lacks a ControlMode parameter
	}
	
	public boolean getArmUpLimitSwitch(){
		boolean armSwitchState = robotArmLimitSwitchUp.get();
		return armSwitchState;
	}
	public boolean getArmDownLimitSwitch(){
		boolean armSwitchState = robotArmLimitSwitchDown.get();
		return armSwitchState;
	}
}

