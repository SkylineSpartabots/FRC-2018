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
public class PIDTurn2 extends Command {
    PIDSource NAVXSource;
	SimplePID turnPID;
	double initDegrees = 0;
	double degrees = 0;
	Timer timer;
	boolean firstTime = true;
	long timeStart = Long.MAX_VALUE;
    public PIDTurn2(double degrees) {	
    	requires(Robot.drivetrain);
    	initDegrees = Robot.rps.getAngle();
    	this.degrees = degrees;
    	NAVXSource =  new PIDSource()	{
    		public double getInput()	{
    			System.out.println("Angle" + Robot.rps.getAngle());
    			return Robot.rps.getAngle();
    		}
    	};
    	double kP = 0.058;//0.009;
    	double kI = 0.00	;//0.002;
    	double kD = 0.0;//0.00008;
	
    	turnPID = new SimplePID(NAVXSource, this.degrees, kP,kI,kD);
    	turnPID.setOutputLimits(-0.7, 0.7);
    //	System.out.println("PIDTurn Constructor\n\n Angle=" + Robot.rps.getAngle() + ", Degrees=" + this.degrees + ", initDegrees=" + this.initDegrees + ",Timer=" + timer.get());
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	turnPID.resetPID();
    	timer.start();
    	//System.out.println("PIDTurn Initialize \n\n Angle=" + Robot.rps.getAngle() + ", Degrees=" + this.degrees + ", initDegrees=" + this.initDegrees + ",Timer=" + timer.get());    	
    }	
    double output = 0;
    double lastTimeOut = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = turnPID.compute();
    	if((Math.abs(turnPID.getError()) > 5)){
    		lastTimeOut = timer.get();
    	}
    	Robot.drivetrain.tankDrive(-output, output);
    	System.out.println("Angle=" + Robot.rps.getAngle() + ", PIDError=" + turnPID.getError()+ ", PIDEoutput=" + output + ",Timer=" + timer.get());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("TurnFinished", System.currentTimeMillis() - timeStart>100);
        //return System.currentTimeMillis() - timeStart > 100;
    	
        return timer.hasPeriodPassed(1) || (timer.get()-lastTimeOut>0.5);
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("\n\n End\n Angle=" + Robot.rps.getAngle() + ", PIDError=" + turnPID.getError()+ ", PIDEoutput=" + output + ",Timer=" + timer.get());
    
    	timer.reset();
    	turnPID.resetPID();
    	Robot.drivetrain.setBrake();
    	
    	System.out.println("\n End End \n ");
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}