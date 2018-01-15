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

    public RobotArm(double p, double i, double d) {
		super(p, i, d);
	}
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX robotMotorArm= new WPI_TalonSRX(RobotMap.robotArmMotorPort); 
	//the limit switch that is pressed when the arm goes up
	DigitalInput robotArmLimitSwitchUp= new DigitalInput(RobotMap.robotArmLimitSwitchPortUp);
	//the limit switch that is pressed when the arm goes down
	DigitalInput robotArmLimitSwitchDown= new DigitalInput(RobotMap.robotArmLimitSwitchPortDown);
	
	Encoder armEncoder = new Encoder(0,1); //TODO FIX PORTS
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void setPower(double p){
		robotMotorArm.set(ControlMode.PercentOutput, p);
	}
	
	public boolean getArmUpLimitSwitch(){
		boolean armSwitchState = robotArmLimitSwitchUp.get();
		return armSwitchState;
	}
	public boolean getArmDownLimitSwitch(){
		boolean armSwitchState = robotArmLimitSwitchDown.get();
		return armSwitchState;
	}
	@Override
	protected double returnPIDInput() {

		return armEncoder.get();
	}
	
	@Override
	protected void usePIDOutput(double output) { 
		
		if (getArmUpLimitSwitch() == true && output > 0) { //up > 0
			robotMotorArm.set(robotMotorArm.getControlMode(),1); //some small power so the arm doesn't fall down
		} else if (getArmUpLimitSwitch() == true && 0 > output) { //up < 0
			robotMotorArm.set(robotMotorArm.getControlMode(),output); //give output power
		} else if (getArmDownLimitSwitch() == true && 0 < output) { //down < 0
			robotMotorArm.set(robotMotorArm.getControlMode(), 0); //no power
		} else if (getArmDownLimitSwitch() == true && 0 > output) { //down > 0
			robotMotorArm.set(robotMotorArm.getControlMode(), output); //give output power
		} else {
			robotMotorArm.set(robotMotorArm.getControlMode(), output); //give output power
		}
	 
	}
}

