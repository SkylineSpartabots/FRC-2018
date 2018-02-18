package util;

import org.usfirst.frc.team2976.robot.Robot;

/**
 * @author NeilHazra
 * use serial to get data from arduino
 */
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArduinoSerialRead {
	SerialPort arduinoPort;
	int distance = 0;

	public ArduinoSerialRead() {
		try	{
			arduinoPort = new SerialPort(9600, SerialPort.Port.kUSB);
		}	catch (Exception e)	{
			Robot.isLidarFunctional = false;
		}	catch (Error e)	{
			Robot.isLidarFunctional = false;
		}
	}

	public synchronized int getDistance() {
		String s = arduinoPort.readString();
		String y0 = "";
		try {
			String[] x = s.split("\n");
			if (x.length > 0) {
				System.out.println("x0:[" + x[0] + "]");
				String[] y = x[0].split("\r");
				if (y.length > 0) {

					y0 = y[0];
					System.out.println("y0:[" + y[0] + "]");
					distance = Integer.valueOf(y0);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Error NumberFormat Lidar: [" + y0 + "]");
		}

		SmartDashboard.putNumber("LidarDista", distance);
		// Thread.sleep(50);
		return distance;
	}
}