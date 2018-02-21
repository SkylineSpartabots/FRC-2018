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
		try	{
			arduinoPort = new SerialPort(9600, SerialPort.Port.kUSB);
		} catch(Exception e) {
			arduinoPort = null;
		}
			// arduinoPort.enableTermination();
	}

	public synchronized int getDistance() {
		String s;
		if(arduinoPort!= null)	{
			s = arduinoPort.readString();
		}	else {
			return -1;
		}
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
