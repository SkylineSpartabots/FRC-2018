package util;

/**
 * @author NeilHazra
 * 
 * Library for reading the Lidar Lite sensor
 * 
 */
//TODO Maybe use i2c multiplexer
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;


public class LidarLite {
	byte[] id = new byte[1];
	byte[] data = new byte[2];
	public static final int REGISTER_MEASURE = 0x00;
	public static final int MEASURE_VALUE = 0x04;
	public static final int REGISTER_HIGH_LOW_B = 0x8f;
	I2C generalSensorWriteAll;
	I2C rightSensor;
	I2C leftSensor;
	// Port address is default 0x62
	
	public LidarLite() {
		generalSensorWriteAll = new I2C(I2C.Port.kOnboard, 0x62);
		connectRightSensor();
		//connectLeftSensor();	
	}
	public int getValue()
	{
		return (int) Integer.toUnsignedLong(data[0] << 8) + Byte.toUnsignedInt(data[1]);
	}
	public void update()
	{
		while (generalSensorWriteAll.write(REGISTER_MEASURE, MEASURE_VALUE))
		{
			Timer.delay(0.001);
		}
		while (generalSensorWriteAll.read(REGISTER_HIGH_LOW_B, 2, data))
		{
			Timer.delay(0.001);
		}
	}
	
	/*
	 * The device id of the right sensor should be 32-72
	 * in other words the first sensor we bought
	 */
	void connectRightSensor()	{
		generalSensorWriteAll.write(0x18, 32); //write first byte (high
		generalSensorWriteAll.write(0x19, 72); //write second byte (low)
		generalSensorWriteAll.write(0x1a, 0x66); //write address 
		rightSensor = new I2C(I2C.Port.kOnboard, 0x66);	
		generalSensorWriteAll.write(0x1e, 0x00);//get rid of default addresses
	}
	//the left sensor device TODO
	void connectLeftSensor()	{
		generalSensorWriteAll.write(0x18, 0); //write first byte (high
		generalSensorWriteAll.write(0x19, 0); //write second byte (low)
		generalSensorWriteAll.write(0x1a, 0x60); //write address 
		leftSensor = new I2C(I2C.Port.kOnboard, 0x60);
		generalSensorWriteAll.write(0x1e, 0x00);//get rid of default addresses
	}

	public int getRightDistance() {
		rightSensor.write(0x00, 0x04);
		byte[] buffer = new byte[2];
		rightSensor.read(0x80 | 0x0f, 2, buffer);
		int distance = buffer[0]<<8 | buffer[1];
		return distance;
	}
	public int getLeftDistance() {
		leftSensor.write(0x00, 0x04);
		byte[] buffer = new byte[2];
		leftSensor.read(0x80 | 0x0f, 2, buffer);
		int distance = buffer[0]<<8 | buffer[1];
		return distance;
	}
}