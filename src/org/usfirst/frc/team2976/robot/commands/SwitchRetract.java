package org.usfirst.frc.team2976.robot.commands;

import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRetract extends Command {

    public SwitchRetract() {
    	requires(Robot.switchArm);
    }
    
    public void initialize() {
    	Robot.switchArm.retract();
    }

	@Override
	protected boolean isFinished() {
		if(!(Robot.switchArm.getRampSolenoid().get())) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
}
