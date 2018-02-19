package util;

/**
 * @author NeilHazra
 * use serial to get data from arduino
 * TODO make cleaner and add semicolon delimited data
 */
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArduinoSerialRead {
	SerialPort arduinoPort;
	int distance = 0;

	public ArduinoSerialRead() {
		arduinoPort = new SerialPort(9600, SerialPort.Port.kUSB);
		// arduinoPort.enableTermination();
	}

	public synchronized int getDistance() {
		String s = arduinoPort.readString();
		//System.out.println("[" + s + "]");
		String[] x = s.split(";");
		if (x.length > 2) {
			s = x[1];
			try {
				distance = Integer.valueOf(s);
			} catch (NumberFormatException e) {
				System.out.println("Error NumberFormat Lidar: [" + s + "]");
			}

		} else {
			return distance;
		}
		
		SmartDashboard.putNumber("LidarDista", distance);

		return distance;
	}
}
