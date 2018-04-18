package org.usfirst.frc.team2976.robot.commands;
import org.usfirst.frc.team2976.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.SimplePID;
import util.PIDSource;
/**
 *
 */
public class PIDTurn extends Command {
    PIDSource NAVXSource;
	SimplePID turnPID;
	double initDegrees = 0;
	double degrees = 0;
	Timer timer;
	boolean firstTime = true;
	long timeStart = Long.MAX_VALUE;
    public PIDTurn(double degrees) {	
    	requires(Robot.drivetrain);
    	Robot.rps.reset();
    	initDegrees = Robot.rps.getAngle();
    	this.degrees = degrees + initDegrees;
    	NAVXSource =  new PIDSource()	{
    		public double getInput()	{
    			System.out.println("Angle" + Robot.rps.getAngle());
    			return Robot.rps.getAngle();
    		}
    	};
    	double kP = 0.045;//0.009;
    	double kI = 0.00	;//0.002;
    	double kD = 0.0;//0.00008;
    	
    	turnPID = new SimplePID(NAVXSource, this.degrees, kP,kI,kD);
    	turnPID.setOutputLimits(-0.6, 0.6);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	turnPID.resetPID();
    	Robot.rps.reset();
    	timer.start();
    }	
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double output = turnPID.compute();
    	Robot.drivetrain.tankDrive(-output, output);
    	System.out.println("Angle" + Robot.rps.getAngle());
    	System.out.println("PIDError" + turnPID.getError());
    	System.out.println("PIDEoutput" + output);
    	
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("TurnFinished", System.currentTimeMillis() - timeStart>100);
        //return System.currentTimeMillis() - timeStart > 100;
        return timer.hasPeriodPassed(1) || (Math.abs(turnPID.getError()) < 2);
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	timer.reset();
    	turnPID.resetPID();
    	Robot.drivetrain.setBrake();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}