/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2976.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class I2C_ColorSensor extends SensorBase {

	I2C m_i2c;

	public I2C_ColorSensor() {
		//m_i2c = new I2C(I2C.Port.kMXP, 0x62);
		m_i2c = new I2C(I2C.Port.kOnboard, 0x39);
	
		initSensor();
	}

	public void initSensor() {
		// nothing to do
	}

	public int getColor() {

		byte[] buffer;
		
		buffer = new byte[2];

		//m_i2c.write(0x00, 0x04);
		int increment = 0;
		/*SmartDashboard.putNumber("Increment", increment);
		while(buffer1[0] != 0) {
			m_i2c.read(0x01, 1, buffer1);
			SmartDashboard.putNumber("Increment", increment);
			increment++;
		}*/
		//m_i2c.read(0x8f, 2, buffer);
		
		m_i2c.read(0x04, 2, buffer);
		return (int) Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1])+1;
		//return (int)Integer.toUnsignedLong(buffer[1]);
	}

	public int getDistance2() {
		m_i2c.write(0x00,0x03);
		byte distanceArray[] = new byte[2];
		m_i2c.read(0x8f,2,distanceArray);
		int distance = (distanceArray[0] << 8) + distanceArray[1];
		return distance;
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		// TODO Auto-generated method stub
		
	}

}