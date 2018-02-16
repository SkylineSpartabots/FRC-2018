package util;

/**
 * @author NeilHazra
 * 
 * Library for reading the TMD3782 Color Sensor
 * 
 */

import edu.wpi.first.wpilibj.I2C;


public class TMDColor {
	byte[] id = new byte[1];
	I2C m_i2c;
	int red;
	int blue;
	int green;
	int proximity;

	// Port address is 0x39
	public TMDColor() {
		m_i2c = new I2C(I2C.Port.kOnboard, 0x39);
		m_i2c.read(0x80 | 0x12, 1, id);
		System.out.println(Integer.toHexString(id[0]));
		initSensor();
	}

	public void initSensor() {
		/*
		 * Bit 5: Proximity Enable Bit 4: Ambient Light Sensor Bit 3: Wait Enable Bit 2:
		 * Proximity Sensor Bit 1: ADC Enable Bit 0: Power on
		 */
		m_i2c.write(0x80 | 0x00, 0b00010111);
		/*
		 * 0xFF = 2.38 ms 0xF6 = 24 ms 0xD6 = 42 0xC0 = 64 0x00 = 609 ms
		 */
		m_i2c.write(0x80 | 0x01, 0xFF);
		/*
		 * 8 bit number describing number of pulses
		 */
		m_i2c.write(0x80 | 0x0E, 0b01000000);
		/*
		 * Bit 6/7: PDRIVE for IR 00 -->100% and 11--> 12.5% Bit 5: Needs to be 1 Bit 4:
		 * PSAT 0,1 use 0 Bit 3: Reserved Write 0 Bit 2: Reserved Write 0 Bit 0/1: Gain
		 * 00-> 1x to to 10 --> 16x to 11->60x
		 */
		m_i2c.write(0x80 | 0x0F, 0b01100010);
	}

	public double[] getRGB_Proximity() {
		double[] rgbProximity = new double[4];
		byte buffer[] = new byte[8];
		m_i2c.read(0xA0 | 0x16, 8, buffer); //read 8 bytes starting from register 0x16 with autoincrement
		red = buffer[0] + buffer[1]<<8;
		green = buffer[2] + buffer[3]<<8;
		blue = buffer[4] + buffer[5]<<8;
		proximity = buffer[6] + buffer[7]<<8;
		System.out.println(red);
		int maxColor = Math.max(red, Math.max(green, blue));
		if(maxColor != 0 )	{
			red *= 255.0/maxColor;
			green *= 255.0/maxColor;
			blue *= 255.0/maxColor;
		}
		rgbProximity[0] = red;
		rgbProximity[1] = green;
		rgbProximity[2] = blue;
		rgbProximity[3] = proximity;
		
		return rgbProximity;
	}

	public double getRedColor() {
		getRGB_Proximity();
		//System.out.println(Integer.toHexString(id[0]));
		return red;
	}

	public double getGreenColor() {
		//System.out.println(Integer.toHexString(id[0]));
		getRGB_Proximity();
		return green;
	}

	public double getBlueColor() {
		//System.out.println(Integer.toHexString(id[0]));
		getRGB_Proximity();
		return blue;
	}

}