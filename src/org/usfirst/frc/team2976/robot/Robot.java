/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.TMDColor;
import util.ArduinoSerialRead;
import util.RPS;

import org.usfirst.frc.team2976.robot.OI;
import org.usfirst.frc.team2976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2976.robot.subsystems.Intake;
import org.usfirst.frc.team2976.robot.subsystems.SwitchArm;
import org.usfirst.frc.team2976.robot.subsystems.RobotArm;
import org.usfirst.frc.team2976.robot.commands.Autonomous;
import org.usfirst.frc.team2976.robot.commands.FancyAuto;
import org.usfirst.frc.team2976.robot.commands.MiddleAuto;
import org.usfirst.frc.team2976.robot.commands.PIDTurn;
import org.usfirst.frc.team2976.robot.commands.TimedDrive;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	Compressor c = new Compressor(0);
	public static DriveTrain drivetrain;
	public static RobotArm robotArm;
	public static SwitchArm switchArm;
	public static Intake intake;
	//public static TMDColor color;
	public static OI oi;
	public static RPS rps;
	
	public static String gameData = "";
	//public static ArduinoSerialRead arduino;
	public static AutoTargetSide autoTargetSide = AutoTargetSide.Unknown;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public static void getGameData()	{
		autoTargetSide = AutoTargetSide.Unknown;
		gameData = "";
		if(DriverStation.getInstance() != null)	{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		System.out.println(gameData);
		
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				autoTargetSide = AutoTargetSide.Left;
			} else if (gameData.charAt(0) == 'R') {
				autoTargetSide = AutoTargetSide.Right;
			} else {
				autoTargetSide = AutoTargetSide.Unknown;
			}			
		}
		System.out.println("Side= " + autoTargetSide);
		
	}
	@Override
	public void robotInit() {
		rps = new RPS(0,0);
		c.setClosedLoopControl(true);
		drivetrain = new DriveTrain();
		robotArm = new RobotArm(2, 0, 0); // TODO add actual PID values here
		switchArm = new SwitchArm();
		intake = new Intake();
		oi = new OI();
		
		//UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		//camera.setResolution(320, 240);
		//camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
		//camera.setResolution(800, 640);
		//m_chooser.addDefault("CenterAuto", new CenterAutoLidar());
		//m_chooser.addObject("RightAuto", new RightAutoLidar());
		//m_chooser.addObject("LeftAuto", new LeftAutoLidar());
		//m_chooser.addDefault("Middle Auto", new MiddleAuto());
		//m_chooser.addObject("Right Auto", new Autonomous(true));
		//m_chooser.addObject("Left Auto", new Autonomous(false));
		//m_chooser.addObject("DriveStraight", new TimedDrive(0.5,5));
		
		SmartDashboard.putData("Auto", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.switchArm.retract();
	}

	@Override
	public void disabledPeriodic() {
		
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		getGameData();
		
		//m_autonomousCommand = new Autonomous(true); //right side
		//m_autonomousCommand = new Autonomous(false); //left side
		//m_autonomousCommand = new TimedDrive(0.5,5); //drive straight
		m_autonomousCommand = new MiddleAuto(); //drive straight
		//m_autonomousCommand = m_chooser.getSelected();
		//m_autonomousCommand = new PIDTurn(-45);
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();	
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot.switchArm.retract();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
