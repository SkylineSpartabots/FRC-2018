/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot;

import org.usfirst.frc.team2976.robot.commands.DriveStraight;
import org.usfirst.frc.team2976.robot.commands.RobotArmMovementDown;
import org.usfirst.frc.team2976.robot.commands.RobotArmMovementUp;
import org.usfirst.frc.team2976.robot.commands.RollIntake;
import org.usfirst.frc.team2976.robot.commands.SwitchAuto;
import org.usfirst.frc.team2976.robot.commands.SwitchExtend;
import org.usfirst.frc.team2976.robot.commands.SwitchRetract;
import org.usfirst.frc.team2976.robot.commands.TurnCorner;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driveStick;
	public Joystick secondStick;

	public enum Button {
		RBumper(6), LBumper(5), A(1), B(2), X(3), Y(4), RightJoystickBtn(10), LeftJoystickBtn(9);

		private final int number;

		Button(int number) {
			this.number = number;
		}

		public int getBtnNumber() {
			return number;
		}
	}

	public enum Axis {
		LX(0), LY(1), LTrigger(2), RTrigger(3), RX(4), RY(5);
		private final int number;

		Axis(int number) {
			this.number = number;
		}

		public int getAxisNumber() {
			return number;
		}
	}

	public OI() {
		driveStick = new Joystick(0);
		secondStick = new Joystick(1);
		//if (Robot.isScale) {	
			//new JoystickButton(secondStick, Axis.RTrigger.getAxisNumber()).whileHeld(new RobotArmMovementUp());
			//new JoystickButton(secondStick, Axis.LTrigger.getAxisNumber()).whileHeld(new RobotArmMovementDown());
		//} else {
			//new JoystickButton(secondStick, Button.A.getBtnNumber()).whenPressed(new SwitchExtend());
			//new JoystickButton(secondStick, Button.B.getBt																			nNumber()).whenPressed(new SwitchRetract());
		//}
		new JoystickButton(secondStick, Button.A.getBtnNumber()).whenPressed(new SwitchExtend());
		new JoystickButton(secondStick, Button.B.getBtnNumber()).whenPressed(new SwitchRetract());
		new JoystickButton(secondStick, Button.X.getBtnNumber()).whileHeld(new RollIntake(0.65, true));
		new JoystickButton(secondStick, Button.Y.getBtnNumber()).whileHeld(new RollIntake(0.65, false));
		new JoystickButton(driveStick, Button.A.getBtnNumber()).whenPressed(new DriveStraight(3.5));
		new JoystickButton(driveStick, Button.B.getBtnNumber()).whenPressed(new TurnCorner(-90));
		
		//new JoystickButton(driveStick, Button.A.getBtnNumber()).whileHeld(new SwitchAuto(2));
	}
}
